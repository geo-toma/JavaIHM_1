package arbres;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTree arbre, arbre1, arbre2;
	private JSplitPane split, split1, split2;
	private DefaultTreeCellRenderer[] treeCellRenderers = new DefaultTreeCellRenderer[2];
	private DefaultTreeModel model;
	private JButton button = new JButton("Ajouter"), button1 = new JButton("Effacer");

	public Fenetre(/* String lookAndFeel */) {
		this.setSize(/*700, 300*/300, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("modifier");
		// String title = lookAndFeel.split("\\.")[lookAndFeel.split("\\.").length - 1];
		// this.setTitle("Nom du look and feel : "+title);

		buldTree();
		// initCellRenderer();
		// listRoot();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (arbre.getLastSelectedPathComponent() != null) {
					String addNode = JOptionPane.showInputDialog("Saisir le non du noeud");
					if (addNode != null && !addNode.trim().equals("")) {
						DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
						DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(addNode);
						parentNode.add(childNode);
						model.insertNodeInto(childNode, parentNode, parentNode.getChildCount() - 1);
						model.nodeChanged(parentNode);
					}
				} else
					JOptionPane.showMessageDialog(null, "Aucune selection!");
			}
		});
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arbre.getLastSelectedPathComponent() != null) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
					model.removeNodeFromParent(node);

				} else
					JOptionPane.showMessageDialog(null, "Aucune selection!");
			}
		});

		JPanel panel = new JPanel();
		panel.add(button);
		panel.add(button1);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}

		this.getContentPane().add(panel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public void initCellRenderer() {
		treeCellRenderers[0] = new DefaultTreeCellRenderer();
		treeCellRenderers[0].setClosedIcon(new ImageIcon("image/carre.jpg"));
		treeCellRenderers[0].setOpenIcon(new ImageIcon("image/rond.jpg"));
		treeCellRenderers[0].setLeafIcon(new ImageIcon("image/triangle.jpg"));

		treeCellRenderers[1] = new DefaultTreeCellRenderer();
		treeCellRenderers[1].setClosedIcon(null);
		treeCellRenderers[1].setOpenIcon(null);
		treeCellRenderers[1].setLeafIcon(null);
	}

	public void buldTree() {
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");
		for (int i = 1; i < 12; i++) {
			DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Noeud : " + i);
			if (i % 2 == 0) {
				for (int j = 1; j < 5; j++) {
					DefaultMutableTreeNode rep1 = new DefaultMutableTreeNode("Fichier enfant : " + j);
					for (int k = 1; k < 5; k++)
						rep1.add(new DefaultMutableTreeNode("Sous-Fichier enfant : " + k));
					rep.add(rep1);
				}
			}
			racine.add(rep);
		}
		model = new DefaultTreeModel(racine);
		model.addTreeModelListener(new TreeModelListener() {
			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				System.out.println("la structure d'un noeud a changer");
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				System.out.println("un noueud a ete retirer");
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				System.out.println("un noeud a ete inserer");
			}

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				System.out.println("Changement dans l'arbre");
				Object[] listNoeuds = e.getChildren();
				int[] listIndices = e.getChildIndices();
				for (int i = 0; i < listIndices.length; i++) {
					System.out.println("Index " + listIndices[i] + ", nouvelle valeur : " + listNoeuds[i]);
				}
			}
		});
		arbre = new JTree();
		arbre.setModel(model);
		arbre.setEditable(true);

		this.getContentPane().add(new JScrollPane(arbre));
	}

	public void listRoot() {
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode();
		for (File file : File.listRoots()) {
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
			try {
				for (File nom : file.listFiles()) {
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
					lecteur.add(listFile(nom, node));
				}
			} catch (NullPointerException e) {
			}
			racine.add(lecteur);
		}
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea();
		arbre = new JTree(racine);
		arbre.setRootVisible(false);
		arbre.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
//				if(arbre.getLastSelectedPathComponent() != null) {
//					System.out.println(getAbsolutePath(e.getPath()));
//					System.out.println(arbre.getLastSelectedPathComponent().toString());
//				}
				if (arbre.getLastSelectedPathComponent() != null) {
					File file = new File(getAbsolutePath(e.getPath()));
					Info info = new Info(getAbsolutePath(e.getPath()), file.isFile(), file.canWrite(), file.canRead());
					textArea.setText(info.toString());
				}
			}

			private String getAbsolutePath(TreePath treePath) {
				String str = "";
				for (Object name : treePath.getPath()) {
					if (name.toString() != null)
						str += name.toString();
				}
				return str;
			}
		});
		panel.setBackground(Color.WHITE);
		arbre.setPreferredSize(new Dimension(100, this.getHeight()));
		panel.add(textArea);
		arbre1 = new JTree(racine);
		arbre1.setPreferredSize(new Dimension(100, this.getHeight()));
		arbre1.setCellRenderer(treeCellRenderers[0]);
		arbre2 = new JTree(racine);
		arbre2.setPreferredSize(new Dimension(100, this.getHeight()));
		arbre2.setCellRenderer(treeCellRenderers[1]);
		split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(arbre1), new JScrollPane(arbre2));
		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(arbre), split2);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split1, panel);
		this.getContentPane().add(split);
	}

	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {
		int count = 0;
		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		File[] nom = file.listFiles();
		if (nom == null)
			return new DefaultMutableTreeNode(file.getName() + "\\");
		for (File list : nom) {
			count++;
			if (count < 5) {
				DefaultMutableTreeNode subNode;
				if (list.isDirectory()) {
					subNode = new DefaultMutableTreeNode(list.getName() + "\\");
					node.add(listFile(list, subNode));
				} else
					node.add(new DefaultMutableTreeNode(list.getName()));
			}
		}
		return node;
	}
}

package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;


public class Animation extends JFrame {

	private static final long serialVersionUID = 1L;

	private Panneau pan = new Panneau();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu anim = new JMenu("Animation");
	private JMenu forme = new JMenu("Forme");
	private JMenu aPropos = new JMenu("A propos");
	private JMenu sMenu = new JMenu("Type de forme");
	private JMenuItem lancer = new JMenuItem("Lancer l'animation");
	private JMenuItem arret = new JMenuItem("Arreter l'animation");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem aide = new JMenuItem("?");
	private JRadioButtonMenuItem rond = new JRadioButtonMenuItem("Rond");
	private JRadioButtonMenuItem carre = new JRadioButtonMenuItem("Carre");
	private JRadioButtonMenuItem triangle = new JRadioButtonMenuItem("Triangle");
	private JRadioButtonMenuItem etoile = new JRadioButtonMenuItem("Etoile");
	private JCheckBoxMenuItem morph = new JCheckBoxMenuItem("Morphing");
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenu background = new JMenu("Couleur de fond");
	private JMenu couleur = new JMenu("Couleur de la forme");
	private JMenuItem rouge = new JMenuItem("Rouge"),
			bleu = new JMenuItem("Bleu"),
			vert = new JMenuItem("Vert"),
			backRouge = new JMenuItem("Rouge"),
			backBleu = new JMenuItem("Bleu"),
			backVert = new JMenuItem("Vert");
	private JMenuItem launch = new JMenuItem("Lancer l'animation"),
			stop = new JMenuItem("Arreter l'animation");
	
	private JToolBar jtb = new JToolBar();
	private JButton play = new JButton(new ImageIcon("image/lancer.jpg")),
			cancel = new JButton(new ImageIcon("image/stop.jpg")),
			square = new JButton(new ImageIcon("image/carre.jpg")),
			circle = new JButton(new ImageIcon("image/rond.jpg")),
			tri = new JButton(new ImageIcon("image/triangle.jpg")),
			star = new JButton(new ImageIcon("image/etoile.jpg"));
	
	private RougeAction ract = new RougeAction("Action rouge", new ImageIcon("image/bleu.png"));

	int x, y;
	boolean animated = true;
	boolean backX = false, backY = false;

	public Animation() {
		this.setTitle("animation");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		play.setBackground(Color.WHITE);
		play.setEnabled(false);
		play.addActionListener(new MenuItemListener());
		cancel.setBackground(Color.WHITE);
		cancel.addActionListener(new MenuItem2Listener());
		square.setBackground(Color.WHITE);
		square.addActionListener(new RadioAction());
		circle.setBackground(Color.WHITE);
		circle.addActionListener(new RadioAction());
		tri.setBackground(Color.WHITE);
		tri.addActionListener(new RadioAction());
		star.setBackground(Color.WHITE);
		star.addActionListener(new RadioAction());
		jtb.add(play);
		jtb.add(cancel);
		jtb.add(square);
		jtb.add(circle);
		jtb.add(tri);
		jtb.add(star);
		jtb.add(ract);
		
		launch.setEnabled(false);
		launch.addActionListener(new MenuItemListener());
		stop.addActionListener(new MenuItem2Listener());
		rouge.addActionListener(new CouleurDeFormeListener());
		bleu.addActionListener(new CouleurDeFormeListener());
		vert.addActionListener(new CouleurDeFormeListener());
		backRouge.addActionListener(new CouleurDeFondListener());
		backVert.addActionListener(new CouleurDeFondListener());
		backBleu.addActionListener(new CouleurDeFondListener());
		background.add(backRouge);
		background.add(backBleu);
		background.add(backVert);
		rouge.setEnabled(false);
		couleur.add(rouge);
		couleur.add(bleu);
		couleur.add(vert);
		jpm.add(launch);
		jpm.add(stop);
		jpm.add(couleur);
		jpm.add(background);
		
		pan.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					jpm.show(pan, e.getX(), e.getY());
				}
			}
		});

		lancer.setMnemonic('L');
		lancer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK ));
		lancer.addActionListener(new MenuItemListener());
		lancer.setEnabled(false);
		arret.setMnemonic('A');
		arret.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		arret.addActionListener(new MenuItem2Listener());
		quitter.setAccelerator(KeyStroke.getKeyStroke('x'));
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		anim.setMnemonic('M');
		anim.add(lancer);
		anim.add(arret);
		anim.addSeparator();
		anim.add(quitter);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rond);
		bg.add(carre);
		bg.add(triangle);
		bg.add(etoile);
		rond.setSelected(true);
		rond.addActionListener(new RadioAction());
		carre.addActionListener(new RadioAction());
		triangle.addActionListener(new RadioAction());
		etoile.addActionListener(new RadioAction());
		sMenu.add(rond);
		sMenu.add(carre);
		sMenu.add(triangle);
		sMenu.add(etoile);
		forme.add(sMenu);
		forme.addSeparator();
		morph.addActionListener(new BoxAction());
		forme.add(morph);
		
		aPropos.add(aide);
		
		menuBar.add(anim);
		menuBar.add(forme);
		menuBar.add(aPropos);
		
		this.setJMenuBar(menuBar);
		this.add(jtb, BorderLayout.NORTH);
		this.add(pan, BorderLayout.CENTER);
		this.setVisible(true);
		go();
	}

	private void go() {
		x = pan.getPosX();
		y = pan.getPosY();
		while (animated) {
			if (x < 1)
				backX = false;
			if (x > pan.getWidth() - pan.getDrawSize())
				backX = true;
			if (y < 1)
				backY = false;
			if (y > pan.getHeight() - pan.getDrawSize())
				backY = true;

			if (backX)
				pan.setPosX(--x);
			else
				pan.setPosX(++x);

			if (backY)
				pan.setPosY(--y);
			else
				pan.setPosY(++y);

			pan.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez vous lancer l'animation ?",
					"lancement de l'animation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				animated = true;
				lancer.setEnabled(false);
				launch.setEnabled(false);
				play.setEnabled(false);
				stop.setEnabled(true);
				arret.setEnabled(true);
				cancel.setEnabled(true);
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						go();
					}
				});
				thread.start();
			}
		}

	}

	class MenuItem2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "Voulez vous arretez l'animatoin", "arret de l'animation",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option != JOptionPane.CANCEL_OPTION && option != JOptionPane.CLOSED_OPTION
					&& option != JOptionPane.NO_OPTION) {
				animated = false;
				lancer.setEnabled(true);
				launch.setEnabled(true);
				play.setEnabled(true);
				arret.setEnabled(false);
				stop.setEnabled(false);
				cancel.setEnabled(false);
			}
		}

	}

	class RadioAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem"))
				pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
			else {
				if(e.getSource() == circle)
					rond.doClick();
				if(e.getSource() == square)
					carre.doClick();
				if(e.getSource() == star)
					etoile.doClick();
				if(e.getSource() == tri)
					triangle.doClick();
			}
		}

	}

	class BoxAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (((JCheckBoxMenuItem) e.getSource()).isSelected())
				pan.setMorph(true);
			else
				pan.setMorph(false);
		}

	}
	
	class CouleurDeFormeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((JMenuItem)e.getSource() == rouge) {
				pan.setCouleur(Color.RED);
				backRouge.setEnabled(false);
				backBleu.setEnabled(true);
				backVert.setEnabled(true);
			}
			if ((JMenuItem)e.getSource() == bleu) {
				pan.setCouleur(Color.BLUE);
				backRouge.setEnabled(true);
				ract.setEnabled(false);
				backBleu.setEnabled(false);
				backVert.setEnabled(true);
			}
			if ((JMenuItem)e.getSource() == vert) {
				pan.setCouleur(Color.GREEN);
				backRouge.setEnabled(true);
				backBleu.setEnabled(true);
				backVert.setEnabled(false);
			}
		}
		
	}
	class CouleurDeFondListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if ((JMenuItem)e.getSource() == backRouge) {
				pan.setBackColor(Color.RED);
				rouge.setEnabled(false);
				bleu.setEnabled(true);
				vert.setEnabled(true);
			}
			if ((JMenuItem)e.getSource() == backBleu) {
				pan.setBackColor(Color.BLUE);
				rouge.setEnabled(true);
				bleu.setEnabled(false);
				vert.setEnabled(true);
			}
			if ((JMenuItem)e.getSource() == backVert) {
				pan.setBackColor(Color.GREEN);
				rouge.setEnabled(true);
				bleu.setEnabled(true);
				vert.setEnabled(false);
			}
		}
		
	}
	
	class RougeAction extends AbstractAction{
		private static final long serialVersionUID = 1L;
		
		public RougeAction(String name) {
			super(name);
		}
		
		public RougeAction(String name, ImageIcon img) {
			super(name, img);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			backBleu.doClick();
		}
		
	}
}

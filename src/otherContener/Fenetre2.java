package otherContener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Fenetre2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane onglet;
	private int nbreTab = 0;
	
	public Fenetre2() {
		this.setTitle("Split");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400, 200);
		
		Panneau[] pan = {new Panneau(Color.RED), new Panneau(Color.BLUE), new Panneau(Color.CYAN)};
		
		onglet = new JTabbedPane();
		//onglet = new JTabbedPane(JTabbedPane.LEFT);
		for (int i = 0; i < pan.length; i++) {
			//onglet.add("Onglet N°"+(i+1), pan[i]);
			onglet.addTab("Onglet N°"+(++nbreTab), new ImageIcon("image/carre.jpg"), pan[i]);
		}
		
		JButton ajouter = new JButton("Ajouter un onglet");
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onglet.addTab("Onglet N°"+(++nbreTab), new ImageIcon("image/carre.jpg"), new Panneau(Color.GRAY));
			}
		});
		
		JButton delete = new JButton("Effacer un onglet");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sel = onglet.getSelectedIndex();
				if(sel > -1)
					onglet.remove(sel);
			}
		});
		
		JPanel panel = new JPanel();
		panel.add(ajouter);
		panel.add(delete);
		
		this.getContentPane().add(onglet, BorderLayout.CENTER);
		this.getContentPane().add(panel, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}

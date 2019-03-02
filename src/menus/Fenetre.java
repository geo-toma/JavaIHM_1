package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menu = new JMenu("Fichier");
	private JMenu menu1 = new JMenu("Sous-Fichier");
	private JMenu menu2 = new JMenu("Edition");
	
	private JMenuItem item = new JMenuItem("Ouvrir");
	private JMenuItem item1 = new JMenuItem("Fermer");
	private JMenuItem item2= new JMenuItem("Lancer");
	private JMenuItem item3 = new JMenuItem("Arreter");
	
	private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
	private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");
	
	private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
	private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");
	
	public Fenetre() {
		this.setTitle("Les menus");
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrmi1);
		bg.add(jrmi2);
		
		item1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		menu1.add(jcmi1);
		menu1.add(jcmi2);
		menu1.addSeparator();
		menu1.add(jrmi1);
		menu1.add(jrmi2);
		
		menu.add(item);
		menu.addSeparator();
		menu.add(menu1);
		menu.addSeparator();
		menu.add(item1);
		
		menu2.add(item2);
		//menu2.addSeparator();
		menu2.add(item3);
		
		menuBar.add(menu);
		menuBar.add(menu2);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}
}

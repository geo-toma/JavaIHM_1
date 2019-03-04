package ardoise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	private Panneau pan = new Panneau();
	private int x;
	private int y;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier"),
			forme = new JMenu("Forme du pointeur"),
			couleur = new JMenu("Couleur du pointeur"),
			edition = new JMenu("Edition");
	private JMenuItem erase = new JMenuItem("Effacer"),
			exit = new JMenuItem("Quitter"),
			carre = new JMenuItem("Carre"),
			rond = new JMenuItem("Rond"),
			rouge = new JMenuItem("Rouge"),
			bleu = new JMenuItem("Bleu"),
			vert = new JMenuItem("Vert");
	private JToolBar toolBar = new JToolBar();
	private JButton square = new JButton(new ImageIcon("image/carre.png")),
			circle = new JButton(new ImageIcon("image/rondN.jpg")),
			red = new JButton(new ImageIcon("image/rouge.jpg")),
			blue = new JButton(new ImageIcon("image/bleu.png")),
			green = new JButton(new ImageIcon("image/vert.png"));
	
	public Fenetre() {
		this.setTitle("Ardoise");
		this.setSize(400, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		erase.addActionListener(new GommeListener());
		fichier.add(erase);
		fichier.addSeparator();
		fichier.add(exit);
		carre.setEnabled(false);
		carre.addActionListener(new FormeListener());
		rond.addActionListener(new FormeListener());
		forme.add(carre);
		forme.add(rond);
		rouge.setEnabled(false);
		rouge.addActionListener(new ColorListener());
		bleu.addActionListener(new ColorListener());
		vert.addActionListener(new ColorListener());
		couleur.add(rouge);
		couleur.add(bleu);
		couleur.add(vert);
		edition.add(forme);
		edition.addSeparator();
		edition.add(couleur);
		menuBar.add(fichier);
		menuBar.add(edition);
		
		square.setBackground(Color.WHITE);
		circle.setBackground(Color.WHITE);
		red.setEnabled(false);
		red.addActionListener(new ColorListener());
		blue.addActionListener(new ColorListener());
		green.addActionListener(new ColorListener());
		red.setBackground(Color.WHITE);
		blue.setBackground(Color.WHITE);
		green.setBackground(Color.WHITE);
		square.setEnabled(false);
		square.addActionListener(new FormeListener());
		circle.addActionListener(new FormeListener());
		toolBar.add(square);
		toolBar.add(circle);
		toolBar.addSeparator(new Dimension(30, 0));
		toolBar.add(red);
		toolBar.add(blue);
		toolBar.add(green);
		
		pan.setBackground(Color.WHITE);
		pan.addMouseMotionListener(new MouseTraceListener());
		
		this.setJMenuBar(menuBar);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(pan, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void esquisse() {
		pan.setX(x);
		pan.setY(y);
		pan.repaint();
	}
	
	class MouseTraceListener implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			pan.setFillWay(true);
			pan.setRep(false);
			x = e.getX();
			y = e.getY();
			esquisse();
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			pan.setFillWay(false);
			pan.setRep(true);
			x = e.getX();
			y = e.getY();
			esquisse();
		}
	}
	
	class ColorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == red || e.getSource() == rouge) {
				pan.setCouleur(Color.RED);
				red.setEnabled(false);
				rouge.setEnabled(false);
				blue.setEnabled(true);
				bleu.setEnabled(true);
				vert.setEnabled(true);
				green.setEnabled(true);
			}
			if(e.getSource() == blue || e.getSource() == bleu) {
				pan.setCouleur(Color.BLUE);
				red.setEnabled(true);
				rouge.setEnabled(true);
				blue.setEnabled(false);
				bleu.setEnabled(false);
				vert.setEnabled(true);
				green.setEnabled(true);
			}
			if(e.getSource() == green || e.getSource() == vert) {
				pan.setCouleur(Color.GREEN);
				red.setEnabled(true);
				rouge.setEnabled(true);
				blue.setEnabled(true);
				bleu.setEnabled(true);
				vert.setEnabled(false);
				green.setEnabled(false);
			}
		}
	}
	
	class FormeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == carre || e.getSource() == square) {
				pan.setSquare(true);
				carre.setEnabled(false);
				square.setEnabled(false);
				rond.setEnabled(true);
				circle.setEnabled(true);
			}
			if (e.getSource() == rond || e.getSource() == circle) {
				pan.setSquare(false);
				carre.setEnabled(true);
				square.setEnabled(true);
				rond.setEnabled(false);
				circle.setEnabled(false);
			}
		}
	}
	
	class GommeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pan.setCouleur(Color.WHITE);
			red.setEnabled(true);
			rouge.setEnabled(true);
			blue.setEnabled(true);
			bleu.setEnabled(true);
			vert.setEnabled(true);
			green.setEnabled(true);
		}
	}

}

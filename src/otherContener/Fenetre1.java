package otherContener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Fenetre1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textArea);
	
	public Fenetre1() {
		this.setTitle("Split");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(200, 200);
		
		JButton bouton = new JButton("Afficher");
		bouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Le texte ecris dans le textArea");
				System.out.println("-------------------------------");
				System.out.println(textArea.getText());
			}
		});
		
		//this.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		this.getContentPane().add(bouton, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}

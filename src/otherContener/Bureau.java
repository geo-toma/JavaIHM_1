package otherContener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class Bureau extends JFrame {

	private static final long serialVersionUID = 1L;
	private int nbreFenetre = 0;
	private JDesktopPane desktop = new JDesktopPane();
	private static int XY = 10;
	
	public Bureau() {
		this.setTitle("Split");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400, 300);
		
		JButton ajouter = new JButton("Ajouter une fenetre");
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nbreFenetre++;
				XY += 2;
				desktop.add(new MiniFenetre(nbreFenetre), nbreFenetre);
			}
		});
		
		this.getContentPane().add(desktop, BorderLayout.CENTER);
		this.getContentPane().add(ajouter, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	
	class MiniFenetre extends JInternalFrame{
		private static final long serialVersionUID = 1L;
		public MiniFenetre(int nbr) {
			this.setTitle("Fenetre N°"+nbr);
			this.setClosable(true);
			this.setResizable(true);
			this.setSize(150, 80);
			this.setLocation(XY, XY);
			this.setVisible(true);
		}
	}

}

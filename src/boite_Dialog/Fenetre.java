package boite_Dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("appel a la zodiaque");
	
	public Fenetre() {
		this.setTitle("Ma fenetre");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setContentPane(button);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZDialog zd = new ZDialog(null, "perso dialog", true);
				ZDialogInfo zInfo = zd.showDialogInfo();
				JOptionPane.showMessageDialog(null, zInfo.toString(), "Infos Personage", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		this.setVisible(true);
	}

}

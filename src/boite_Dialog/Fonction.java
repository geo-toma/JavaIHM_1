package boite_Dialog;

import javax.swing.JOptionPane;

public class Fonction {

	public Fonction() {
//		JOptionPane jop1, jop2, jop3;
//		jop1 = new JOptionPane();
//		jop1.showMessageDialog(null, "Message informatif", "Information", JOptionPane.INFORMATION_MESSAGE);
//		jop2 = new JOptionPane();
//		jop2.showMessageDialog(null, "Message préventif", "Attention", JOptionPane.WARNING_MESSAGE);
//		jop3 = new JOptionPane();
//		jop3.showMessageDialog(null, "Message d'erreur", "Erreur", JOptionPane.ERROR_MESSAGE);

		// Boîte du message d'information
		// ImageIcon img = new ImageIcon("image/info.png");
		// JOptionPane.showMessageDialog(null, "Message informatif", "Information",
		// JOptionPane.INFORMATION_MESSAGE, img);

		// Boîte du message préventif
		// img = new ImageIcon("image/warning.jpg");
		// JOptionPane.showMessageDialog(null, "Message préventif", "Attention",
		// JOptionPane.WARNING_MESSAGE, img);

		// Boîte du message d'erreur
		// img = new ImageIcon("image/erreur.jpg");
		// JOptionPane.showMessageDialog(null, "Message d'erreur", "Erreur",
		// JOptionPane.ERROR_MESSAGE, img);

		// entrez du texte
		// String nom = JOptionPane.showInputDialog(null, "Votre nom !", "name",
		// JOptionPane.QUESTION_MESSAGE);
		// JOptionPane.showMessageDialog(null, "Votre nom est : "+nom, "name",
		// JOptionPane.INFORMATION_MESSAGE);

		// choix dans une liste deroulante
		String[] sexe = { "Masculin", "Feminin", "Indeterminer" };
		// String nom = (String) JOptionPane.showInputDialog(null, "Chois du sexe",
		// "Sexe", JOptionPane.QUESTION_MESSAGE, null, sexe, sexe[2]);
		// JOptionPane.showMessageDialog(null, "Votre sexe est : "+nom, "Sexe",
		// JOptionPane.INFORMATION_MESSAGE);
		// Autre alternative mais avec des boutons
		int option = JOptionPane.showOptionDialog(null, "Chois du sexe", "Sexe", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, sexe, sexe[2]);
		JOptionPane.showMessageDialog(null, "Votre sexe est : "+sexe[option], "Sexe",
				JOptionPane.INFORMATION_MESSAGE);
	}

}

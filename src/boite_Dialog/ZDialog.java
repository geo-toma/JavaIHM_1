package boite_Dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ZDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private ZDialogInfo zinfo = new ZDialogInfo();
	private JLabel nomLabel, sexeLabel, cheveuxLabel, tailleLabel, taillle1Label, icon;
	private JTextField nomField, tailleField;
	private JComboBox<String> sexeBox, cheveuxBox;
	private JRadioButton ageButton1, ageButton2, ageButton3, ageButton4;
	
	public ZDialog(JFrame frame ,String name ,boolean modal) {
		super(frame, name, modal);
		this.setSize(600, 270);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.initComponent();
	}

	public ZDialogInfo showDialogInfo() {
		this.setVisible(true);
		return zinfo;
	}
	
	private void initComponent() {
		// L'icon
		icon = new JLabel(new ImageIcon("image/icon.jpg"));
		JPanel iconPanel = new JPanel();
		iconPanel.setBackground(Color.WHITE);
		iconPanel.add(icon);
		
		// Le nom
		nomLabel = new JLabel("Saisir un nom : ");
		nomField = new JTextField();
		nomField.setPreferredSize(new Dimension(100, 25));
		JPanel nomPanel = new JPanel();
		nomPanel.setPreferredSize(new Dimension(220, 60));
		nomPanel.setBackground(Color.WHITE);
		nomPanel.setBorder(BorderFactory.createTitledBorder("Nom du personage"));
		nomPanel.add(nomLabel);
		nomPanel.add(nomField);
		
		// Le sexe
		sexeLabel = new JLabel("Sexe : ");
		sexeBox = new JComboBox<String>();
		sexeBox.addItem("Masculin");
		sexeBox.addItem("Feminin");
		sexeBox.addItem("Indeterminer");
		JPanel sexePanel = new JPanel();
		sexePanel.setBackground(Color.WHITE);
		sexePanel.setPreferredSize(new Dimension(220, 60));
		sexePanel.setBorder(BorderFactory.createTitledBorder("Sexe du personage"));
		sexePanel.add(sexeLabel);
		sexePanel.add(sexeBox);
		
		// L'age
		ageButton1 = new JRadioButton("15 - 25 ans");
		ageButton1.setSelected(true);
		ageButton2 = new JRadioButton("26 - 35 ans");
		ageButton3 = new JRadioButton("36 - 50 ans");
		ageButton4 = new JRadioButton("+ de 50 ans");
		ButtonGroup bg = new ButtonGroup();
		bg.add(ageButton1);
		bg.add(ageButton2);
		bg.add(ageButton3);
		bg.add(ageButton4);
		JPanel agePanel = new JPanel();
		agePanel.setBackground(Color.WHITE);
		agePanel.setPreferredSize(new Dimension(440, 60));
		agePanel.setBorder(BorderFactory.createTitledBorder("Age du personage"));
		agePanel.add(ageButton1);
		agePanel.add(ageButton2);
		agePanel.add(ageButton3);
		agePanel.add(ageButton4);
		
		// La taille
		tailleLabel = new JLabel("Taille : ");
		taillle1Label = new JLabel("cm");
		tailleField = new JTextField();
		tailleField.setPreferredSize(new Dimension(90, 25));
		JPanel taillePanel = new JPanel();
		taillePanel.setBackground(Color.WHITE);
		taillePanel.setPreferredSize(new Dimension(220, 60));
		taillePanel.setBorder(BorderFactory.createTitledBorder("Taille du personage"));
		taillePanel.add(tailleLabel);
		taillePanel.add(tailleField);
		taillePanel.add(taillle1Label);
		
		// Les cheveux
		cheveuxLabel = new JLabel("Cheveux : ");
		cheveuxBox = new JComboBox<String>();
		cheveuxBox.addItem("Blond");
		cheveuxBox.addItem("Brun");
		cheveuxBox.addItem("Roux");
		cheveuxBox.addItem("Blanc");
		JPanel cheveuxPanel = new JPanel();
		cheveuxPanel.setBackground(Color.WHITE);
		cheveuxPanel.setPreferredSize(new Dimension(220, 60));
		cheveuxPanel.setBorder(BorderFactory.createTitledBorder("Couleur de cheveux du personage"));
		cheveuxPanel.add(cheveuxLabel);
		cheveuxPanel.add(cheveuxBox);
		
		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.add(nomPanel);
		content.add(sexePanel);
		content.add(agePanel);
		content.add(taillePanel);
		content.add(cheveuxPanel);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zinfo = new ZDialogInfo(getNom(), (String)sexeBox.getSelectedItem(), getAge(), (String)cheveuxBox.getSelectedItem(),
						getTaille());
				setVisible(false);
			}

			private String getTaille() {
				return (tailleField.getText().equals(""))? "180" : tailleField.getText();
			}

			private String getAge() {
				return (ageButton1.isSelected())? ageButton1.getText() :
					   (ageButton2.isSelected())? ageButton2.getText() :
					   (ageButton3.isSelected())? ageButton3.getText() :
					   (ageButton4.isSelected())? ageButton4.getText() :
						   ageButton1.getText();
			}

			private String getNom() {
				return (nomField.getText().equals(""))? "Noname" : nomField.getText();
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JPanel control = new JPanel();
		control.add(okButton);
		control.add(cancelButton);
		
		this.getContentPane().add(iconPanel, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		
	}

}

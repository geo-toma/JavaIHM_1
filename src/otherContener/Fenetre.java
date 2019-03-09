package otherContener;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSplitPane split;
	private JSplitPane split1;
	private JSplitPane split3;
	
	public Fenetre() {
		this.setTitle("Split");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(200, 200);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.YELLOW);
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.BLUE);
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.GREEN);
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel2, panel3);
		split.setOneTouchExpandable(true);
		split.setDividerSize(10);
		split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel);
		split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, split, split1);
		
		this.getContentPane().add(split3, BorderLayout.CENTER);
		this.setVisible(true);
	}

}

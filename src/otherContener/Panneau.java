package otherContener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color color = Color.WHITE;
	private static int COUNT = 0;
	private String msg = "";
	
	public Panneau(Color color) {
		this.color = color;
		this.msg = "Panneau N°"+(++COUNT);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString(msg, 10, 20);
	}
}

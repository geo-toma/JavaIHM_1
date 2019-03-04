package ardoise;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;
	private int x = -5;
	private int y = -5;
	private boolean fillWay = false;
	private boolean rep = true;
	private boolean isSquare = true;
	private Color couleur = Color.RED;
	private List<Integer> listX = new ArrayList<>();
	private List<Integer> listY = new ArrayList<>();
	private List<Color> list = new ArrayList<>();
	private List<Boolean> listB = new ArrayList<>();
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		if(!fillWay) 
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(couleur);
		if(isSquare)
			g.fillRect(x - 5, y - 5, 10, 10);
		else
			g.fillOval(x - 5, y - 5, 10, 10);
		if (fillWay) {
			listX.add(x - 5);
			listY.add(y - 5);
			list.add(couleur);
			listB.add(isSquare);
		}
		if(rep)
			dessiner(g);
		rep = true;
	}
	
	public void dessiner(Graphics g) {
		for (int i = 0; i < list.size(); i++) {
			g.setColor(list.get(i));
			if(listB.get(i))
		    	g.fillRect(listX.get(i), listY.get(i), 10, 10);
			else
				g.fillOval(listX.get(i), listY.get(i), 10, 10);
		}
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setFillWay(boolean fillWay) {
		this.fillWay = fillWay;
	}

	public void setRep(boolean rep) {
		this.rep = rep;
	}

	public void setSquare(boolean isSquare) {
		this.isSquare = isSquare;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

}

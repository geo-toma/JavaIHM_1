package menus;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	private static final long serialVersionUID = 1L;
	private int posX = -50;
	private int posY = -50;
	private int drawSize = 50;
	private boolean morph = false, reduce = false;
	private String forme = "Rond";
	private int increment = 0;
	private Color couleur = Color.RED,
			backColor = Color.WHITE;

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(backColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(couleur);
		if (morph)
			drawMorph(g);
		else
			draw(g);
	}

	private void draw(Graphics g) {
		if (forme.equals("Rond"))
			g.fillOval(posX, posY, drawSize, drawSize);
		if (forme.equals("Carre"))
			g.fillRect(posX, posY, drawSize, drawSize);
		if (forme.equals("Triangle")) {
			int[] X = { posX + drawSize / 2, posX, posX + drawSize };
			int[] Y = { posY, posY + drawSize, posY + drawSize };
			g.fillPolygon(X, Y, 3);
		}
		if (forme.equals("Etoile")) {
			int[] X = { posX + drawSize / 2, posX + 3 * drawSize / 8, posX, posX + drawSize / 4, posX,
					posX + drawSize / 2, posX + drawSize, posX + 3 * drawSize / 4, posX + drawSize,
					posX + 5 * drawSize / 8 };
			int[] Y = { posY, posY + drawSize / 4, posY + drawSize / 4, posY + drawSize / 2, posY + drawSize,
					posY + 5 * drawSize / 8, posY + drawSize, posY + drawSize / 2, posY + drawSize / 4,
					posY + drawSize / 4 };
			g.fillPolygon(X, Y, 10);
		}
	}

	private void drawMorph(Graphics g) {
		increment++;
		if (drawSize >= 50)
			reduce = true;
		if (drawSize <= 10)
			reduce = false;

		if (reduce)
			drawSize -= getUsedSize();
		else
			drawSize += getUsedSize();

		if (forme.equals("Rond"))
			g.fillOval(posX, posY, drawSize, drawSize);
		if (forme.equals("Carre"))
			g.fillRect(posX, posY, drawSize, drawSize);
		if (forme.equals("Triangle")) {
			int[] X = { posX + drawSize / 2, posX, posX + drawSize };
			int[] Y = { posY, posY + drawSize, posY + drawSize };
			g.fillPolygon(X, Y, 3);
		}
		if (forme.equals("Etoile")) {
			int[] X = { posX + drawSize / 2, posX + 3 * drawSize / 8, posX, posX + drawSize / 4, posX,
					posX + drawSize / 2, posX + drawSize, posX + 3 * drawSize / 4, posX + drawSize,
					posX + 5 * drawSize / 8 };
			int[] Y = { posY, posY + drawSize / 4, posY + drawSize / 4, posY + drawSize / 2, posY + drawSize,
					posY + 5 * drawSize / 8, posY + drawSize, posY + drawSize / 2, posY + drawSize / 4,
					posY + drawSize / 4 };
			g.fillPolygon(X, Y, 10);
		}
	}

	public int getUsedSize() {
		int res = 0;
		if (increment / 10 == 1) {
			increment = 0;
			res = 1;
		}
		return res;
	}

	public void setMorph(boolean morph) {
		this.morph = morph;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}

	public void setDrawSize(int drawSize) {
		this.drawSize = drawSize;
	}

	public int getDrawSize() {
		return drawSize;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}
}

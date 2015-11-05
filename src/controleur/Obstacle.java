package controleur;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import modele.Coordonnees;

public class Obstacle {
	private Coordonnees c; //Centre
	private int diametre = new Random().nextInt(30)+20; // diametre entre 20 et 50
	private Color color;
	private int angle = -180;
	
	public Obstacle(Coordonnees c, Color color) {
		this.c = c;
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setC(Coordonnees c) {
		this.c = c;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * This function draws an obstacle.
	 * @param g
	 */
	public void draw(Graphics g) { //Dessine l'obstacle
		g.setColor(this.color);
		g.drawArc((int) this.c.getX()-diametre/2,(int) this.c.getY()-diametre/2, diametre, diametre, 0, 360);
		g.setColor(Color.black);
		g.drawArc((int) this.c.getX()-diametre/4, (int) this.c.getY()-diametre/4, diametre/4, diametre/4, 0, 360);
		g.drawArc((int) this.c.getX()-diametre/4+(2*diametre/6), (int) this.c.getY()-diametre/4, diametre/4, diametre/4, 0, 360);
		g.drawArc((int) this.c.getX()-diametre/9, (int) this.c.getY()+(diametre/20),diametre/4, diametre/4, 0, this.angle);
		System.out.println();
	}
	
	@Override
	public String toString() { 
		return "x : " + (int) this.c.getX() + ", y : " + (int) this.c.getY() + ", dimension : " + this.diametre;
	}
	
	public int getDiametre() {
		return diametre;
	}
	
	public Coordonnees getC() {
		return c;
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public int getAngle() {
		return angle;
	}
}

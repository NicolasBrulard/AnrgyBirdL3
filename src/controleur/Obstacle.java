package controleur;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import vue.FenetreContener;
import modele.Coordonnees;

public class Obstacle {
	private Coordonnees c; //Centre
	private int diametre = new Random().nextInt(30)+20; // diametre entre 20 et 50
	private int longueur = new Random().nextInt(30)+20;
	private int largeur = new Random().nextInt(30)+20;
	private int forme = 0; //new Random().nextInt(2);
	private FenetreContener fenetre;
	private Color color;
	private int angle = -180;
	
	public Obstacle(Coordonnees c, Color color, FenetreContener fenetre) {
		this.c = c;
		this.color = color;
		this.fenetre = fenetre;
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
		if(forme == 0){
			g.setColor(this.color);
			g.drawArc((int) this.c.getX()-diametre/2,(int) this.c.getY()-diametre/2, diametre, diametre, 0, 360);
			g.setColor(Color.black);
			g.drawArc((int) this.c.getX()-diametre/4, (int) this.c.getY()-diametre/4, diametre/4, diametre/4, 0, 360);
			g.drawArc((int) this.c.getX()-diametre/4+(2*diametre/6), (int) this.c.getY()-diametre/4, diametre/4, diametre/4, 0, 360);
			g.drawArc((int) this.c.getX()-diametre/9, (int) this.c.getY()+(diametre/20),diametre/4, diametre/4, 0, this.angle);
		}
		if(forme == 1){
			g.setColor(this.color);
			g.drawRect((int) this.c.getX()-largeur, (int) this.c.getY()-longueur, longueur, largeur);
			g.setColor(Color.black);
			//g.drawArc((int) this.c.getX()-largeur+5, (int) this.c.getY()-longueur+5, longueur/4, largeur/4, 0, 360);
			//g.drawArc((int) this.c.getX()-largeur+(2*largeur/6), (int) this.c.getY()-longueur+5, longueur/4, largeur/4, 0, 360);
			//g.drawLine((int) this.c.getX()-largeur+5, (int) this.c.getY()-longueur+longueur-5, (int) this.c.getX()-largeur+largeur-5, (int) this.c.getY()-longueur+longueur-5);
		}
		//System.out.println();
	}
	
	
	public void deplaceObstGtD(){
		this.c.setX(this.c.getT()+this.fenetre.getWidth()/2);
		//this.c.setY(-this.c.getT()*this.c.getT()+this.fenetre.getWidth()/2);
		this.c.setT(this.c.getT()+0.2);
	}
	
	public void deplaceObstDtG(){
		this.c.setX(-this.c.getT()+this.fenetre.getWidth()-50);
		//this.c.setY(-this.c.getT()*this.c.getT()+this.fenetre.getWidth()/2);
		this.c.setT(this.c.getT()+0.2);
	}
	
	public void deplaceObstCercle(){
		//if(this.c.getX() > this.fenetre.getWidth()-20){
			
		//}
		this.c.setX(50*Math.cos(this.c.getT())+this.fenetre.getWidth()/2);
		this.c.setY(50*Math.sin(this.c.getT())+this.fenetre.getWidth()/2);
		this.c.setT(this.c.getT()+0.025);
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
	public void setDiametre(int diametre) {
		this.diametre = diametre;
	}
}

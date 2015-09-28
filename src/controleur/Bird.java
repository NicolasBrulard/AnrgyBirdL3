package controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import modele.Constantes;
import modele.Coordonnees;

public class Bird{ //Mon petit oiseau :D
	private ArrayList<Coordonnees> liste = new ArrayList<>(); //Permet de récupérer tous les centres par lesquelles l'oiseau est passé
	private Coordonnees coord; //centre
	private Color color;
	private int coeff;
	
	public Bird(Coordonnees coord, Color color) {
		this.coord = coord;
		this.color = color;
		this.coeff = new Random().nextInt(20)+1;
	}
	
	public void drawBird(Graphics g){ //dessine l'oiseau
		g.setColor(this.color);
        g.drawOval((int) this.coord.getX()-Constantes.rayonBird, (int) (this.coord.getY()-Constantes.rayonBird), Constantes.rayonBird*2, Constantes.rayonBird*2);
	}
	
	public void deplace(){ //Déplace les x et y selon le temps (T), Parabole codé en dur à changer en aléatoire !
		this.coord.setX(this.coeff*this.coord.getT()+250);
		this.coord.setY(this.coord.getT()*this.coord.getT());
		this.coord.setT(this.coord.getT()+0.1); // Varier le + pour faire varier la fréquence de point. >0
		/*this.coord.setX(this.coeff*this.coord.getT()+250);
		this.coord.setY((this.coord.getT()*this.coord.getT()));
		this.coord.setT(this.coord.getT()+0.1); // Varier le + pour faire varier la fréquence de point. >0*/
		this.liste.add(this.coord); //Permet d'ajouter la nouvelle coordonné à la liste
		try {
			Thread.sleep(10);
		} catch (Exception e) {

		}
	}
	
	public void ajouteListe(Coordonnees c){
		this.liste.add(c);
	}
	
	
	
	@Override
	public String toString() {
		return "x : " + this.coord.getX() + ", y : " + this.coord.getY();
	}
	
	public Coordonnees getCoord() {
		return coord;
	}
	
	public ArrayList<Coordonnees> getListe() {
		return liste;
	}
	
	public void drawCentre(Graphics g){
		for (Coordonnees coord : liste) {
			g.drawLine((int )coord.getX(),(int) coord.getY(), (int )coord.getX(), (int ) coord.getY());
		}
	}
}

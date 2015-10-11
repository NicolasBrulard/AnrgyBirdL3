package controleur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import vue.FenetreContener;
import modele.Constantes;
import modele.Coordonnees;

public class Bird{ //Mon petit oiseau :D
	private ArrayList<Coordonnees> liste = new ArrayList<>(); //Permet de r�cup�rer tous les centres par lesquelles l'oiseau est pass�
	private Coordonnees coord; //centre
	private Color color;
	private int coeff;
	private int nbCourbe;
	private int coeffAlea; 
	private boolean changement = true;
	private FenetreContener fenetre;
	
	public Bird(Coordonnees coord, Color color, FenetreContener fenetre) {
		this.coord = coord;
		this.color = color;
		this.coeffAlea = 5;
		this.coeff = new Random().nextInt(this.coeffAlea);
		this.nbCourbe = new Random().nextInt(3);
		this.changement = true;
		this.fenetre = fenetre;
	}
	
	public void drawBird(Graphics g){ //dessine l'oiseau
		g.setColor(this.color);
        g.drawOval((int) this.coord.getX()-Constantes.rayonBird, (int) (this.coord.getY()-Constantes.rayonBird), Constantes.rayonBird*2, Constantes.rayonBird*2);
	}
	
	public void deplace(){ //D�place les x et y selon le temps (T), Parabole cod� en dur � changer en al�atoire !
		this.changement(this.nbCourbe);
		if(this.nbCourbe==0){
		this.coord.setX(this.coeff*this.coord.getT()+this.fenetre.getWidth()/2);
		this.coord.setY(this.coord.getT()*this.coord.getT());
		this.coord.setT(this.coord.getT()+0.1); // Varier le + pour faire varier la fr�quence de point. >0
		}
		else if(this.nbCourbe==1){
			this.coord.setX((this.coeff*Math.cos(this.coord.getT())+this.fenetre.getWidth()/2)/2);
			this.coord.setY((this.coeff++*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2)/2);
			this.coord.setT(this.coord.getT()+0.04);
		}
		else if(this.nbCourbe==2){
			this.coord.setX(this.coeff*this.coord.getT());
			this.coord.setY(this.coeff*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setT(this.coord.getT()+0.1);
		}
		else if(this.nbCourbe==3){
			this.coord.setX(50*this.coeff*Math.cos(5*this.coord.getT())*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setY(50*this.coeff*Math.cos(5*this.coord.getT())*Math.cos(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setT(this.coord.getT()+0.1);
		}
		this.liste.add(this.coord); //Permet d'ajouter la nouvelle coordonn� � la liste
		try {
			Thread.sleep(10);
		} catch (Exception e) {

		}
	}
	
	public void ajouteListe(Coordonnees c){
		this.liste.add(c);
	}
	
	public void changement(int c){
		if(this.changement){
			switch (c) {
			case 0:
				this.coeffAlea = 40;
				this.coeff += 10;
				break;
				
			case 1:
				this.coeffAlea = 20;
				this.coeff += 10;
				break;
				
			case 2:
				this.coeffAlea = 10;
				this.coeff += 5;
				break;

			default:
				break;
			}
			this.changement = false;
		}
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
	
	public void drawBec(Graphics g){
		int x = 0;
		int y = 0;
		switch (this.nbCourbe) {
		case 0:
			x = (int) (this.coeff + this.getCoord().getX());
			y = (int) (2* this.coord.getT()+this.getCoord().getY());
			break;
			
		case 1:
			x = (int) (-this.coeff*Math.sin(this.coord.getT()) + this.getCoord().getX());
			y = (int) (this.coeff*Math.cos(this.coord.getT()) +this.getCoord().getY());
			break;
			
		case 2:
			x = (int) (this.coeff + this.getCoord().getX());
			y = (int) (this.coeff*Math.cos(this.coord.getT()) +this.getCoord().getY());
			break;
			
		case 3:
			x = (int) (-this.coeff*Math.sin(this.coord.getT())*Math.cos(this.coord.getT()) + this.getCoord().getX());
			y = (int) (this.coeff*Math.sin(this.coord.getT())*Math.sin(this.coord.getT()) +this.getCoord().getY());
			break;

		default:
			break;
		}
		g.drawLine((int) this.getCoord().getX(),(int)this.getCoord().getY(),(int) x, (int) y);
	}
}

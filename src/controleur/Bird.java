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
	private int temp = 5;
	
	public Bird(Coordonnees coord, Color color, FenetreContener fenetre) {
		this.coord = coord;
		this.color = color;
		this.coeffAlea = 5;
		this.coeff = new Random().nextInt(this.coeffAlea);
		this.nbCourbe =new Random().nextInt(4);
		this.nbCourbe = new Random().nextInt(3);
		this.changement = true;
		this.fenetre = fenetre;
	}
	
	/**
	 * This function draw the bird, according to the color in the constructor
	 * @param Graphics
	 */
	public void drawBird(Graphics g){ //dessine l'oiseau
		g.setColor(this.color);
        g.drawOval((int) this.coord.getX()-Constantes.rayonBird, (int) (this.coord.getY()-Constantes.rayonBird), Constantes.rayonBird*2, Constantes.rayonBird*2);
	}
	
	/**
	 * This function moves the bird point to point according to the values x and y
	 */
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
			this.coord.setT(this.coord.getT()+0.02);
		}
		else if(this.nbCourbe==2){
			this.coord.setX(this.coeff*this.coord.getT());
			this.coord.setY(this.coeff*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setT(this.coord.getT()+0.1);
		}
		else if(this.nbCourbe==3){
			this.coord.setX(12*this.coeff*Math.cos(5*this.coord.getT())*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setY(12*this.coeff*Math.cos(5*this.coord.getT())*Math.cos(this.coord.getT())+this.fenetre.getWidth()/2);
			this.coord.setT(this.coord.getT()+0.01);
		}
		/*if(this.nbCourbe==0){
			this.coord.setX(this.coeff*this.coord.getT()+this.fenetre.getWidth()/2);
			this.coord.setY(this.coord.getT()*this.coord.getT());
			this.coord.setT(this.coord.getT()+0.1); // Varier le + pour faire varier la fr�quence de point. >0
			this.temp = 10;
			}
			else if(this.nbCourbe==1){
				this.coord.setX((this.coeff*Math.cos(this.coord.getT())+this.fenetre.getWidth()/2)/2);
				this.coord.setY((this.coeff++*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2)/2);
				this.coord.setT(this.coord.getT()+0.1);
				this.temp = 20;
			}
			else if(this.nbCourbe==2){
				this.coord.setX(this.coeff*this.coord.getT());
				this.coord.setY(this.coeff*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
				this.coord.setT(this.coord.getT()+0.1);
				this.temp = 10;
			}
			else if(this.nbCourbe==3){
				this.coord.setX(12*this.coeff*Math.cos(5*this.coord.getT())*Math.sin(this.coord.getT())+this.fenetre.getWidth()/2);
				this.coord.setY(12*this.coeff*Math.cos(5*this.coord.getT())*Math.cos(this.coord.getT())+this.fenetre.getWidth()/2);
				this.coord.setT(this.coord.getT()+0.1);
				this.temp = 100;
			}*/
		this.liste.add(this.coord); //Permet d'ajouter la nouvelle coordonn� � la liste
		
	}
	
	/**
	 * This function add the point the bird went on to the list of every point he went on
	 * @param Coordinates
	 */
	public void ajouteListe(Coordonnees c){
		this.liste.add(c);
	}
	
	/**
	 * Switch the value of an Integer 
	 * @param c
	 */
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
			case 3:
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
	
	/**
	 * This function
	 * @param g
	 */
	public void drawCentre(Graphics g){
		for (Coordonnees coord : liste) {
			g.drawLine((int )coord.getX(),(int) coord.getY(), (int )coord.getX(), (int ) coord.getY());
		}
	}
	
	/**
	 * This function draw the bird's mouth
	 * @param g
	 */
	public void drawBec(Graphics g){
		int x = 0;
		int y = 0;
		
		float X = (int) this.getCoord().getX();
		float Y = (int) this.getCoord().getY();
		
		float a = 0;
		float b = 0;
		float Ax = 0;
		float Bx = 0;
		float Cx = 0;
		
		float l = Constantes.rayonBird*(2);
		float delta = 0;
		
		int xl = 0;
		int yl = 0;
		
		int alphaDegree = 90;
		
		int xc1 = 0;
		int xc2 = 0;
		int yc1 = 0;
		int yc2 = 0;
		
		// dérivé du déplacement, c'est x et y représentent la tangente (et la vitesse?)
		switch (this.nbCourbe) {
		case 0:

			//x = (int) (this.coeff + this.getCoord().getX());
			//y = (int) (2* this.coord.getT()+this.getCoord().getY());
			x = (int) ((this.coeff + X));
			y = (int) ((2* this.coord.getT()+Y));

			break;
			
		case 1:
			x = (int) (-this.coeff*Math.sin(this.coord.getT()) + X);
			y = (int) (this.coeff*Math.cos(this.coord.getT()) + Y);
			break;
			
		case 2:
			x = (int) (this.coeff + X);
			y = (int) (this.coeff*Math.cos(this.coord.getT()) + Y);
			break;
			
		case 3:
			/*x = (int) (-this.coeff*Math.sin(this.coord.getT())*Math.cos(this.coord.getT()) + X);
			y = (int) (this.coeff*Math.sin(this.coord.getT())*Math.sin(this.coord.getT()) + Y);*/
			x = (int) (12*this.coeff*(3*Math.cos(6*X)-2*Math.cos(4*X)));
			y = (int) (-12*this.coeff*(2*Math.sin(4*X)+3*Math.sin(6*X)));

			break;

		default:
			break;
		}
		
		/* Explication rapide :
		 * Y = aX + b
		 * y = ay +b
		 * a = (y-Y)/(X-x)
		 * b = Y - aX
		 * 
		 * l^2 = (xx - X)^2 + (yy - Y)^2
		 * 
		 * A(xx)^2 + Bxx + C = 0
		 * Avec :
		 * A = 1+a^2
		 * B = -2X + 2ab - 2aY
		 * C = X^2 + b^2 - 2bY + Y^2 - l^2
		 * 
		 * xx = (-b+racine(delta))/2A)
		 */
		
		a = (y - Y)/(x - X);
		//System.out.println("a : " +a);
		b = Y - a*X;
		//System.out.println("b : " +b);
		
		Ax = 1 + a*a;
		//System.out.println("A : " + Ax);
		Bx = -2*X + 2*a*b - 2*a*Y;
		//System.out.println("B : " + Bx);
		Cx = X*X + b*b - 2*b*Y + Y*Y - l*l;
		//System.out.println("C :" + Cx);
		
		delta = Bx*Bx - 4*Ax*Cx;
		//System.out.println("delta : " + delta);
		
		if(X < x){
			xl = (int) ((-Bx+Math.sqrt(delta))/(2*Ax));
			yl = (int) ((a*(-Bx+Math.sqrt(delta))/(2*Ax)) + b);
		}else{
			xl = (int) ((-Bx-Math.sqrt(delta))/(2*Ax));
			yl = (int) ((a*(-Bx-Math.sqrt(delta))/(2*Ax)) + b);
		}
		g.drawLine((int) X,(int) Y, xl, yl);
		
		//System.out.println("\nx : " + X + " y : " + Y);
		//System.out.println("\nxl : " + (int) (-Bx+Math.sqrt(delta)/(2*Ax)) + " yl : " + (int) (a*(-Bx+Math.sqrt(delta)/(2*Ax)) + b));
		
		/*
		xc1 = (int) X;//((X-xl)*Math.cos(alphaDegree));
		yc1 = (int) Y+20;//((Y-yl)*Math.sin(alphaDegree));
		xc2 = (int) X;
		yc2 = (int) Y-20;
		
		int xPoly[] = {xc1, xc2, xl};
		int yPoly[] = {yc1, yc2, yl};
		
		g.drawPolygon(xPoly, yPoly, 3);
		*/
	}
	
	public int getNbCourbe() {
		return nbCourbe;
	}
}

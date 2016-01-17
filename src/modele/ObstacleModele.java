package modele;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class ObstacleModele extends Observable {
	
	private Color color = Color.GREEN;
	private CoordonneesModele coord = new CoordonneesModele(0, 0);
	private int rayon = 20;
	private int masse = 100;
	private String f;
	private VecteurModele vitesse;
	private VecteurModele acceleration = new VecteurModele(0, -Constantes.g);

	public VecteurModele getAcceleration() {
		return acceleration;
	}
	
	public int getMasse() {
		return masse;
	}
	
	public void setMasse(int masse) {
		this.masse = masse;
	}
	
	public void setAcceleration(VecteurModele acceleration) {
		this.acceleration = acceleration;
	}

	public ObstacleModele(int x,int y, String f, VecteurModele vitesse) {
		coord.setX(x);
		coord.setY(y);
		this.f =f ;
		this.vitesse = vitesse;
	}
	
	public void deplace(ObstacleModele obs, SolModele sol, ArrayList<ObstacleModele> ob){
		this.getVitesse().setX(this.getVitesse().getX()+this.acceleration.getX()*(this.masse/50));
		this.getVitesse().setY(this.getVitesse().getY()+this.acceleration.getY()*(this.masse/50));
		this.setX((int)(this.getX()+this.getVitesse().getX()/53));
		this.setY((int)(this.getY()+this.getVitesse().getY()/53));
	}
	
	public VecteurModele getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(VecteurModele vitesse) {
		this.vitesse = vitesse;
		setChanged ();
		notifyObservers ();
	}
	
	public String getF() {
		return f;
	}
	
	public void setF(String f) {
		this.f = f;
		setChanged ();
		notifyObservers ();
	}
	
	public int getRayon() {
		return rayon;
	}
	
	public void setRayon(int rayon) {
		this.rayon = rayon;
		setChanged ();
		notifyObservers ();
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getX() {
		return (int) coord.getX();
	}
	
	public int getY() {
		return (int) coord.getY();
	}
	
	public void setColor(Color color) {
		this.color = color;
		setChanged ();
		notifyObservers ();
	}
	
	public void setX(int x) {
		this.coord.setX(x);
		setChanged ();
		notifyObservers ();
	}
	
	public void setY(int y) {
		this.coord.setY(y);
		setChanged ();
		notifyObservers ();
	}
	
	public CoordonneesModele getCoord() {
		return coord;
	}
	
	public void setCoord(CoordonneesModele coord) {
		this.coord = coord;
		setChanged ();
		notifyObservers ();
	}
	
}
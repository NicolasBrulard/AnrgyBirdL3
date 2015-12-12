package modele;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import vue.FenetreContener;

public class BirdModele extends Observable {

	private Color color = Color.RED;
	private CoordonneesModele coord = new CoordonneesModele(150, Constantes.fenetreY-200);
	private int masse = 50;
	private int rayon = 20;
	private VecteurModele acceleration;
	private VecteurModele vitesse;
	private ArrayList<VecteurModele> centre;
	private int nb = 0;
	private CoordonneesModele coordBec;

	
	public BirdModele() {
		acceleration = new VecteurModele(0,-Constantes.g);
		this.vitesse = new VecteurModele(0,0);
		centre = new ArrayList<VecteurModele>();
	}
	
	public int getNb() {
		return nb;
	}
	
	public void setNb(int nb) {
		this.nb = nb;
	}
	
	public void init(){
		acceleration = new VecteurModele(0,10);
		this.vitesse = new VecteurModele(0,0);
		centre = new ArrayList<VecteurModele>();
	}
	
	public void deplace(){
		this.getVitesse().setX(this.getVitesse().getX()+this.acceleration.getX());
		this.getVitesse().setY(this.getVitesse().getY()+this.acceleration.getY());
		this.setX((int)(this.getX()+this.getVitesse().getX()/53));
		this.setY((int)(this.getY()+this.getVitesse().getY()/53));
	}
	
	public ArrayList<VecteurModele> getCentre() {
		return centre;
	}
	
	public void setCentre(ArrayList<VecteurModele> centre) {
		this.centre = centre;
	}
	
	public VecteurModele getVitesse() {
		return vitesse;
	}
	
	public VecteurModele getAcceleration() {
		return acceleration;
	}
	
	public void setVitesse(VecteurModele vitesse) {
		this.vitesse = vitesse;
		setChanged ();
		notifyObservers ();
	}
	
	
	public Color getColor() {
		return this.color;
	}
	
	public int getRayon() {
		return rayon;
	}
	
	public void setRayon(int rayon) {
		this.rayon = rayon;
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

	public int getX() {
		return (int) this.coord.getX();
	}
	
	public int getY() {
		return (int) this.coord.getY();
	}

	public void setX(int x) {
		this.coord.setX(x);
		setChanged ();
		notifyObservers ();
	}
	
	public void setColor(Color color) {
		this.color = color;
		setChanged ();
		notifyObservers ();
	}
	
	public void setY(int y) {
		this.coord.setY(y);
		setChanged ();
		notifyObservers ();
	}
	
	public int getMasse() {
		return masse;
	}
	
	public void setMasse(int masse) {
		this.masse = masse;
		setChanged ();
		notifyObservers ();
	}
	
}

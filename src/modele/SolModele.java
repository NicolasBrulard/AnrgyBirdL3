package modele;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Random;

public class SolModele extends Observable {
	
	private Color color = Color.GREEN;
	private CoordonneesModele coord = new CoordonneesModele(0, 0);
	private int masse = 1000000;
	private VecteurModele vitesse;
	private VecteurModele acceleration = new VecteurModele(0, 0);

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

	public SolModele(int x,int y, VecteurModele vitesse) {
		coord.setX(x);
		coord.setY(y);
		
		this.vitesse = vitesse;
	}
	
	public void deplace(){
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
	
	public int getX() {
		return (int) coord.getX();
	}
	
	public int getY() {
		return (int) coord.getY();
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
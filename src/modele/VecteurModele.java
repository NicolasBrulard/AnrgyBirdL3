package modele;

import java.util.Observable;

public class VecteurModele extends Observable{

	private double x,y;
	
	public VecteurModele(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
		setChanged ();
		notifyObservers ();
	}
	
	public void setY(double y) {
		this.y = y;
		setChanged ();
		notifyObservers ();
	}
}
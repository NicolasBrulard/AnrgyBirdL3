package modele;

import java.util.Observable;

public class CoordonneesModele extends Observable{
	private double x; //Les doubles sont plus pratiques à manipuler pour les calculs
	private double y; // Donc attention à caster en int pour les méthodes de Graphics
	
	public CoordonneesModele(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double doubleX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	@Override
	public String toString() {
		return "x : " + this.x + ", y = " + this. y;
	}
	
	
}

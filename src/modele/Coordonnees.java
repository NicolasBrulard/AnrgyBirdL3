package modele;

public class Coordonnees {
	private double x; //Les doubles sont plus pratiques à manipuler pour les calculs
	private double y; // Donc attention à caster en int pour les méthodes de Graphics
	private double t;
	
	public Coordonnees(double x, double y,double t) {
		this.x = x;
		this.y = y;
		this.t = t;
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
	
	public void setT(double t) {
		this.t = t;
	}
	
	public double getT() {
		return t;
	}
	
	public double getX() {
		return x;
	}
	
	@Override
	public String toString() {
		return "x : " + this.x + ", y = " + this. y + ", t : " + this.t;
	}
	
	
}

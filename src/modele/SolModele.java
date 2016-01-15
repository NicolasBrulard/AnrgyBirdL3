package modele;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Random;

public class SolModele extends Observable {
	
	private Color color = Color.GREEN;
	private CoordonneesModele coord = new CoordonneesModele(0, 0);
	private int masse = 1000000;

	
	public int getMasse() {
		return masse;
	}
	
	public void setMasse(int masse) {
		this.masse = masse;
	}


	public SolModele() {

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
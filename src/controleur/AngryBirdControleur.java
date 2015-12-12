package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import vue.AngryBirdVue;
import modele.AngryBirdModele;
import modele.Calcul;
import modele.Constantes;
import modele.CoordonneesModele;

public class AngryBirdControleur implements MouseListener,MouseMotionListener{

	//AngryBirdVue vue;
	
	AngryBirdModele m ;
	
	public AngryBirdControleur(AngryBirdModele model/* AngryBirdVue vue*/){
		//vue.addMouseListener(this);
		this.m = model;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(this.m.getB().getCentre().size()<1 && Calcul.calculDistance(new CoordonneesModele(e.getX(), e.getY()), new CoordonneesModele(m.getB().getX(), m.getB().getY()))<=Constantes.rayonBird){
			this.m.getB().getVitesse().setX(this.m.getB().getX());
			this.m.getB().getVitesse().setY(this.m.getB().getY());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.m.getB().getVitesse().getX()!= 0 && this.m.getB().getVitesse().getY()!= 0 && this.m.getB().getCentre().size()<1){
			this.m.getB().getVitesse().setX((this.m.getB().getVitesse().getX()-this.m.getB().getX())*7);//*4 pour rendre realiste la simu
			this.m.getB().getVitesse().setY((this.m.getB().getVitesse().getY()-this.m.getB().getY())*7);
			this.m.go();
		}
	}
	
	/*public void addVue(AngryBirdVue vue){
		this.vue = vue;
	}*/
	@Override
	public void mouseDragged(MouseEvent e) {
		if(Calcul.calculDistance(new CoordonneesModele(e.getX(), e.getY()), new CoordonneesModele(m.getB().getCoord().getX(), m.getB().getCoord().getY()))<=Constantes.rayonBird && this.m.getB().getCentre().size()<1){
			if(this.m.getB().getVitesse().getX()== 0 && this.m.getB().getVitesse().getY()== 0){//Permet si le premier clique n'est pas dans la zone
				this.m.getB().getVitesse().setX(e.getX());
				this.m.getB().getVitesse().setY(e.getY());
			}
			m.getB().setCoord(new CoordonneesModele(e.getX(), e.getY()));
		}
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
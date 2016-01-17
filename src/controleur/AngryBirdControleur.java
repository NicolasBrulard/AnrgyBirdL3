package controleur;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
		if(e.getX()>0 && e.getX()<100 &&e.getY()>0 && e.getY()<50){
			this.m.getGraph().setGraph(3);
		}
		if(e.getX()>Constantes.fenetreX-100 && e.getX()<Constantes.fenetreX &&e.getY()>0 && e.getY()<50){
			if(this.m.getGraph().getGraph() == 2){
				this.m.getGraph().setGraph(1);
			}else{
				this.m.getGraph().setGraph(2);
			}
		}
	}
	@Override
	/**
	 * Drop part : set the initial starting point of the bird
	 */
	public void mouseReleased(MouseEvent e) {
		m.setClique(false);
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
	/**
	 * Drag part : compute the speed according to where the bird is dragged
	 */
	public void mouseDragged(MouseEvent e) {
		if(Calcul.calculDistance(new CoordonneesModele(e.getX(), e.getY()), new CoordonneesModele(m.getB().getCoord().getX(), m.getB().getCoord().getY()))<=Constantes.rayonBird){
			m.setClique(true);
		}
		if(m.getClique() && this.m.getB().getCentre().size()<1){
			if(this.m.getB().getVitesse().getX()== 0 && this.m.getB().getVitesse().getY()== 0){//Permet si le premier clique n'est pas dans la zone
				this.m.getB().getVitesse().setX(e.getX());
				this.m.getB().getVitesse().setY(e.getY());
			}
			if(Calcul.calculDistance(new CoordonneesModele(150, Constantes.fenetreY-200), new CoordonneesModele(e.getX(), e.getY()))<=6*Constantes.rayonBird){
				m.getB().setCoord(new CoordonneesModele(e.getX(), e.getY()));
			}
			else{
				while(Calcul.calculDistance(new CoordonneesModele(150, Constantes.fenetreY-200), new CoordonneesModele(e.getX(), e.getY()))<=6*Constantes.rayonBird){
				int rediX = m.getB().getX()-e.getX();
				int rediY = m.getB().getY()-e.getY();
				m.getB().setCoord(new CoordonneesModele(m.getB().getX()+rediX, m.getB().getY()+rediY));}
			}
		}
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
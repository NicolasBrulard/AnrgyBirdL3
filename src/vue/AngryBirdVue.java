package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import modele.AngryBirdModele;
import modele.BirdModele;
import modele.Calcul;
import modele.Constantes;
import modele.CoordonneesModele;
import modele.ObstacleModele;
import modele.VecteurModele;
import controleur.AngryBirdControleur;

public class AngryBirdVue extends JPanel implements Observer/*,MouseListener,MouseMotionListener*/{

	private FenetreContener fenetre;
	private AngryBirdModele model;
	private AngryBirdControleur control;

	public AngryBirdVue(FenetreContener fenetre, AngryBirdControleur control, AngryBirdModele model) {
		this.fenetre = fenetre;
		this.model = model;
		this.control = control;
		fenetre.add(this);
		this.addMouseListener(control);
		model.addObserver(this); // Connexion entre la vue et le modele
		this.model.getB().addObserver(this);
		this.addMouseMotionListener(control);
		model.deplaceOB();
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, model.getFenetreX(), model.getFenetreY());
		this.dessineBird(g, this.model.getB());
		this.dessineObstaclesRond(g, model.getOb());
		this.dessineCentre(g);
		if(model.getB().getCentre().size()<1){
			this.lance(g);
		}
		else{
			this.dessineBec(g,this.model.getB());
		}
		repaint();
	}

	public void lance(Graphics g){
		g.setColor(Color.MAGENTA);
		g.drawLine(model.getB().getX(), model.getB().getY(), 150, Constantes.fenetreY-200);
	}

	public void dessineBird(Graphics g,BirdModele bird){
		g.setColor(bird.getColor());
		g.drawOval(bird.getX()-bird.getRayon(),bird.getY()-bird.getRayon(), bird.getRayon()*2, bird.getRayon()*2);
		g.drawString("" + bird.getNb(), 50, 50);
	}
	
	public void dessineBec(Graphics g,BirdModele bird){
		g.drawLine((int)bird.getX(),(int) bird.getY(), (int)(bird.getX()+model.getB().getVitesse().getX()/15), (int)(bird.getY()+model.getB().getVitesse().getY()/15));
	}

	public void dessineObstaclesRond(Graphics g, ArrayList<ObstacleModele> obs){
		for (ObstacleModele ob : obs) {
			g.setColor(ob.getColor());
			if(ob.getF().equals("o"))
			g.drawOval(ob.getX()-ob.getRayon(),ob.getY()-ob.getRayon(), ob.getRayon()*2, ob.getRayon()*2);
			else
			g.drawRect(ob.getX()-ob.getRayon(),ob.getY()-ob.getRayon(), ob.getRayon()*2, ob.getRayon()*2);
			
		}
	}

	public void dessineCentre(Graphics g){
		for (VecteurModele c : this.model.getB().getCentre()) {
			g.setColor(Color.BLACK);
			g.drawOval((int)c.getX()-1,(int) c.getY()-1, 2, 2);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}

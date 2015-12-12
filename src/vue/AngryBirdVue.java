package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import modele.AngryBirdModele;
import modele.BirdModele;
import modele.Calcul;
import modele.Constantes;
import modele.CoordonneesModele;
import modele.GraphismeModele;
import modele.ObstacleModele;
import modele.VecteurModele;
import controleur.AngryBirdControleur;

public class AngryBirdVue extends JPanel implements Observer/*,MouseListener,MouseMotionListener*/{

	private FenetreContener fenetre;
	private AngryBirdModele model;
	private AngryBirdControleur control;
	
	private BufferedImage demonBird;
	private BufferedImage background;
	private BufferedImage obsRond;
	private BufferedImage obsCarre;
	
	private GraphismeModele graph = new GraphismeModele();
	
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
		
		this.addKeyListener(control);
		
		try {
			demonBird = ImageIO.read(new File("src/images/demonbirdlittle.png"));
			background = ImageIO.read(new File("src/images/background.png"));
			obsRond = ImageIO.read(new File("src/images/obsrond.png"));
			obsCarre = ImageIO.read(new File("src/images/obscarre.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			 
		
		
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, model.getFenetreX(), model.getFenetreY());
		this.dessineFond(g);
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
	
	public void dessineFond(Graphics g){
		g.drawImage(background, 0, 0, null);
	}

	public void dessineBird(Graphics g,BirdModele bird){
		g.setColor(bird.getColor());
		g.drawOval(bird.getX()-bird.getRayon(),bird.getY()-bird.getRayon(), bird.getRayon()*2, bird.getRayon()*2);
		g.drawString("" + bird.getNb(), 50, 50);
		if(graph.getGraph()==false){
			g.drawImage(demonBird,bird.getX()-bird.getRayon()-10,bird.getY()-bird.getRayon()-9,null);
		}
	}
	
	public void dessineBec(Graphics g,BirdModele bird){
		g.drawLine((int)bird.getX(),(int) bird.getY(), (int)(bird.getX()+model.getB().getVitesse().getX()/15), (int)(bird.getY()+model.getB().getVitesse().getY()/15));
	}

	public void dessineObstaclesRond(Graphics g, ArrayList<ObstacleModele> obs){
		for (ObstacleModele ob : obs) {
			g.setColor(ob.getColor());
			if(ob.getF().equals("o")){
				g.fillOval(ob.getX()-ob.getRayon(),ob.getY()-ob.getRayon(), ob.getRayon()*2, ob.getRayon()*2);
				if(graph.getGraph()==false){
					g.drawImage(obsRond, ob.getX()-ob.getRayon(), ob.getY()-ob.getRayon(), null);
				}
				
			}else{
				g.fillRect(ob.getX()-ob.getRayon(),ob.getY()-ob.getRayon(), ob.getRayon()*2, ob.getRayon()*2);
				if(graph.getGraph()==false){
					g.drawImage(obsCarre, ob.getX()-ob.getRayon(), ob.getY()-ob.getRayon(), null);
				}
				
			}
			
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
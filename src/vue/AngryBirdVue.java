package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modele.AngryBirdModele;
import modele.BirdModele;
import modele.Constantes;
import modele.ObstacleModele;
import modele.SolModele;
import modele.VecteurModele;
import controleur.AngryBirdControleur;

public class AngryBirdVue extends JPanel implements Observer/*
															 * ,MouseListener,
															 * MouseMotionListener
															 */{

	private FenetreContener fenetre;
	private AngryBirdModele model;
	private AngryBirdControleur control;
	// JButton btn = new JButton("graphisme");

	private BufferedImage demonBird;
	private BufferedImage background01;
	private BufferedImage background02;
	private BufferedImage obsRond;
	private BufferedImage obsCarre;
	private BufferedImage btn;
	private BufferedImage btnBG;

	public AngryBirdVue(FenetreContener fenetre, AngryBirdControleur control,
			AngryBirdModele model) {
		this.fenetre = fenetre;
		this.model = model;
		this.control = control;
		// btn.setBounds(50,100,100,100);
		// this.add(btn);
		fenetre.setContentPane(this);
		fenetre.setVisible(true);
		this.addMouseListener(control);
		model.addObserver(this); // Connexion entre la vue et le modele
		this.model.getB().addObserver(this);
		this.addMouseMotionListener(control);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model.deplaceOB();
		try {
			demonBird = ImageIO
					.read(new File("AnrgyBirdL3/src/images/demonbirdlittle.png"));
			background01 = ImageIO.read(new File("AnrgyBirdL3/src/images/background.png"));
			background02 = ImageIO.read(new File("AnrgyBirdL3/src/images/background02.png"));
			// obsRond = ImageIO.read(new File("src/images/obsrond.png"));
			// obsCarre = ImageIO.read(new File("src/images/obscarre.png"));
			btn = ImageIO.read(new File("AnrgyBirdL3/src/images/btn.png"));
			btnBG = ImageIO.read(new File("AnrgyBirdL3/src/images/btnBG.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// this.setVisible(true);
	}

	/**
	 * Draws the whole scene (bird, obstacles, ...)
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		
			g.clearRect(0, 0, model.getFenetreX(), model.getFenetreY());
			
			this.dessineFond(g);
			
			g.drawImage(btn, 0, 0, null);
			g.drawImage(btnBG, Constantes.fenetreX-100, 0, null);
			this.dessineBird(g, this.model.getB());
			this.dessineObstacles(g, model.getListOb());
			this.dessineSol(g, model.getSol());
			this.dessineCentre(g);
			if (model.getB().getCentre().size() < 1) {
				this.lance(g);
			} else {
				this.dessineBec(g, this.model.getB());
			}
			repaint();
		
	}

	/**
	 * Show the estimated path of the bird following the drag part
	 * 
	 * @param g
	 */
	public void lance(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.drawLine(model.getB().getX(), model.getB().getY(), 150,
				Constantes.fenetreY - 200);
	}

	/**
	 * Draws the background of the scene
	 * 
	 * @param g
	 */
	public void dessineFond(Graphics g) {
		if (this.model.getGraph().getGraph()==1) {
			g.drawImage(background01, 0, 0, null);
		}else if(this.model.getGraph().getGraph()==2){
			g.drawImage(background02, 0, 0, null);
		}
		
	}

	/**
	 * Draws the bird according to its model
	 * 
	 * @param g
	 * @param bird
	 */
	public void dessineBird(Graphics g, BirdModele bird) {
		g.setColor(bird.getColor());
		if (this.model.getGraph().getGraph() == 1 || this.model.getGraph().getGraph() == 2) {

			g.drawImage(demonBird, bird.getX() - bird.getRayon() - 10,
			 bird.getY() - bird.getRayon() - 9, null);
			//Graphics2D g2d = (Graphics2D) g;


			//g.drawImage(demonBird, bird.getX() - bird.getRayon() - 10,
			// bird.getY() - bird.getRayon() - 9, null);
			/*Graphics2D g2d = (Graphics2D) g;

			AffineTransform at = g2d.getTransform();
			at.translate(model.getB().getX(), model.getB().getY());
			at.rotate(-Math.toDegrees(Math.atan2(model.getB().getY()
					/ 2*Constantes.YBirdDebut, model.getB().getX()
					/ 2*Constantes.xBirdDebut)),20,10);
			g2d.drawImage(demonBird, at, null);
		/*if (this.model.getGraph().getGraph() == 1 || this.model.getGraph().getGraph() == 2) {
			//g.drawImage(demonBird, bird.getX() - bird.getRayon() - 10,
				//	bird.getY() - bird.getRayon() - 9, null);
			g.drawImage(demonBird, bird.getX() - bird.getRayon() - 10,
					bird.getY() - bird.getRayon() - 9, null);

			Graphics2D g2d = (Graphics2D) g;
			AffineTransform at = new AffineTransform();
			//at.setToTranslation(model.getB().getX(), model.getB().getY());
			//System.out.println(Math.toDegrees(Math.atan2(model.getB().getY()-model.getB().getVitesse().getY(), model.getB().getX()-model.getB().getVitesse().getX())));
			//at.rotate(Math.toDegrees(Math.atan2(model.getB().getVitesse().getY()-model.getB().getY(), model.getB().getVitesse().getX()-model.getB().getX())));
		//	at.translate(-(model.getB().getX()), -(model.getB().getY()));

			g2d.drawImage(demonBird, at, this);*/

			//g2d.drawImage(demonBird, at, this);

			//g2d.drawImage(demonBird, at, null);

		} else {
			g.drawOval(bird.getX() - bird.getRayon(),
					bird.getY() - bird.getRayon(), bird.getRayon() * 2,
					bird.getRayon() * 2);
		}

		g.drawString("" + bird.getNb(), 50, 125);

		// g.drawImage(demonBird,bird.getX()-bird.getRayon()-10,bird.getY()-bird.getRayon()-9,null);
	}

/*	public void rotation(int angle) {
		BufferedImage image = null;
		JFileChooser choix = new JFileChooser();
		image = new BufferedImage(demonBird.getWidth(null),
				demonBird.getHeight(null), BufferedImage.TYPE_BYTE_INDEXED);
		image.getGraphics().drawImage(demonBird, 0, 0, null);

		AffineTransform transformer = new AffineTransform();
		transformer.rotate(angle, image.getWidth() / 2, image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(transformer,
				AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
	}*/

	/**
	 * Draws the bird mouth according to its movement (angle, direction)
	 * 
	 * @param g
	 * @param bird
	 */
	public void dessineBec(Graphics g, BirdModele bird) {
		g.drawLine((int) bird.getX(), (int) bird.getY(),
				(int) (bird.getX() + model.getB().getVitesse().getX() / 15),
				(int) (bird.getY() + model.getB().getVitesse().getY() / 15));
	}

	/**
	 * Draws the obstacles whether they are rectangular or circular
	 * 
	 * @param g
	 * @param obs
	 */
	public void dessineObstacles(Graphics g, ArrayList<ObstacleModele> obs) {
		for (ObstacleModele ob : obs) {

			if (ob.getF().equals("o")) {
				if (this.model.getGraph().getGraph() == 1 || this.model.getGraph().getGraph() == 2) {
					g.setColor(Color.BLACK);
					g.fillOval(ob.getX() - ob.getRayon(),
							ob.getY() - ob.getRayon(), ob.getRayon() * 2,
							ob.getRayon() * 2);
					g.setColor(ob.getColor());
					g.fillOval(ob.getX() - ob.getRayon() + 2,
							ob.getY() - ob.getRayon() + 2,
							ob.getRayon() * 2 - 4, ob.getRayon() * 2 - 4);
					g.drawImage(obsRond, ob.getX() - ob.getRayon(), ob.getY()
							- ob.getRayon(), null);

				} else {
					g.drawOval(ob.getX() - ob.getRayon(),
							ob.getY() - ob.getRayon(), ob.getRayon() * 2,
							ob.getRayon() * 2);
				}

			} else {
				if (this.model.getGraph().getGraph() == 1 || this.model.getGraph().getGraph() == 2) {
					g.setColor(Color.BLACK);
					g.fillRect(ob.getX() - ob.getRayon(),
							ob.getY() - ob.getRayon(), ob.getRayon() * 2,
							ob.getRayon() * 2);
					g.setColor(ob.getColor());
					g.fillRect(ob.getX() - ob.getRayon() + 2,
							ob.getY() - ob.getRayon() + 2,
							ob.getRayon() * 2 - 4, ob.getRayon() * 2 - 4);
					g.drawImage(obsCarre, ob.getX() - ob.getRayon(), ob.getY()
							- ob.getRayon(), null);
				} else {
					g.drawRect(ob.getX() - ob.getRayon(),
							ob.getY() - ob.getRayon(), ob.getRayon() * 2,
							ob.getRayon() * 2);
				}
			}
		}
	}

	/**
	 * Draws the ground to see where it's place exactly
	 * 
	 * @param g
	 * @param obs
	 */
	public void dessineSol(Graphics g, SolModele sol) {
		g.drawRect(sol.getX(), sol.getY(), 999, 100);
	}

	/**
	 * Draws the center of the bird
	 * 
	 * @param g
	 */
	public void dessineCentre(Graphics g) {
		for (VecteurModele c : this.model.getB().getCentre()) {
			g.setColor(Color.BLACK);
			g.drawOval((int) c.getX() - 1, (int) c.getY() - 1, 2, 2);
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
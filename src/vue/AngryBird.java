package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import controleur.Bird;
import controleur.Calcul;
import controleur.Obstacle;
import modele.Constantes;
import modele.Coordonnees;

public class AngryBird extends JPanel implements MouseListener{ //Classe principal

	private FenetreContener fenetre; //Fenetre Qui contient le jeu
	private ArrayList<Obstacle> ob = new ArrayList<>(); // Liste les obstacle du jeu
	private Bird bird; // Mon petit oiseau :D
	boolean init = true; // Boolean qui me sert à initialiser une fois certains élément
	

	public AngryBird(FenetreContener fenetre) {
		this.fenetre = fenetre;
		this.setSize(new Dimension(this.fenetre.getWidth(),this.fenetre.getHeight()));
		fenetre.addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		if(this.init){
		this.initBird(); // Cr�ation de l'oiseau
		this.initObstacle(); //Cr�ation des obstacles
		this.init = false;
		}
		if(!Calcul.testContactObstacle(this) && !Calcul.testContactFenetre(this,this.fenetre) && !Calcul.testTemps(bird.getCoord().getT())){ // Si l'oiseau ne touche pas l'obstacle le plus proche
			g.clearRect(0, 0, this.fenetre.getWidth(), this.fenetre.getHeight()); //efface tout
		this.bird.ajouteListe(new Coordonnees(bird.getCoord().getX(),bird.getCoord().getY(), bird.getCoord().getT())); //Permet de mettre ne m�moire tous les centres de cercles pour "tracer les courbes"
		this.bird.drawCentre(g); // Dessine tous les centre des positions de l'oiseau
		this.bird.drawBird(g); //Dessine l'oiseau � la nouvelle position
		this.bird.drawBec(g); //Dessine le bec de l'oiseau
		this.bird.deplace(); // Calcul les nouvelles coordonn�es de l'oiseau
		/*try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		}
		else{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.initBird();
		}
		for (Obstacle obstacle : this.ob) {
			obstacle.draw(g); // Dessine les obstacles
		}
		repaint();
	}
		
	public void initObstacle(){ //Permet d'initialiser les obstacles
		for(int i = 0; i<Constantes.nbOb;i++){
			this.ob.add(new Obstacle(new Coordonnees(this.fenetre.getWidth()-100, i*50+50,0),Color.GREEN)); // Coord � peu pr�s en dur
		}
	}
	
	public void initBird(){ //Permet d'initialiser l'oiseau
		this.bird = new Bird(new Coordonnees(4.7,497.29,-22.3), Color.RED,this.fenetre); //coord en dur � changer !!
	}
	
	public Bird getBird() {
		return bird;
	}
	
	public ArrayList<Obstacle> getOb() {
		return ob;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

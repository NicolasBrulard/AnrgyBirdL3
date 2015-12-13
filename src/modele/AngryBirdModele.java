package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class AngryBirdModele extends Observable {

	private BirdModele b = new BirdModele();
	private ArrayList<ObstacleModele> ob;
	private int fenetreX = 1000;
	private int fenetreY = 500;
	public javax.swing.Timer timer;
	private GraphismeModele graph = new GraphismeModele();

	public AngryBirdModele() {
		this.initB();
		this.initOb();
	}

	/**
	 * Initiates the bird's coordinate (before the drag/drop part)
	 */
	public void initB(){
		b.setCoord(new CoordonneesModele(150, Constantes.fenetreY-200));
		b.setNb(b.getNb()+1);
	}

	/**
	 * Initiates the obstacles on the scene
	 */
	public void initOb(){
		ob = new ArrayList<>();
		for(int i = 1;i<=Constantes.nbOb;i++){
			if(i<Constantes.nbOb)
				ob.add(new ObstacleModele(this.fenetreX-150,i*100,"o",new VecteurModele(-i, 0)));
			else
				ob.add(new ObstacleModele(this.fenetreX-150,i*100,"r",new VecteurModele(0, i)));	
		}
		
		ob.add(new ObstacleModele(this.fenetreX-350,200,"r",new VecteurModele(0, 0)));
		ob.add(new ObstacleModele(this.fenetreX-350,400,"r",new VecteurModele(0, 0)));
	}

	/**
	 * Makes the bird move according to whether or not it can
	 */
	public void deplace(){
		if(!Calcul.testContactObstacle(this) && !Calcul.testContactFenetre(this)){
			this.b.deplace();
		}
		else{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {

				System.out.println(e.getMessage());
			}
			this.initB();
			this.getB().init();
			timer.stop();
		}
	}

	/**
	 * Makes the obstacles move on a straight line making them go back and forth
	 */
	public void deplaceOB(){
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (ObstacleModele ob : ob) {
					if(ob.getX()<fenetreX-180 || ob.getX()>fenetreX - 120){
						ob.getVitesse().setX(-ob.getVitesse().getX());
						ob.getVitesse().setY(-ob.getVitesse().getY());
					}
					if(ob.getF().equals("r") && (ob.getY()<fenetreY-200 || ob.getY()>fenetreY - 100)){
						ob.getVitesse().setX(-ob.getVitesse().getX());
						ob.getVitesse().setY(-ob.getVitesse().getY());
					}

					ob.setCoord(new CoordonneesModele(ob.getX()+ob.getVitesse().getX(), ob.getY()+ob.getVitesse().getY()));
				}
			}
		};
		timer = new javax.swing.Timer(15, a);
		timer.start();
	}

	/**
	 * Launches the whole game, with a timer system.
	 */
	public void go(){
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				b.getCentre().add(new VecteurModele(b.getX(), b.getY()));
				deplace();
				//deplaceOB();
				setChanged ();
				notifyObservers ();
			}
		};
		timer = new javax.swing.Timer(15, a);
		timer.start();

	}
	
	public BirdModele getB() {
		return b;
	}

	public ArrayList<ObstacleModele> getOb() {
		return ob;
	}

	public int getFenetreX() {
		return fenetreX;
	}

	public int getFenetreY() {
		return fenetreY;
	}
	
	public GraphismeModele getGraph() {
		return graph;
	}

}

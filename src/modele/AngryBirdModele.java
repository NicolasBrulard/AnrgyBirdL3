package modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

public class AngryBirdModele extends Observable {

	private BirdModele b = new BirdModele();
	private ArrayList<ObstacleModele> listOb;
	private SolModele sol = new SolModele();
	private int fenetreX = 1000;
	private int fenetreY = 500;
	public javax.swing.Timer timer,timer2;
	private GraphismeModele graph = new GraphismeModele();
	private boolean clique= false;

	public AngryBirdModele() {
		this.initB();
		this.initOb();
		this.initSol();
	}
	
	public void setClique(boolean clique) {
		this.clique = clique;
	}
	
	public boolean getClique(){
		return this.clique;
	}

	/**
	 * Initiates the bird's coordinate (before the drag/drop part)
	 */
	public void initB(){
		b.setCoord(new CoordonneesModele(150, Constantes.fenetreY-200));
		b.setVitesse(new VecteurModele(0, 0));
		b.setNb(b.getNb()+1);
		if (this.getB().getNb() >= 10) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(
					null,
					"10 lancers ont ete effectues. L'application va se fermer.",
					"Alert", JOptionPane.WARNING_MESSAGE, null);
			System.exit(1);
		}
	}
	
	public void initSol(){
		sol.setCoord(new CoordonneesModele(0, Constantes.fenetreY-70));
	}

	/**
	 * Initiates the obstacles on the scene
	 */
	public void initOb(){
		listOb = new ArrayList<>();
		for(int i = 1;i<=Constantes.nbOb;i++){
			if(i<Constantes.nbOb)
				listOb.add(new ObstacleModele(this.fenetreX-150,i*100,"o",new VecteurModele(0, 0),2));
			else
				listOb.add(new ObstacleModele(this.fenetreX-150,i*100,"r",new VecteurModele(0, 0),3));	
		}
		
		listOb.add(new ObstacleModele(this.fenetreX-350,200,"r",new VecteurModele(0, 0),3));
		listOb.add(new ObstacleModele(this.fenetreX-350,400,"r",new VecteurModele(0, 0),3));
	}

	/**
	 * Makes the bird move according to whether or not it can
	 */
	public void deplace(){
		if(!Calcul.testContactBirdObstacle(this) && !Calcul.testContactFenetre(this) && !Calcul.testContactBirdSol(this.getB(), this.getSol())){
			this.getB().deplace();
		}
		else if(Calcul.testContactBirdObstacle(this)){
			Calcul.chercherObsProche(this.getB(),this.getListOb()).setVitesse(this.getB().getVitesse());
			Calcul.chercherObsProche(this.getB(),this.getListOb()).setAcceleration(this.getB().getAcceleration());
			this.getB().setVitesse(new VecteurModele(-this.getB().getVitesse().getX(),this.getB().getVitesse().getX()));
			Calcul.chercherObsProche(this.getB(),this.getListOb()).perdVie();
		}
		else if(Calcul.testContactBirdSol(this.getB(), this.getSol())){
			this.getB().setY(this.getB().getY()-5);
			this.getB().setX(this.getB().getX());
			this.getB().setVitesse(new VecteurModele(this.getB().getVitesse().getX()/2,-this.getB().getVitesse().getY()/2));
		}
		else{
			
			this.initB();
			this.getB().init();
			timer.stop();
		}
		if(this.getB().getVitesse().getX()>-20 && this.getB().getVitesse().getX()<20 && this.getB().getVitesse().getY()>-20 && this.getB().getVitesse().getX()<20){
			this.initB();
			this.getB().init();
			timer.stop();
		}
				}
	/*
	public void stopOB(){
		if(Calcul.testContactSol){
			
		}
	}*/

	/**
	 * Makes the obstacles move on a straight line making them go back and forth
	 */
	public void deplaceOB(){
		for (ObstacleModele obs : listOb) {
			
				ActionListener a = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						if(obs.getVie()<=0){
							obs.setX(1000);
							obs.setY(1000);
						}
						
						
						if (Calcul.testContactObSol(obs,sol)) {
							System.out.println("sol : " + obs.getVitesse().getY());
							if(obs.getVitesse().getY() < 120){
								System.out.println("ok");
								obs.setVitesse(new VecteurModele(0,0));
							}else{
								obs.setVitesse(new VecteurModele(obs.getVitesse().getX()/2,-obs.getVitesse().getY()/2));
							}
							obs.setY(obs.getY()-2);
							obs.setX(obs.getX());
							obs.deplace(obs,sol,listOb);
						}
						else if(Calcul.testContactObOb(obs,listOb)){
							if(obs.getVitesse().getY() < 70){
								obs.setVitesse(new VecteurModele(0,0));
							}else{
								obs.setVitesse(new VecteurModele(obs.getVitesse().getX()/2,-obs.getVitesse().getY()/2));
							}
							obs.deplace(obs,sol,listOb);
						}
							
						obs.deplace(obs,sol,listOb);
						
						
						setChanged ();
						notifyObservers ();
					}
				};
				timer2 = new javax.swing.Timer(15, a);
				timer2.start();
			}
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

	public ArrayList<ObstacleModele> getListOb() {
		return listOb;
	}
	
	public SolModele getSol(){
		return sol;
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

package modele;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.TimerTask;

import javax.management.timer.Timer;








import javax.swing.JComponent;

import vue.AngryBirdVue;

public class AngryBirdModele extends Observable {

	private BirdModele b = new BirdModele();
	private ArrayList<ObstacleModele> ob;
	/*private VecteurModele acceleration;
	private VecteurModele vitesse;*/
	private int fenetreX = 1000;
	private int fenetreY = 500;
	public javax.swing.Timer timer;

	public AngryBirdModele() {
		this.initB();
		this.initOb();
	}

	public void initB(){
		b.setCoord(new CoordonneesModele(150, Constantes.fenetreY-200));
		b.setNb(b.getNb()+1);
	}

	public void initOb(){
		ob = new ArrayList<>();
		for(int i = 1;i<=Constantes.nbOb;i++){
			if(i<Constantes.nbOb)
				ob.add(new ObstacleModele(this.fenetreX-150,i*100,"o",new VecteurModele(-i, 0)));
			else
				ob.add(new ObstacleModele(this.fenetreX-150,i*100,"r",new VecteurModele(0, i)));	
		}
	}

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

	//public void deplaceOB(/*ObstacleModele o*/){
	//	o.setCoord(new CoordonneesModele(o.getX()+o.getVitesse().getX(), o.getY()+o.getVitesse().getY()));
	//	for (ObstacleModele ob : ob) {
	//		ob.setCoord(new CoordonneesModele(ob.getX()+ob.getVitesse().getX(), ob.getY()+ob.getVitesse().getY()));

	//}
	//}

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

	/*public void goOB(){
		ActionListener a = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (ObstacleModele ob : ob) {
					deplaceOB(ob);
				}
				setChanged ();
				notifyObservers ();
			}
		};
		timer = new javax.swing.Timer(1000, a);
		timer.start();
	}*/

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

	/*public VecteurModele getVitesse() {
		return vitesse;
	}
public VecteurModele getAcceleration() {
		return acceleration;
	}*/
}
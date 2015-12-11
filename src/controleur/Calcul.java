package controleur;

import java.awt.Color;
import java.util.ArrayList;


import modele.Constantes;
import modele.Coordonnees;
import vue.AngryBird;
import vue.FenetreContener;

public class Calcul {
	
	/**
	 * This function looks for obstacles from the frame
	 * @param Bird
	 * @param List of Obstacle
	 * @return the nearest obstacle
	 */
	public static Obstacle chercherObsProche(Bird bird, ArrayList<Obstacle> ob){ //Permet de rechercher l'obstacle le plus proche
		double distance = 999999;
		Obstacle obProche = null;
		for (Obstacle obs : ob) { //Parcours les obstacles

			if(distance>Calcul.calculDistance(bird.getCoord(), obs.getC())){ //Si la distance avec le nouvel obstacle est plus petite, alors il la r�cup�re

			if(distance>Calcul.calculDistance(bird.getCoord(), obs.getC())){ //Si la distance avec le nouvel obstacle est plus petite, alors il la recupere

				distance = Calcul.calculDistance(bird.getCoord(), obs.getC()); 
				obProche = obs;
			}
		}
		}
		
		return obProche;
		
	}

	/*public static double calculDistance(Bird bird, Obstacle ob){ //Calcul la distance entre les 2 centres. Racine[(xB-xA)^2+(yB-Ya)^2]
		
		return Math.sqrt(Math.pow((ob.getC().getX()-bird.getCoord().getX()),2)+Math.pow((ob.getC().getY()-bird.getCoord().getY()),2));
		
	}*/
	
	/**
	 * This function computes the distance between two coordinates.
	 * @param Coordinates 1
	 * @param Coordinates 2
	 * @return the result of the operation
	 */

	public static double calculDistance(Coordonnees c1, Coordonnees c2){ //Calcul la distance entre les 2 centres. Racine[(xB-xA)^2+(yB-Ya)^2]

		
		
		return Math.sqrt(Math.pow((c2.getX()-c1.getX()),2)+Math.pow((c2.getY()-c1.getY()),2));
		
	}
	
	/**
	 * This function tests if the Bird hits an obstacle
	 * @param Bird
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactObstacle(AngryBird bird){ //Reenvoi vrai si la distance avec le plus proche obstacle est inferieur a la somme des deux rayons
		if(Calcul.calculDistance(bird.getBird().getCoord(), Calcul.chercherObsProche(bird.getBird(),bird.getOb()).getC())< Calcul.chercherObsProche(bird.getBird(),bird.getOb()).getDiametre()/2+Constantes.rayonBird){

			Calcul.chercherObsProche(bird.getBird(),bird.getOb()).setColor(Color.BLUE);
			Calcul.chercherObsProche(bird.getBird(),bird.getOb()).setAngle(180);

			return true;
		}		
		return false;
	}
	
	/**
	 * This function tests if the Bird hits the top of the window or not
	 * @param Bird
	 * @param Window
	 * @return true or false, depending on if there's a collision
	 */
	public static boolean testContactFenetre(AngryBird bird, FenetreContener fenetre){
		
		if((bird.getBird().getCoord().getX()+Constantes.rayonBird>=fenetre.getWidth()-Constantes.decalageFenetreXDroite || bird.getBird().getCoord().getY()+Constantes.rayonBird*2>=fenetre.getHeight()-Constantes.decalageFenetreYBas) && bird.getBird().getCoord().getX()>=200){ //dernier if moche
			return true;
		}
		return false;
	}
	
	/**
	 * This function tests how long it will take to execute the move
	 * @param d
	 * @param courbe
	 * @return the time
	 */
	public static boolean testTemps(double d, int courbe){
		switch (courbe) {
		case 0:
			return d>=127.7;
		case 1:
			return d>=7.7;
		case 2:
			return d>=127.7;
		case 3:
			return d>=-7.3;
		default:
			break;
		}
		return false;
	}

	public static boolean testSupperpositionObstacle(AngryBird angry) {
		// TODO Auto-generated method stub
		return false;
	}
	
/*public static boolean testContactFenetre(AngryBird bird){
		
		if(Calcul.calculDistance(bird.getBird().getCoord(), new Coordonnees(bird.getBird().getCoord().getX(), Constantes.fenetreY, 0))<Constantes.rayonBird){
			return true;
		}
		return false;
	}*/
}

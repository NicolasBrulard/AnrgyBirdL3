package modele;

import java.awt.Color;
import java.util.ArrayList;


import modele.Constantes;
import modele.CoordonneesModele;

import vue.FenetreContener;

public class Calcul {
	
	/**
	 * This function looks for obstacles from the frame
	 * @param Bird
	 * @param List of Obstacle
	 * @return the nearest obstacle
	 */
	public static ObstacleModele chercherObsProche(BirdModele bird, ArrayList<ObstacleModele> ob){ //Permet de rechercher l'obstacle le plus proche
		double distance = 999999;
		ObstacleModele obProche = null;
		for (ObstacleModele obs : ob) { //Parcours les obstacles

			
			if(distance>Calcul.calculDistance(bird.getCoord(), obs.getCoord())){ //Si la distance avec le nouvel obstacle est plus petite, alors il la recupere

				distance = Calcul.calculDistance(bird.getCoord(), obs.getCoord()); 
				obProche = obs;
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

	public static double calculDistance(CoordonneesModele c1, CoordonneesModele c2){ //Calcul la distance entre les 2 centres. Racine[(xB-xA)^2+(yB-Ya)^2]

		
		
		return Math.sqrt(Math.pow((c2.getX()-c1.getX()),2)+Math.pow((c2.getY()-c1.getY()),2));
		
	}
	
	/**
	 * This function tests if the Bird hits an obstacle
	 * @param Bird
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactObstacle(AngryBirdModele bird){ //Reenvoi vrai si la distance avec le plus proche obstacle est inferieur a la somme des deux rayons
		if(Calcul.calculDistance(bird.getB().getCoord(), Calcul.chercherObsProche(bird.getB(),bird.getOb()).getCoord())< Calcul.chercherObsProche(bird.getB(),bird.getOb()).getRayon()+bird.getB().getRayon()){

			Calcul.chercherObsProche(bird.getB(),bird.getOb()).setColor(Color.BLUE);
			//Calcul.chercherObsProche(bird.getB(),bird.getOb()).setAngle(180);

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
	public static boolean testContactFenetre(AngryBirdModele bird){
		
		if((bird.getB().getX()+Constantes.rayonBird>=bird.getFenetreX()-Constantes.decalageFenetreXDroite || bird.getB().getY()+Constantes.rayonBird*2+Constantes.decalageFenetreYBas>=bird.getFenetreY())){ //dernier if moche
			return true;
		}
		return false;
	}
	
}

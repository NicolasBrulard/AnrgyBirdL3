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
	 * @param AngryBirdModel
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactBirdObstacle(AngryBirdModele model){ //Renvoi vrai si la distance avec le plus proche obstacle est inferieur a la somme des deux rayons
		if(Calcul.calculDistance(model.getB().getCoord(), Calcul.chercherObsProche(model.getB(),model.getListOb()).getCoord())< Calcul.chercherObsProche(model.getB(),model.getListOb()).getRayon()+model.getB().getRayon()){

			Calcul.chercherObsProche(model.getB(),model.getListOb()).setColor(Color.BLUE);
			//Calcul.chercherObsProche(bird.getB(),bird.getOb()).setAngle(180);

			return true;
		}		
		return false;
	}
	
	/**
	 * This function tests if the Obstacle hits the ground
	 * @param ObstacleModele
	 * @param SolModele
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactObSol(ObstacleModele ob, SolModele sol){
		
		if((ob.getY()+ob.getRayon()) > sol.getY()){
			ob.setColor(Color.CYAN);
			return true;
		}
		return false;
	}
	
	/**
	 * This function tests if the Bird hits the ground
	 * @param BirdModele
	 * @param SolModele
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactBirdSol(BirdModele bird, SolModele sol){
		
		if((bird.getY()+bird.getRayon()) > sol.getY()){
			return true;
		}
		return false;
	}
	
	/**
	 * This function tests if the Obstacle hits another Obstacle
	 * @param List of Obstacle
	 * @return the nearest obstacle
	 */

	
	public static ObstacleModele chercheContactObOb(ObstacleModele ob, ArrayList<ObstacleModele> listObs) {
		double distance = 999999;
		ObstacleModele obsProche = null;
		for (ObstacleModele obs : listObs) {
				if(ob!=obs){	
					if (distance > Calcul.calculDistance(ob.getCoord(), obs.getCoord())) { // Si la distance avec le nouvel
						// obstacle est plus petite, alors
						// il la recupere
						
						distance = Calcul.calculDistance(ob.getCoord(), obs.getCoord());
						
						obsProche = obs;
					}
				}
			}
		return obsProche;

		}

		

	
	
	/**
	 * This function tests if the Obstacle hits another Obstacle
	 * @param ObstacleModel
	 * @param ArrayList<ObstacleModele>
	 * @return true or false, depending on if there's a collision
	 */

	
	public static boolean testContactObOb(ObstacleModele obstacle, ArrayList<ObstacleModele> listObs) {
		ObstacleModele obs = Calcul.chercheContactObOb(obstacle, listObs);

		if (Calcul.calculDistance(obs.getCoord(), obstacle.getCoord()) < obs.getRayon() + obstacle.getRayon()) {

			return true;
		}

		return false;

	}
	
	
	/**
	 * This function tests if the Obstacle go really slow
	 * @param ObstacleModel
	 * @param ArrayList<ObstacleModele>
	 * @return true or false, depending on if the obstacle go slow or not
	 */

	
	public static boolean testObLent(ObstacleModele obstacle) {
		if (obstacle.getVitesse().equals(new VecteurModele(0,0))) {

			return true;
		}

		return false;

	}
	
	
	/**
	 * This function tests if the Bird hits the top of the window or not
	 * @param Bird
	 * @return true or false, depending on if there's a collision
	 */
	public static boolean testContactFenetre(AngryBirdModele bird){
		
		if((bird.getB().getX()+Constantes.rayonBird>=bird.getFenetreX()-Constantes.decalageFenetreXDroite || bird.getB().getX()+Constantes.rayonBird<=0)){
			return true;
		}
		return false;
	}
	
}
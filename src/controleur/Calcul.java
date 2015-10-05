package controleur;

import java.awt.Color;
import java.util.ArrayList;

import modele.Constantes;
import modele.Coordonnees;
import vue.AngryBird;
import vue.FenetreContener;

public class Calcul {
	
	public static Obstacle chercherObsProche(Bird bird, ArrayList<Obstacle> ob){ //Permet de rechercher l'obstacle le plus proche
		double distance = 999999;
		Obstacle obProche = null;
		for (Obstacle obs : ob) { //Parcours les obstacles
			if(distance>Calcul.calculDistance(bird.getCoord(), obs.getC())){ //Si la distance avec le nouvel obstacle est plus petite, alors il la récupère
				distance = Calcul.calculDistance(bird.getCoord(), obs.getC()); 
				obProche = obs;
			}
		}
		
		return obProche;
		
	}
	
	/*public static double calculDistance(Bird bird, Obstacle ob){ //Calcul la distance entre les 2 centres. Racine[(xB-xA)²+(yB-Ya)²]
		
		
		return Math.sqrt(Math.pow((ob.getC().getX()-bird.getCoord().getX()),2)+Math.pow((ob.getC().getY()-bird.getCoord().getY()),2));
		
	}*/
	
	public static double calculDistance(Coordonnees c1, Coordonnees c2){ //Calcul la distance entre les 2 centres. Racine[(xB-xA)²+(yB-Ya)²]
		
		
		return Math.sqrt(Math.pow((c2.getX()-c1.getX()),2)+Math.pow((c2.getY()-c1.getY()),2));
		
	}
	
	public static boolean testContactObstacle(AngryBird bird){ //Reenvoi vrai si la distance avec le plus proche obstacle est inférieur à la somme des deux rayons
		if(Calcul.calculDistance(bird.getBird().getCoord(), Calcul.chercherObsProche(bird.getBird(),bird.getOb()).getC())< Calcul.chercherObsProche(bird.getBird(),bird.getOb()).getDiametre()/2+Constantes.rayonBird){
			Calcul.chercherObsProche(bird.getBird(),bird.getOb()).setColor(Color.BLUE);

			return true;
		}		
		return false;
	}
	
	public static boolean testContactFenetre(AngryBird bird, FenetreContener fenetre){
		
		if((bird.getBird().getCoord().getX()+Constantes.rayonBird>=fenetre.getWidth()-Constantes.decalageFenetreXDroite || bird.getBird().getCoord().getY()+Constantes.rayonBird*2>=fenetre.getHeight()-Constantes.decalageFenetreYBas) && bird.getBird().getCoord().getX()>=200){ //dernier if moche
			return true;
		}
		return false;
	}
	
/*public static boolean testContactFenetre(AngryBird bird){
		
		if(Calcul.calculDistance(bird.getBird().getCoord(), new Coordonnees(bird.getBird().getCoord().getX(), Constantes.fenetreY, 0))<Constantes.rayonBird){
			return true;
		}
		return false;
	}*/
}

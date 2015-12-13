package main;

import controleur.AngryBirdControleur;
import modele.AngryBirdModele;
import modele.GraphismeModele;
import vue.AngryBirdVue;
import vue.FenetreContener;




public class Main {

	public static void main(String[] args) {
				FenetreContener fenetre = new FenetreContener();
				AngryBirdModele model = new AngryBirdModele();
				GraphismeModele gmodel = new GraphismeModele();
				AngryBirdControleur control = new AngryBirdControleur(model);
				AngryBirdVue vue = new AngryBirdVue(fenetre, control, model);
			}
	}
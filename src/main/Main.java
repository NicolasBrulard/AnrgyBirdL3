package main;

import controleur.AngryBirdControleur;
import modele.AngryBirdModele;
import vue.AngryBirdVue;
import vue.FenetreContener;




public class Main {

	public static void main(String[] args) {
				FenetreContener fenetre = new FenetreContener();
				AngryBirdModele model = new AngryBirdModele();
				AngryBirdControleur control = new AngryBirdControleur(model);
				AngryBirdVue vue = new AngryBirdVue(fenetre, control, model);
			}
	}

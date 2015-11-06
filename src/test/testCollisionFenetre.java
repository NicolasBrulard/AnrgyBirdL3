package test;

import static org.junit.Assert.*;


import modele.Constantes;
import modele.Coordonnees;

import org.junit.Test;

import vue.AngryBird;
import vue.FenetreContener;
import controleur.Bird;
import controleur.Calcul;

public class testCollisionFenetre {

	@Test
	public void testCollisionFenetreX() {
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(Constantes.fenetreX-1, Constantes.fenetreY/2, 0),null,fe);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		assertTrue(Calcul.testContactFenetre(angry, fe));
	}
	@Test
	public void testCollisionFenetreY() {
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(Constantes.fenetreX/2, Constantes.fenetreY-1, 0),null,fe);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		assertTrue(Calcul.testContactFenetre(angry, fe));
	}
	@Test
	public void testNoCollisionFenetre() {
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(Constantes.fenetreX/2, Constantes.fenetreY/2, 0),null,fe);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		assertFalse(Calcul.testContactFenetre(angry, fe));
	}
}

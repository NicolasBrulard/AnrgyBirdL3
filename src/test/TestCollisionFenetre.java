package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modele.AngryBirdModele;
import modele.BirdModele;
import modele.Calcul;
import modele.Constantes;
import modele.Coordonnees;
import modele.CoordonneesModele;

import org.junit.Test;

import vue.FenetreContener;

public class TestCollisionFenetre {

	@Test
	public void testCollisionFenetreXContact() {
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(990, 50));
		assertTrue(Calcul.testContactFenetre(angry));
	}
	@Test
	public void testCollisionFenetreX() {
		FenetreContener fe = new FenetreContener();
		AngryBirdModele angry = new AngryBirdModele(); // pas fini
		assertTrue(Calcul.testContactFenetre(angry));
	}
	
	@Test
	public void testCollisionFenetreYContact() {
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(Constantes.fenetreX/2, Constantes.fenetreY-1, 0),null,fe);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		assertTrue(Calcul.testContactFenetre(angry, fe));
	}
	@Test
	public void testCollisionFenetreY() {
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(Constantes.fenetreX/2, Constantes.fenetreY+10, 0),null,fe);
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
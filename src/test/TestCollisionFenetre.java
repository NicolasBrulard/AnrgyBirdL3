package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import modele.AngryBirdModele;
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
		AngryBirdModele angry = new AngryBirdModele(); // pas fini
		angry.getB().setCoord(new CoordonneesModele(1010, 50));
		assertTrue(Calcul.testContactFenetre(angry));
	}
	
	@Test
	public void testCollisionFenetreYContact() {
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(50, 450));
		assertTrue(Calcul.testContactFenetre(angry));
	}
	@Test
	public void testCollisionFenetreY() {
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(50, 510));
		assertTrue(Calcul.testContactFenetre(angry));
	}
	
	@Test
	public void testNoCollisionFenetre() {
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(150, 50));
		assertFalse(Calcul.testContactFenetre(angry));
	}
}
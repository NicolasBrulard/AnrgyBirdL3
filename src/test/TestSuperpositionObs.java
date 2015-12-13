package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import modele.AngryBirdModele;
import modele.Calcul;
import modele.Coordonnees;
import modele.CoordonneesModele;

import org.junit.Test;

import vue.FenetreContener;

public class TestSuperpositionObs {

	@Test
	public void testSuperposeExact() {
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(angry.getFenetreX()-150, 100));
		assertTrue(Calcul.calculDistance(angry.getB().getCoord(), angry.getOb().get(0).getCoord())==0);
	}
	
}

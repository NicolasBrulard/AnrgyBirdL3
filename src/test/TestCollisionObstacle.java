package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import modele.AngryBirdModele;
import modele.Calcul;
import modele.Coordonnees;
import modele.CoordonneesModele;
import modele.ObstacleModele;

import org.junit.Test;

import vue.FenetreContener;

public class TestCollisionObstacle {

	@Test
	public void testCollisionObstacleInterieur(){	
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(angry.getFenetreX()-135, 100));
		assertTrue(Calcul.testContactObstacle(angry));
	}
	
	@Test
	public void testCollisionObstacleContact(){
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(angry.getFenetreX()-175, 100));
		assertTrue(Calcul.testContactObstacle(angry));
	}
	
	@Test
	public void testNoCollision(){
		AngryBirdModele angry = new AngryBirdModele();
		angry.getB().setCoord(new CoordonneesModele(500, 100));
		assertTrue(!Calcul.testContactObstacle(angry));
	}
	

}
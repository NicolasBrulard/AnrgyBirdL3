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
		FenetreContener fe = new FenetreContener();
		AngryBirdModele ois = new AngryBirdModele();
		ois.getB().setCoord(new CoordonneesModele(100, 100));
		
		ObstacleModele obs = new ObstacleModele(99,99);
		ArrayList<ObstacleModele> ListOb = new ArrayList<ObstacleModele>();
		ListOb.add(obs);
		fe.setBird(ois);
		fe.setOb(ListOb);
		
		assertTrue(Calcul.testContactObstacle(ois));
	}
	
	@Test
	public void testCollisionObstacleContact(){
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(100, 100, 0),null,fe);
		Obstacle obs = new Obstacle(new Coordonnees(130,100,0),null);
		obs.setDiametre(20);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obs);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		angry.setOb(ListOb);
		assertTrue(Calcul.testContactObstacle(angry));
	}
	
	@Test
	public void testNoCollision(){
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(100, 100, 0),null,fe);
		Obstacle obs = new Obstacle(new Coordonnees(175,100,0),null);
		obs.setDiametre(20);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obs);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		angry.setOb(ListOb);
		assertFalse(Calcul.testContactObstacle(angry));
	}
	

}
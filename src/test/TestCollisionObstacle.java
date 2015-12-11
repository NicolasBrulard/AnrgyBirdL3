package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modele.Coordonnees;

import org.junit.Test;

import vue.AngryBird;
import vue.FenetreContener;
import controleur.Bird;
import controleur.Calcul;
import controleur.Obstacle;

public class TestCollisionObstacle {

	@Test
	public void testCollisionObstacleInterieur(){
		FenetreContener fe = new FenetreContener();
		Bird ois = new Bird(new Coordonnees(100, 100, 0),null,fe);
		Obstacle obs = new Obstacle(new Coordonnees(99,99,0),null);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obs);
		AngryBird angry = new AngryBird(fe);
		angry.setBird(ois);
		angry.setOb(ListOb);
		assertTrue(Calcul.testContactObstacle(angry));
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

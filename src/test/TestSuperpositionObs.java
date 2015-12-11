package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import modele.Coordonnees;

import org.junit.Test;

import vue.AngryBird;
import vue.FenetreContener;
import controleur.Calcul;
import controleur.Obstacle;

public class TestSuperpositionObs {
/*
	@Test
	public void testSuperposeExact() {
		FenetreContener fe = new FenetreContener();
		Obstacle obsA = new Obstacle(new Coordonnees(100,100,0),null);
		obsA.setDiametre(20);
		Obstacle obsB = new Obstacle(new Coordonnees(100,100,0),null);
		obsB.setDiametre(20);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obsA);
		ListOb.add(obsB);
		AngryBird angry = new AngryBird(fe);
		angry.setOb(ListOb);
		assertTrue(Calcul.testSupperpositionObstacle(angry));
	}
	@Test
	public void testSuperposeContact() {
		FenetreContener fe = new FenetreContener();
		Obstacle obsA = new Obstacle(new Coordonnees(100,100,0),null);
		obsA.setDiametre(20);
		Obstacle obsB = new Obstacle(new Coordonnees(120,100,0),null);
		obsB.setDiametre(20);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obsA);
		ListOb.add(obsB);
		AngryBird angry = new AngryBird(fe);
		angry.setOb(ListOb);
		assertTrue(Calcul.testSupperpositionObstacle(angry));
	}
	@Test
	public void testPasSuperpose() {
		FenetreContener fe = new FenetreContener();
		Obstacle obsA = new Obstacle(new Coordonnees(100,100,0),null);
		obsA.setDiametre(20);
		Obstacle obsB = new Obstacle(new Coordonnees(121,100,0),null);
		obsB.setDiametre(20);
		ArrayList<Obstacle> ListOb = new ArrayList<Obstacle>();
		ListOb.add(obsA);
		ListOb.add(obsB);
		AngryBird angry = new AngryBird(fe);
		angry.setOb(ListOb);
		assertFalse(Calcul.testSupperpositionObstacle(angry));
	}
	*/
}

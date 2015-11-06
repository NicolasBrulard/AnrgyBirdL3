package test;

import static org.junit.Assert.*;

import java.awt.Color;

import modele.Coordonnees;
import controleur.Bird;
import controleur.Calcul;
import controleur.Obstacle;
import vue.FenetreContener;

public class Test {

	@org.junit.Test
	public void testCalculDistance() { // Calcul de distance OK !
		assertEquals(7.07, Calcul.calculDistance(new Bird(new Coordonnees(50, 50, 0), Color.red,new FenetreContener()).getCoord(), new Obstacle(new Coordonnees(55, 55, 0), Color.blue).getC()),0.01);
	}
	
	

}

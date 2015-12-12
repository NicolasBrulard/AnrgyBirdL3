package test;

import static org.junit.Assert.assertEquals;


import java.awt.Color;


import modele.BirdModele;
import modele.Calcul;
import modele.CoordonneesModele;
import modele.ObstacleModele;
import modele.VecteurModele;

public class Test {

	@SuppressWarnings("deprecation")
	@org.junit.Test
	public void testCalculDistance() { // Calcul de distance OK !
		BirdModele bird = new BirdModele();
				bird.setCoord(new CoordonneesModele(50, 55));
				assertEquals(5.0, Calcul.calculDistance(bird.getCoord(), new ObstacleModele(55, 55,"o",new VecteurModele(5, 5)).getCoord()),0.01); 	

	 }
	
	

}

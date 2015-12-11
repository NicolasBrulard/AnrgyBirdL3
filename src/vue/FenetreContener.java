package vue;


import javax.swing.JFrame;

import modele.AngryBirdModele;
import modele.Constantes;

public class FenetreContener extends JFrame{ // Fenetre dans laquelle est contenu tous le projet

	public FenetreContener() {
		this.setSize(Constantes.fenetreX,Constantes.fenetreY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

package vue;




import javax.swing.JFrame;

import modele.Constantes;

public class FenetreContener extends JFrame{ // Fenetre dans laquelle est contenu tous le projet

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public FenetreContener() {
		this.pack();
		this.setSize(Constantes.fenetreX,Constantes.fenetreY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
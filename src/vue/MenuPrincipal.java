package vue;

import javax.swing.JPanel;

public class MenuPrincipal extends JPanel{
	
	private FenetreContener fenetre;
	
	public MenuPrincipal(FenetreContener fenetre){
		this.fenetre = fenetre;
		
		fenetre.setContentPane(this);
		fenetre.setVisible(true);
	}

}

import vue.AngryBird;
import vue.FenetreContener;


public class Main {

	public static void main(String[] args) {
		
		//SwingUtilities.invokeLater(new Runnable() {
			
			//@Override
			//public void run() {
				FenetreContener fenetre = new FenetreContener();
				AngryBird angry = new AngryBird(fenetre);
				fenetre.add(angry);
				angry.go();
			}
		//});
		
	}

package modele;

import java.util.Observable;

public class GraphismeModele extends Observable{

	private boolean graph = true;
	
	public GraphismeModele() {
		// TODO Auto-generated constructor stub
	}
	
	public void setGraph(boolean graph) {
		this.graph = graph;
		setChanged ();
		notifyObservers ();
	}
	
	public boolean getGraph(){
		return this.graph;
	}
}

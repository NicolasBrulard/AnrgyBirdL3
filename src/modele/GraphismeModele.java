package modele;

import java.util.Observable;

public class GraphismeModele extends Observable{

	private int graph = 1;
	
	public GraphismeModele() {
		// TODO Auto-generated constructor stub
	}
	
	public void setGraph(int graph) {
		this.graph = graph;
		setChanged ();
		notifyObservers ();
	}
	
	public int getGraph(){
		return this.graph;
	}
}

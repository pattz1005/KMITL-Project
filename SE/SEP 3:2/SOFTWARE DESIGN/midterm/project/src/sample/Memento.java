package sample;

import java.util.ArrayList;

/**
 * This class used to save/get state of inventory
 */
 
@SuppressWarnings("serial")
public class Memento implements java.io.Serializable{

	private ArrayList<Movie> movieCollection;
	
	public void saveState(ArrayList<Movie> newMovieCollection){
		
		this.movieCollection = new ArrayList<Movie>(newMovieCollection);
		
	}
	
	public ArrayList<Movie> getState(){
		
		return this.movieCollection;
		
	}
}

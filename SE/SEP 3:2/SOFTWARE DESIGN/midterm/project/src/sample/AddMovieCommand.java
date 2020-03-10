package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class used to execute addMovie method on inventory and serialize it to file.
 */

@SuppressWarnings("serial")
public class AddMovieCommand extends Command implements java.io.Serializable {

	private Movie movie;
	private String fileName = "Command.ser";
	
	AddMovieCommand(Movie newMovie){
	this.movie = newMovie; 
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		newInvent.addMovie(movie);
		
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream(fileName,true);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	        
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	        
	      }
	}

}

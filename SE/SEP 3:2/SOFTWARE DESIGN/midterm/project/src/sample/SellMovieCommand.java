package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class used to execute sellMovie method on inventory and serialize it to file.
 */

@SuppressWarnings("serial")
public class SellMovieCommand extends Command implements java.io.Serializable {

	private String movieName;
	private String fileName = "Command.ser";
	
	SellMovieCommand(String newMovieName){
	this.movieName = newMovieName; 
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		try {
			  newInvent.sellMovie(movieName);
		} catch (MatchNotFoundException e) {
			System.out.println(e);
		}

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

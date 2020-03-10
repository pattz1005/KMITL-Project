package sample;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class used to execute addCopy method on inventory and serialize it to file.
 */

@SuppressWarnings("serial")
public class AddCopyCommand extends Command implements java.io.Serializable {

	private String movieName;
	private Integer numberOfCopy;
	private String fileName = "Command.ser";
	
	AddCopyCommand(String newMovieName, Integer newNumberOfCopy){
	this.movieName = newMovieName; 
	this.numberOfCopy = newNumberOfCopy;
	}
	
	@Override
	public void execute(Inventory newInvent) {
		
		try {
		    newInvent.addCopy(movieName,numberOfCopy);
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

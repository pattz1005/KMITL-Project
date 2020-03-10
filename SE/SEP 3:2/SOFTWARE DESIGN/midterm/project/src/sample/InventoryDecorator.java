package sample;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class InventoryDecorator implements InventoryInterface{
	
	private Inventory invent = new Inventory();
	private FileInputStream fileIn ;
	private ArrayList<Command> commandCollection = new ArrayList<Command>();
	private CareTaker careTaker = new CareTaker();
	private Memento memento = new Memento();
	private String CommandFileName = "Command.ser";
	
	
	public Inventory getInvent() {
		
		return invent;
	}

	public void setInvent(Inventory invent) {
		
		this.invent = invent;
	}
	
	public void addMovie(Movie movie){
		
		AddMovieCommand addMovie= new AddMovieCommand(movie);
	    addMovie.execute(invent);
	}
	
	public void addCopy(String movieName, Integer numberOfCopy){
		
		AddCopyCommand addCopy = new AddCopyCommand(movieName, numberOfCopy);
		addCopy.execute(invent);
		
	}
	
	public void changePrice(String movieName, Integer newPrice){
		
		ChangePriceCommand changePrice = new ChangePriceCommand(movieName, newPrice);
		changePrice.execute(invent);
	
	}
	
	public void sellMovie(String movieName){
		
		SellMovieCommand sellMovie = new SellMovieCommand(movieName);
		sellMovie.execute(invent);
	}
	
	public Integer findPriceByName(String movieName) throws MatchNotFoundException{
		
		try {
			
			return invent.findPriceByName(movieName);
			
		} catch (MatchNotFoundException e) {
			
			throw new MatchNotFoundException("No Match Found");
		}
	}
	
	public Integer findPriceByID(Integer movieID) throws MatchNotFoundException{
		
		try {
			
			return invent.findPriceByID(movieID);
			
		} catch (MatchNotFoundException e) {
			
			throw new MatchNotFoundException("No Match Found");
		}
	}
	
	public Integer findQuantityByName(String movieName) throws MatchNotFoundException{
		
		try {
			
			return invent.findQuantityByName(movieName);
			
		} catch (MatchNotFoundException e) {
			
			throw new MatchNotFoundException("No Match Found");
		}
	}
	
	public Integer findQuantityByID(Integer movieID) throws MatchNotFoundException{
		
		try {
			 
			return invent.findQuantityByID(movieID);
			
		} catch (MatchNotFoundException e) {
			
			throw new MatchNotFoundException("No Match Found");
		}
	}
		
	/**
	   * This method get the commands from file  
	   * and also runs them to inventory object       
	   */
	
	private void replyCommands(Inventory invent){
			
		try {
			  fileIn = new FileInputStream(CommandFileName);
			  while (true) {
				ObjectInputStream input = new ObjectInputStream(fileIn);
				commandCollection.add((Command) input.readObject());
			  }
			}catch (IOException e) {

				try{
					fileIn.close();
				}catch(IOException i)
				{
					i.printStackTrace();
				}

			}catch(ClassNotFoundException c)
			{
				System.out.println("class not found");
				c.printStackTrace();
			}

		for(Command command : commandCollection){
			 command.execute(invent);
		}
	}

	@Override
	public void saveState() {
		memento.saveState(invent.getMovieCollection());
		careTaker.serialzeMemento(memento);
		File file = new File(CommandFileName);
		file.delete();
		try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void getState() {
	memento = careTaker.deserialseMemento();
	invent.setMovieCollection(memento.getState());
	this.replyCommands(invent);
	}

}
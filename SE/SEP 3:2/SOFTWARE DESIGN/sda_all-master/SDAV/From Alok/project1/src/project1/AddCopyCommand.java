/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class AddCopyCommand extends Command implements java.io.Serializable {

	private String movieName;
	private int numberOfCopy;
	private String fileName = "Command.ser";
	
	AddCopyCommand(String newMovieName, int newNumberOfCopy){
            this.movieName = newMovieName;
            this.numberOfCopy = newNumberOfCopy;
	}
	
	public void execute(Inventory newInvent) {
            try{
                newInvent.addCopy(movieName,numberOfCopy);
            }
            catch(NoSuchElementException e){
                System.out.println(e);
            }
	}
}

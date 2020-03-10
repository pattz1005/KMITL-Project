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
public class ChangePriceCommand extends Command implements java.io.Serializable {
    private String name;
    private Integer numberOfCopy;
    private String fileName = "Command.ser";

    ChangePriceCommand(String newMovieName, Integer newNumberOfCopy){
        this.name = newMovieName; 
        this.numberOfCopy = newNumberOfCopy;
    }

    public void execute(Inventory newInvent) {
        try{
            newInvent.changePrice(name,numberOfCopy);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }
}
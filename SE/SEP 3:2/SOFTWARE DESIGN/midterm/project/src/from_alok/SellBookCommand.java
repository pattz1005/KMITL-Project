/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class SellBookCommand extends Command implements java.io.Serializable {
    private String movieName;
    private String fileName = "Command.ser";
    private int num;

    SellBookCommand(String newMovieName, int num){
        this.num = num;
        this.movieName = newMovieName; 
    }

    @Override
    public void execute(Inventory newInvent) {
        try{
            newInvent.sellBook(movieName, num);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

import java.util.ArrayList;

/**
 *
 * @author alok
 */
public class Memento implements java.io.Serializable{
    private ArrayList<Book> state;
    
    public Memento(){}
    
    public Memento(ArrayList<Book> state){
        this.state = new ArrayList<Book>(state);
    }
	
    public void setState(ArrayList<Book> newBooks){
        this.state = new ArrayList<Book>(newBooks);
    }
    
    public ArrayList<Book> getState(){
        return this.state;
    }
}

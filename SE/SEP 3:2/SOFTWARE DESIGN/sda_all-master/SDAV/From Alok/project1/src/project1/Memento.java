/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alok
 */
public class ConcreteSubject implements Subject{
    private List<IAuctionListener> observers;
    public ConcreteSubject(){
        this.observers = new ArrayList<IAuctionListener>();
    }
    public void addObserver(IAuctionListener al, Item itemCopy){
        observers.add(al);
        this.notifyObservers(itemCopy);
    }
    public void removeObserver(IAuctionListener al){
        observers.remove(al);
    }
    public void notifyObservers(Item itemCopy){
        for(IAuctionListener listener:observers){
            listener.update(itemCopy);
        }
    }
}

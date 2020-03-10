/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author alok
 */
public interface Subject {
    public void addObserver(IAuctionListener al, Item itemCopy);
    public void removeObserver(IAuctionListener al);
    public void notifyObservers(Item item);
}

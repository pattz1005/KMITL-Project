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
public interface Item {
    public void setName(String name);
    public void setOwnerName(String ownerName);
    public void setDescription(String description);
    public void setHighestBidder(String bidder);
    public void setCurrentBid(double bid);
    public void setTimeLeft(int time);
    public String getName();
    public String getOwnerName();
    public String getDescription();
    public String getHighestBidder();
    public double getCurrentBid();
    public int getTimeLeft();
    public ConcreteItem deepCopy();
    public void addObserver(IAuctionListener al);
    public void removeObserver(IAuctionListener al);
    public void notifyObservers();
}

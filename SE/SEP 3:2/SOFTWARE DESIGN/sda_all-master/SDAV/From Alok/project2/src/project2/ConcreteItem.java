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
public class ConcreteItem implements Item{
    private Subject notifier;
    private String name;
    private String ownerName;
    private String description;
    private String highestBidder;
    private double currentBid;
    private int timeLeft;
    public ConcreteItem(String name, String ownerName, String description, double startBid, int timeLeft){
        this.name = new String(name);
        this.ownerName = new String(ownerName);
        this.description = new String(description);
        this.currentBid = startBid;
        this.timeLeft = timeLeft;
        this.notifier = new ConcreteSubject();
    }
    public void setName(String name){
        this.name = name;
        this.notifyObservers();
    }
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
        this.notifyObservers();
    }
    public void setDescription(String description){
        this.description = description;
        this.notifyObservers();
    }
    public void setHighestBidder(String bidder){
        this.highestBidder = bidder;
    }
    public void setCurrentBid(double bid){
        this.currentBid = bid;
        this.notifyObservers();
    }
    public void setTimeLeft(int time){
        this.timeLeft = time;
        this.notifyObservers();
    }
    public String getName(){
        return this.name;
    }
    public String getOwnerName(){
        return this.ownerName;
    }
    public String getDescription(){
        return this.description;
    }
    public String getHighestBidder(){
        return this.highestBidder;
    }
    public double getCurrentBid(){
        return this.currentBid;
    }
    public int getTimeLeft(){
        return this.timeLeft;
    }
    public ConcreteItem deepCopy(){
        String newName = new String(this.name);
        String newOwnerName = new String(this.ownerName);
        String newDescription = new String(this.description);
        double newCurrentBid = this.currentBid;
        int newTimeLeft = this.timeLeft;
        return new ConcreteItem(newName, newOwnerName, newDescription, newCurrentBid, newTimeLeft);
    }
    public void addObserver(IAuctionListener al){
        this.notifier.addObserver(al, this.deepCopy());
    }
    public void removeObserver(IAuctionListener al){
        this.notifier.removeObserver(al);
    }
    public void notifyObservers(){
        notifier.notifyObservers(this.deepCopy());
    }
    
    public String toString(){
        String r = new String();
        r = r+"Item Name: "+this.name;
        r = r+"\nOwner Name: "+this.ownerName;
        r = r+"\nDescription: "+this.description;
        r = r+"\nCurrent Bid: "+Double.toString(this.currentBid);
        r = r+"\nTime Left: "+Integer.toString(timeLeft);
        return r;
    }
}

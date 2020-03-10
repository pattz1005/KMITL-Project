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
public class AuctionServer implements IAuctionServer{
    private List<Item> items;
    private Timer timer;
    private static IAuctionServer singleInstance;
    
    private AuctionServer(){
        this.items = new ArrayList<Item>();
        this.timer = new Timer1(this);
        this.timer.startTimer();
    }
    
    public static synchronized IAuctionServer getInstance(){
        if(singleInstance==null){
            singleInstance = new AuctionServer();
        }
        return singleInstance;
    }
    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) {
        for(Item item:items){
            if(item.getName().equals(itemName)){
                throw new IllegalArgumentException("Item already Exists");
            }
        }
        Item item = new ConcreteItem(itemName, ownerName, itemDesc, startBid, auctionTime);
        items.add(item);
    }

    @Override
    public void bidOnItem(String bidderName, String itemName, double bid) {
        for(Item item:items){
            if(item.getName().equalsIgnoreCase(itemName)){
                if(item.getCurrentBid()<=bid){
                    item.setCurrentBid(bid);
                    item.setHighestBidder(bidderName);
                    item.setTimeLeft(3);
                }
                else{
                    throw new IllegalArgumentException("Bid too low");
                }
            }
        }
        throw new IllegalArgumentException("Item doesn't exist");
    }

    @Override
    public List getItems() {
        return this.items;
    }

    @Override
    public Item getItem(String itemName) {
        for(Item item:items){
            if(item.getName().equalsIgnoreCase(itemName)){
                return item;
            }
        }
        throw new IllegalArgumentException("Item doesn't exist");
    }

    @Override
    public void registerListener(IAuctionListener al, String itemName) {
        this.getItem(itemName).addObserver(al);
    }

    @Override
    public void decrementTime() {
        int i = 0;
        while(i<items.size()){
            Item item = items.get(i);
            item.setTimeLeft(item.getTimeLeft()-1);
            if(item.getTimeLeft()==0){
                if(item.getHighestBidder()!=null){
                    System.out.println(item.getName()+" sold to "+item.getHighestBidder()+".");
                    items.remove(item);
                    i--;
                }
                else{
                    System.out.println(item.getName()+" couldn't be sold.");
                    items.remove(item);
                    i--;
                }
            }
            i++;
        }
    }

    @Override
    public void endAuction() {
        this.timer.stopTimer();
    }
}

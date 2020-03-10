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
public interface IBidder {
    public void setName(String name);
    public String getName();
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime);
    public void bidOnItem(String ownerName, String itemName, double bid);
    public void observeItem(String itemName);
    public void stopObservingItem();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.List;

/**
 *
 * @author alok
 */
public interface IAuctionServer {
    public void  placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime);
    public void bidOnItem(String bidderName, String itemName, double bid);
    public List getItems();
    public Item getItem(String itemName);
    public void registerListener(IAuctionListener al, String itemName);
    public void decrementTime();
    public void endAuction();
}

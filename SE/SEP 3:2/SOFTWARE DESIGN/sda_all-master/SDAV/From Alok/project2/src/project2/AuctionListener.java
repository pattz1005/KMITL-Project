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
public class AuctionListener implements IAuctionListener, IBidder{
    private String name;
    private GUI gui;
    private IAuctionServer server;
    private String[] columnNames = {"Item", "Owner", "Current id", "Time remaining"};
    private Object[][] data = {};
    
    public AuctionListener(IAuctionServer server, String name){
        this.name = name;
        this.gui = new GUI(this);
        this.server = server;
        this.gui.setVisible(true);
    }

    @Override
    public void update(Item item) {
        Object[] row = {item.getName(), item.getOwnerName(), item.getCurrentBid(), item.getTimeLeft()};
        this.gui.updateTable(row);
    }

    @Override
    public void placeItemForBid(String ownerName, String itemName, String itemDesc, double startBid, int auctionTime) {
        try{
            this.server.placeItemForBid(ownerName, itemName, itemDesc, startBid, auctionTime);
            this.server.registerListener(this, itemName);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void bidOnItem(String ownerName, String itemName, double bid) {
        try{
        this.server.bidOnItem(ownerName, itemName, bid);
        }
        catch(Exception e){
             System.out.println(e);
        }
    }

    @Override
    public void observeItem(String itemName) {
        try{
            this.server.registerListener(this, itemName);
        }
        catch(Exception e){
             System.out.println(e);
        }
    }

    @Override
    public void stopObservingItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
}

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
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AbstractServerFactory factory = new ServerFactory();
        IAuctionServer server = factory.createServer();
        IAuctionListener listener1 = new AuctionListener(server, "udah");
        IAuctionListener listener2 = new AuctionListener(server, "soidh");
    }
}

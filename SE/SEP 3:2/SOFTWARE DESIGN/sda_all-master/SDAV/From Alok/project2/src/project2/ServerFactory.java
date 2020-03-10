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
public class ServerFactory implements AbstractServerFactory{
    public IAuctionServer createServer(){
        return AuctionServer.getInstance();
    }
}

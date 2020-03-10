/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alok
 */
public class Timer1 implements Timer, Runnable{
    private Thread t;
    private IAuctionServer server;
    private boolean counting;
    public Timer1(IAuctionServer server){
        this.server = server;
        this.counting = true;
    }

    @Override
    public void run() {
        while(counting){
            server.decrementTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void startTimer() {
        this.counting = true;
        if(t==null){
            t = new Thread (this);
            t.start();
        }
    }

    @Override
    public void stopTimer() {
        this.counting = false;
    }
    
}

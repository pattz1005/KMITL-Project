/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author alok
 */
public class Flock implements Quackable{
    private ArrayList<Quackable> quackers;
    
    public Flock(){
        quackers = new ArrayList<Quackable>();
    }
    
    public void add(Quackable quacker){
        quackers.add(quacker);
    }
    
    @Override
    public void quack() {
        for(Quackable quacker:quackers){
            quacker.quack();
        }
    }
    
    public Iterator getIterator(){
        return quackers.iterator();
    }

    @Override
    public void registerObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notibyObservers() {
        for(QuackObservable quacker:quackers){
            quacker.notibyObservers();
        }
    }

    @Override
    public String getName() {
        return "Flock";
    }
}

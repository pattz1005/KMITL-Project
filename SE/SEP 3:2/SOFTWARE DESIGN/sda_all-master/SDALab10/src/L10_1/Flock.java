package L10_1;

import java.util.ArrayList;
import java.util.Iterator;

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
    public void notifyObservers() {
        for(QuackObservable quacker:quackers){
            quacker.notifyObservers();
        }
    }

    @Override
    public String getName() {
        return "L10_1.Flock";
    }
}

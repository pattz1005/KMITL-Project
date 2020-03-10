package L10_1;

import java.util.ArrayList;

public class Observable implements QuackObservable{
    private Quackable quacker;
    private ArrayList<Observer> observers;
    
    public Observable(Quackable q){
        this.quacker = q;
        observers = new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer:observers){
            observer.update(quacker);
        }
    }
}

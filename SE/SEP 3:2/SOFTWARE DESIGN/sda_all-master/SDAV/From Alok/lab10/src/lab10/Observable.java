/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

import java.util.ArrayList;

/**
 *
 * @author alok
 */
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
    public void notibyObservers() {
        for(Observer observer:observers){
            observer.update(quacker);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author alok
 */
public class QuackCounter implements Quackable{
    private Quackable quacker;
    private Observable observable;
    private static int quackCount;
    
    public QuackCounter(Quackable quacker){
        this.quacker = quacker;
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        quacker.quack();
        observable.notibyObservers();
        quackCount++;
    }
    
    public static int getQuacks(){
        return quackCount;
    }

    @Override
    public void registerObserver(Observer o) {
        observable.registerObserver(o);
    }

    @Override
    public void notibyObservers() {
        observable.notibyObservers();
    }

    @Override
    public String getName() {
        return this.quacker.getName();
    }
}

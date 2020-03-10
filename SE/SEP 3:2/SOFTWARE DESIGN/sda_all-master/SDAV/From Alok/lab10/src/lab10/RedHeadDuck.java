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
public class RedHeadDuck implements Quackable{
    Observable observable;

    public RedHeadDuck(){
        this.observable = new Observable(this);
    }
    @Override
    public void quack() {
        System.out.println("Red head duck quack");
        observable.notibyObservers();
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
        return "Red head duck";
    }
    
}

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
public class GooseAdapter implements Quackable{
    private Goose goose;
    Observable observable;
    
    public GooseAdapter(Goose goose){
        this.goose = goose;
        this.observable = new Observable(this);
    }
    
    @Override
    public void quack() {
        goose.honk();
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
        return "Goose";
    }
}

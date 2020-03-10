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
public interface QuackObservable {
    public void registerObserver(Observer o);
    public void notibyObservers();
}

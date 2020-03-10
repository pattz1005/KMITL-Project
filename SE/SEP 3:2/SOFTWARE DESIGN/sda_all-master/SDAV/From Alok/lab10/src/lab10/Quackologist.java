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
public class Quackologist implements Observer{

    @Override
    public void update(QuackObservable q) {
        Quackable quacker = (Quackable) q;
        System.out.println(quacker.getName()+" just quacked");
    }
    
}

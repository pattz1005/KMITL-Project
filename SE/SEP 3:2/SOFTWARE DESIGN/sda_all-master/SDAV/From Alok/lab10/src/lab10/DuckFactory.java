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
public class DuckFactory implements AbstractDuckFactory{
    public Quackable createMallardDuck(){
        return new MallardDuck();
    }
    public Quackable createRedHeadDuck(){
        return new RedHeadDuck();
    }
    public Quackable createDuckCall(){
        return new DuckCall();
    }
    public Quackable createRubberDuck(){
        return new RubberDuck();
    }
}

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
public class CountingDuckFactory implements AbstractDuckFactory{
    public Quackable createMallardDuck(){
        return new QuackCounter(new MallardDuck());
    }
    public Quackable createRedHeadDuck(){
        return new QuackCounter(new RedHeadDuck());
    }
    public Quackable createDuckCall(){
        return new QuackCounter(new DuckCall());
    }
    public Quackable createRubberDuck(){
        return new QuackCounter(new RubberDuck());
    }
}

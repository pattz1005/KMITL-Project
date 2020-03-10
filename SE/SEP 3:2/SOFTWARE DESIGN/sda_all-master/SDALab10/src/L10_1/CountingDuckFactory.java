package L10_1;

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

package L10_1;

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

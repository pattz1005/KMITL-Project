package L10_1;

public class MallardDuck implements Quackable{
    Observable observable;
    
    public MallardDuck(){
        this.observable = new Observable(this);
    }
    public void quack() {
        System.out.println("Mallard duck quack");
        observable.notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer o) {
        observable.registerObserver(o);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }

    @Override
    public String getName() {
        return "Mallard duck";
    }
}

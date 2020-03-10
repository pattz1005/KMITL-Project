package L10_1;

public class RubberDuck  implements Quackable{
    Observable observable;
    
    public RubberDuck(){
        this.observable = new Observable(this);
    }
    @Override
    public void quack() {
        System.out.println("Rubber ducks don't quack");
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
        return "Rubber duck";
    }
}

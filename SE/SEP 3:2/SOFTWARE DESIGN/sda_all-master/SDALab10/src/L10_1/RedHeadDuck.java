package L10_1;

public class RedHeadDuck implements Quackable{
    Observable observable;

    public RedHeadDuck(){
        this.observable = new Observable(this);
    }
    @Override
    public void quack() {
        System.out.println("Red head duck quack");
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
        return "Red head duck";
    }
    
}

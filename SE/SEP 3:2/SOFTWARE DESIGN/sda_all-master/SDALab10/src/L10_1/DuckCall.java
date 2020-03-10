package L10_1;

public class DuckCall  implements Quackable{
    Observable observable;
    
    public DuckCall(){
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("Duck call quack");
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
        return "Duck call";
    }

}

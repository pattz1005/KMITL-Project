package L10_1;

public class GooseAdapter implements Quackable{
    private Goose goose;
    Observable observable;
    
    public GooseAdapter(Goose goose){
        this.goose = goose;
        this.observable = new Observable(this);
    }
    
    @Override
    public void quack() {
        goose.honk();
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
        return "L10_1.Goose";
    }
}

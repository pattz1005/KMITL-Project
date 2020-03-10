package L10_1;

public class QuackCounter implements Quackable{
    private Quackable quacker;
    private Observable observable;
    private static int quackCount;
    
    public QuackCounter(Quackable quacker){
        this.quacker = quacker;
        this.observable = new Observable(this);
    }

    @Override
    public void quack() {
        quacker.quack();
        observable.notifyObservers();
        quackCount++;
    }
    
    public static int getQuacks(){
        return quackCount;
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
        return this.quacker.getName();
    }
}

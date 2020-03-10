package L10_1;

public interface QuackObservable {
    public void registerObserver(Observer o);
    public void notifyObservers();
}

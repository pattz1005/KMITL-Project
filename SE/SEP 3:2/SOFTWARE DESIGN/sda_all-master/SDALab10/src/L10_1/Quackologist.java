package L10_1;

public class Quackologist implements Observer{

    @Override
    public void update(QuackObservable q) {
        Quackable quacker = (Quackable) q;
        System.out.println(quacker.getName()+" just quacked");
    }
    
}

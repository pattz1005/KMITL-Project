package L10_1;

public class DuckSimulator {
    public Quackable makeDuck(AbstractDuckFactory factory){
        return factory.createDuckCall();
    }
}

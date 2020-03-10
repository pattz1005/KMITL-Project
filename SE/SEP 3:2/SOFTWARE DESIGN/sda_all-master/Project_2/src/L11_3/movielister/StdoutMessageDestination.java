package L11_3.movielister;

public class StdoutMessageDestination implements MessageDestination {

    public void write(String message) {
        System.out.println(message);
    }

}

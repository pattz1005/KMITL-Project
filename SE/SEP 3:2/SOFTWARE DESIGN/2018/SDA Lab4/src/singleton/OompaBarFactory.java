package singleton;

public class OompaBarFactory implements Factory {

    private int counter = 0;

    private static OompaBarFactory uniqueInstance = new OompaBarFactory(); // = new OompaBarFactory();

    private OompaBarFactory() {
    }

    public synchronized Bar create(int id) {
        Bar bar = new OompaBar(counter++);
        System.out.println(id + " creates new Oompa bar created with id:" + counter);
        return bar;
    }

    public static OompaBarFactory getInstance() {
        /*
		if (uniqueInstance==null) {
			uniqueInstance= new WonkaBarFactory();
		}
         */
        return uniqueInstance;
    }
}

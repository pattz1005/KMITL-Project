package singleton;

public class BarProducer extends Thread {

    Factory wonka;

    public int count = 0; // each producer creates 50 bars
    public int id;

    public BarProducer(int identifier, Factory f) {
        id = identifier;
        wonka = f;
        System.out.println("creating new Bar Producer with ID:" + id);
    }

    public void run() {
        while (count < 50) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
            count++;
            //System.out.println(id+":running");
            wonka.create(id);
        }
    }
}

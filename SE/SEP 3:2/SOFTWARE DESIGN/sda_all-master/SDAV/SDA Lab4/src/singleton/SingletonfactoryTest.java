package singleton;
public class SingletonfactoryTest {
	public static void main(String[] args) {
		BarProducer t1 = new BarProducer(1, WonkaBarFactory.getInstance());
		BarProducer t2 = new BarProducer(2, WonkaBarFactory.getInstance());
                BarProducer t3 = new BarProducer(3, OompaBarFactory.getInstance());
                BarProducer t4 = new BarProducer(4, OompaBarFactory.getInstance());
		t1.start();
		t2.start();
                t3.start();
                t4.start();
		//t1.interrupt();
		//t1.interrupt();
	}
}

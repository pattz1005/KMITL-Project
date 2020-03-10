package push;

import java.util.Observable;
import java.util.Observer;

public class BabyMonitorSimple implements Observer {

	private Observable mdata;
	private String name;
	private boolean crying;
	
	public BabyMonitorSimple(String location, Baby d) {
		this.mdata=d;
		this.name=location;
		mdata.addObserver(this);
	}
		
	public void display() {
		if (crying) {
			System.out.println("Monitor:" + name + " baby is crying");
		}
	}
	
	public void turnOff() {
		mdata.deleteObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		this.crying = (boolean) arg;
	}
}

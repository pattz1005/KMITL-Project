package push;

public class BabyMonitorAdvanced implements Observer {
	private Subject mdata, cdata;
	private String babyname;
	private String name;
	private boolean crying;
	private int level;
	
	public BabyMonitorAdvanced(String name, Baby m, Baby c) {
		this.name=name; this.mdata=m; this.cdata=c;
		mdata.registerObserver(this);cdata.registerObserver(this);
	}

	public void update(String name, boolean crying, int level) {
		this.babyname=name;
		this.crying=crying;
		this.level=level;
		display();
	}
	
	public void display() {
		if (crying) {
			System.out.println("Monitor:"+ name + " baby: " + babyname + " is crying at level: " + level);
		}
	}
}

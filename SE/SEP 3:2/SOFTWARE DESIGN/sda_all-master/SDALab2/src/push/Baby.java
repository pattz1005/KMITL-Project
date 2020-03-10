package push;

import java.util.ArrayList;
import java.util.Observable;

public class Baby extends Observable {

	private ArrayList observers;
	private boolean crying=false;
	private int level=0;
	private String babyname;
	
	public Baby(String name){
		this.babyname=name;
		observers=new ArrayList();
	}

	public boolean isCrying() {
		return crying;
	}

	public void setData(boolean crying, int level) {
		this.crying=crying;
		this.level=level;
		notifyObservers(this.crying);
	}

}

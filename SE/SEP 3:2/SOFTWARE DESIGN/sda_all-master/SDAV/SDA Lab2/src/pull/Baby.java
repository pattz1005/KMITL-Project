package pull;

import java.util.ArrayList;

public class Baby implements Subject {

	private ArrayList observers;
	private boolean crying=false;
	private int level=0;
	private String babyname;
	
	public Baby(String name){
		this.babyname=name;
		observers=new ArrayList();
	}
	
	public void notifyObservers() {
		for (int i=0; i< observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(this);
		}
	}

	public void registerObserver(Observer o) {
		observers.add(o);

	}

	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >=0) {
			observers.remove(i);
		}
	}
	
	public void setData(boolean crying, int level) {
		this.crying=crying;
		this.level=level;
		notifyObservers();
	}
        
        public boolean getCrying() {
            return this.crying;
        }
        
        public int getLevel() {
            return this.level;
        }
        
        public String getName() {
            return this.babyname;
        }
}

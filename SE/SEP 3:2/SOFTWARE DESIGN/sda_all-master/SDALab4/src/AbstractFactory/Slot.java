package AbstractFactory;
import java.util.ArrayList;

public abstract class Slot {
	
	Cabinet cabinet;
	Display display;
	String name;
	String software = "linux";
	ArrayList components = new ArrayList();
	
	abstract void build();
	
	void collectParts() {
		System.out.println("fetching components:");
	}
	void assembleParts() {
		System.out.println("assembling components");
	}
	void test(String type) {
		if (type.equals("hardware")) System.out.println("testing hardware");
		else System.out.println("testing software");
	}
	void uploadSoftware() {
		System.out.println("uploading software:" + software);
	}
	void ship() {
		System.out.println("wrapping the slot machine in Ceram Wrap and shipping it with UPS");
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name=n;
	}
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + name + " ----\n");
		if (cabinet != null) {
			result.append(cabinet);
			result.append("\n");
		}
		if (display != null) {
			result.append(display);
			result.append("\n");
		}
		return result.toString();
	}
}
package push;

public class TestBabyMonitor {
	public static void main(String[] args) {
				Baby marla = new Baby("marla");
				// one monitor with one baby
				BabyMonitorSimple livingRoom = new BabyMonitorSimple("kitchen ", marla);
				marla.setData(true, 1);
				// one monitor listening to two babies
				Baby charlie = new Baby("Charlie");

			}
	}

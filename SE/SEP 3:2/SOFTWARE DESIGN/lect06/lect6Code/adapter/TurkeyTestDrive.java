import java.util.LinkedList;
import java.util.List;

public class TurkeyTestDrive {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();
		Turkey duckAdapter = new DuckAdapter(duck);

                List<Turkey> turkies = new LinkedList<Turkey>();
                turkies.add(new WildTurkey());
                turkies.add(duckAdapter);

                for (Turkey t : turkies) {
                   t.gobble();
                   t.fly();
                }
 
	}
}

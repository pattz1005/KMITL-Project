package xmastree;
public class LEDs extends TreeDecorator {

		public LEDs(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "LEDs | ";
		}

		public double cost() {
			return tree.cost() + 10;
	    }

}

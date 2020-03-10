package xmastree;
public class Lights extends TreeDecorator {

		public Lights(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Lights | ";
		}

		public double cost() {
			return tree.cost() + 5;
	    }

}

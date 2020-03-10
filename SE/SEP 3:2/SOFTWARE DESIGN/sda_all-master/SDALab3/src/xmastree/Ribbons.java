package xmastree;
public class Ribbons extends TreeDecorator {

		public Ribbons(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Ribbons | ";
		}

		public double cost() {
			return tree.cost() + 2;
	    }

}


public class Ruffles extends TreeDecorator {
	
		public Ruffles(Tree tree) {
			this.tree=tree;
		}
		
		public String getDescription() {
			return tree.getDescription() + ", Ruffles";
		}

		public double cost() {
			return 1 + tree.cost();
    	}
}

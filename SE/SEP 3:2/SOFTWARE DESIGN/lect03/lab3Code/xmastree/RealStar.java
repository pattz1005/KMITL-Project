
public class RealStar extends TreeDecorator {
		
		public RealStar(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Star";
		}

		public double cost() {
			return tree.cost() + 4;
	    }

}

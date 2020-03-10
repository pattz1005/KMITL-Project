package xmastree;
public class BallsBlue extends TreeDecorator {

		public BallsBlue(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Balls-Blue | ";
		}

		public double cost() {
			return tree.cost() + 2;
	    }

}

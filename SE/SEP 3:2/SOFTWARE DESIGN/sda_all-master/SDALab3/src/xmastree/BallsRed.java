package xmastree;
public class BallsRed extends TreeDecorator {

		public BallsRed(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Balls-Red | ";
		}

		public double cost() {
			return tree.cost() + 1;
	    }

}

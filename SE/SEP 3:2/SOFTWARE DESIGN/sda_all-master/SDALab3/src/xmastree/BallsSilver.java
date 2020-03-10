package xmastree;
public class BallsSilver extends TreeDecorator {

		public BallsSilver(Tree t) {
				this.tree=t; 
		}
		
		public String getDescription() {
			return tree.getDescription() + "Balls-Silver | ";
		}

		public double cost() {
			return tree.cost() + 3;
	    }

}

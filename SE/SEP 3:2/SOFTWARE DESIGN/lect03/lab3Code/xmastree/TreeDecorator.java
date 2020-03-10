public abstract class TreeDecorator extends Tree {
	    public Tree tree;
		public abstract String getDescription();
		public abstract double cost();
		public void setStar() {
			tree.setStar();
		}
		public boolean hasStar() {
			return tree.hasStar();
		}
}

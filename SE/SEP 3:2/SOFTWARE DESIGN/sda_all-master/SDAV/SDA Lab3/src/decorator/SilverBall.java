package decorator;

public class SilverBall extends TreeDecorator {

    public SilverBall(Tree tree) {
        this.tree = tree;
    }

    public String getDescription() {
        return tree.getDescription() + ", SilverBall";
    }

    public double cost() {
        return 3 + tree.cost();
    }
}

package decorator;

public class BlueBall extends TreeDecorator {

    public BlueBall(Tree tree) {
        this.tree = tree;
    }

    public String getDescription() {
        return tree.getDescription() + ", BlueBall";
    }

    public double cost() {
        return 2 + tree.cost();
    }
}

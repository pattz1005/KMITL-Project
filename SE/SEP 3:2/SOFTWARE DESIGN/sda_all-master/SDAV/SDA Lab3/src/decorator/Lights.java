package decorator;

public class Lights extends TreeDecorator {

    public Lights(Tree tree) {
        this.tree = tree;
    }

    public String getDescription() {
        return tree.getDescription() + ", Lights";
    }

    public double cost() {
        return 5 + tree.cost();
    }
}

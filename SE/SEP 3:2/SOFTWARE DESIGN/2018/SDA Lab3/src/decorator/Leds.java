package decorator;

public class Leds extends TreeDecorator {

    public Leds(Tree tree) {
        this.tree = tree;
    }

    public String getDescription() {
        return tree.getDescription() + ", Leds";
    }

    public double cost() {
        return 10 + tree.cost();
    }
}

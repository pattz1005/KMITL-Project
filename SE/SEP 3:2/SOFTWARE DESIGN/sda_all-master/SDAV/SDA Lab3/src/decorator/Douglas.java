package decorator;

public class Douglas extends Tree {

    public Douglas() {
        description = "Douglas tree decorated with";
    }

    public double cost() {
        return 15;
    }
}

package decorator;

public class Balsam extends Tree {

    public Balsam() {
        description = "Balsam tree decorated with";
    }

    public double cost() {
        return 5;
    }
}

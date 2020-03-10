package decorator;

public class Test_tree {

    public static void main(String[] args) {
        Tree mytree = new Balsam();
        mytree = new Star(mytree);
        mytree = new Ruffles(mytree);
        mytree = new Star(mytree);
        mytree = new BlueBall(mytree);
        mytree = new RedBall(mytree);
        mytree = new SilverBall(mytree);
        mytree = new Ribbons(mytree);
        mytree = new Lights(mytree);
        mytree = new Leds(mytree);
        mytree = new Leds(mytree);
        
        System.out.println(mytree.getDescription() + " costs:" + mytree.cost());
    }
}

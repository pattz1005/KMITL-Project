package xmastree;
public class Test_tree {
	
	public static void main(String[] args) {
		System.out.println("Tree 1 - Blue Spruce");
		Tree mytree = new BlueSpruce();
		mytree = new Star(mytree);
		mytree = new Star(mytree);
		mytree = new Ruffles(mytree);
		mytree = new Ribbons(mytree);
		mytree = new LEDs(mytree);
		mytree = new BallsBlue(mytree);
		System.out.println(mytree.getDescription() + "\n costs:" +mytree.cost());

		System.out.println("Tree 2 - Douglas Fir");
		Tree mytree2 = new BlueSpruce();
		mytree2 = new Star(mytree2);
		mytree2 = new Ruffles(mytree2);
		mytree2 = new BallsRed(mytree2);
		mytree2 = new Lights(mytree2);
		mytree2 = new BallsSilver(mytree2);
		System.out.println(mytree2.getDescription() + "\n costs:" +mytree2.cost());
	}
}

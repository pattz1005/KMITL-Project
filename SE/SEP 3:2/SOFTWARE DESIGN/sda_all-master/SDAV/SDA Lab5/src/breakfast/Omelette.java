package breakfast;

public class Omelette extends EggRecipe {
	
	public void prepareEggs() {
		System.out.println("Stirring the eggs");
	}
	
	public void cook() {
		System.out.println("Flipping the omelette while cooking");
	}

}

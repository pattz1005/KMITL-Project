package breakfast;

public class ScrambledEggs extends EggRecipe {
    
        public boolean isAddSaltPepper() {
            return false;
        }
	
	public void prepareEggs() {
		System.out.println("Stirring and adding milk to the eggs");
	}
	
	public void cook() {
		System.out.println("Scrambling the eggs.");
	}
}

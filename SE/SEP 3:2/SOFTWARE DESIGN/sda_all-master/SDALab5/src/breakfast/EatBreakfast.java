package breakfast;

public class EatBreakfast {

    public static void main(String[] args) {
        System.out.println("\nScrambled Eggs:");
        EggRecipe scrambled = new ScrambledEggs();
        scrambled.make(2);

        System.out.println("\nOmelette:");
        EggRecipe omelette = new Omelette();
        omelette.make(3);

        System.out.println("\nSunny Side:");
        EggRecipe sunny = new SunnySide();
        sunny.make(1);
    }

}

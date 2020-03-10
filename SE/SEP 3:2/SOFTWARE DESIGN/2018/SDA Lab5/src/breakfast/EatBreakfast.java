package breakfast;

public class EatBreakfast {

    public static void main(String[] args) {
        System.out.println("***************************");
        System.out.println("Making Scramble Eggs");
        EggRecipe scrambled = new ScrambledEggs();
        scrambled.make(2);

        System.out.println("***************************");
        System.out.println("Making Omelette");
        EggRecipe omelette = new Omelette();
        omelette.make(3);

        System.out.println("***************************");
        System.out.println("Making SunnySide");
        EggRecipe sunny = new SunnySide();
        sunny.make(1);
    }

}

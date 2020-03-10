package builder;

public class BurgerMealBuilder extends MealBuilder {

    @Override
    public void buildEntree() {
        theMeal.setEntree("burger");
    }

    @Override
    public void buildDrink() {
        theMeal.setDrink("cola");
    }

    @Override
    public void buildSide() {
        theMeal.setSide("fries");
    }

}

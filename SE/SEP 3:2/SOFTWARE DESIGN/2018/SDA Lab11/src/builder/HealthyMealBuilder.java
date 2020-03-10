package builder;

public class HealthyMealBuilder extends MealBuilder {
    
    @Override
    public void buildEntree() {
        theMeal.setEntree("chicken sandwich");
    }

    @Override
    public void buildDrink() {
        theMeal.setDrink("diet cola");
    }

    @Override
    public void buildSide() {
        theMeal.setSide("carrot sticks");
    }
}

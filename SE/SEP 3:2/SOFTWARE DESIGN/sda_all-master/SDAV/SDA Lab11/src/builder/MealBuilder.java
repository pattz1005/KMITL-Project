package builder;

/**
 * Abstract builder for the Meal objects, supporting all the operations for
 * building a meal.
 */
public abstract class MealBuilder {

    protected Meal theMeal;

    public Meal getMeal() {
        return this.theMeal;
    }
    
    public void createMeal() {
        this.theMeal = new Meal();
    }
    
    public abstract void buildEntree();
    public abstract void buildDrink();
    public abstract void buildSide();
}

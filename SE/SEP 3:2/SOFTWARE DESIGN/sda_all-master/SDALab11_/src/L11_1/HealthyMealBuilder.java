/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L11_1;

/**
 *
 * @author boonjv
 */
public class HealthyMealBuilder extends MealBuilder {


    @Override
    public void buildEntree() {
        theMeal.setEntree("chicken sandwich");
    }

    @Override
    public void buildDrink() {
        theMeal.setDrink("a diet cola");
    }

    @Override
    public void buildSide() {
        theMeal.setSide("carrot sticks");
    }
}

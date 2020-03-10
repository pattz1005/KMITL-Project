package L11_1;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author boonjv
 */
/** Concrete builder for a meal with a burger, fries, and a cola. */
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
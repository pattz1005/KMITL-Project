/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

/**
 *
 * @author alok
 */
public class Project1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        InventoryDecorator inventory = new InventoryDecorator();
        InventoryDecorator inventory2 = new InventoryDecorator();
        inventory.newBook(new ConcreteBook("abc", 2, 1, 3));
        careTaker.addMemento(inventory.getMemento(), "Memento.ser");
        //inventory.resetCommands();
        /*Checkpoint 1*/
        inventory.newBook(new ConcreteBook("pqr", 2, 2, 3));
        inventory.newBook(new ConcreteBook("xyz", 3, 3, 3));
        inventory.addCopy("pqr", 1);
        inventory.sellBook("xyz", 1);
        /*Reloading last memento save*/
        inventory2.setFromMemento(careTaker.get("Memento.ser"));
        /*Replaying commands*/
        inventory2.replayCommands();
        System.out.println(inventory2);
        
    }
}

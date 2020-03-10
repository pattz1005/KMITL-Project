package Store;

import java.io.IOException;

public class Memento_Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Initialization
        System.out.println("------------------------------");
        System.out.println("INVENTORY. INITIALIZATION");
        CareTaker careTaker = new CareTaker();
        Inventory_Decorator inventory = new Inventory_Decorator();
        inventory.addBook(new Book("SVV", 10, 5));
        inventory.addBook(new Book("SEP", 20, 6));
        System.out.println("INVENTORY: MEMENTO SAVE");
        System.out.println("INVENTORY: FIRST LISTING");
        inventory.print();
        careTaker.add(new Memento(inventory.getBooksList()));
        inventory.resetCommands();
        inventory.addBook(new Book("SDA", 1, 1));
        inventory.addCopy(0, 3);
        inventory.sellBook(1, 4);
        inventory.changeBookPrice(1, 23);
        System.out.println("INVENTORY: LISTING BEFORE EXIT");
        inventory.print();
        System.out.println("INVENTORY: EXIT");
        System.out.println("------------------------------");
        System.out.println("INVENTORY: RELOAD INVENTORY INITIALIZATION");
        Inventory_Decorator new_inventory = new Inventory_Decorator();
        System.out.println("INVENTORY: GET FROM MEMENTO");
        new_inventory.setFromMemento(careTaker.get());
        /*Replaying commands*/
        System.out.println("INVENTORY: REPLAY COMMANDS");
        new_inventory.replayCommands();
        System.out.println("INVENTORY: LISTING AFTER RESTORE");
        new_inventory.print();
        System.out.println("------------------------------");
        System.out.println("INVENTORY: CHECK AVAILABILITY");
        System.out.println("--Check price by [ID = 1, Name = SEP]");
        new_inventory.findPrice(1);
        new_inventory.findPrice("SEP");
        System.out.println("--Check quantity by [ID = 1, Name = SEP]");
        new_inventory.findQuantity(1);
        new_inventory.findQuantity("SEP");
    }
}
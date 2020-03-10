package Store;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Inventory_Decorator implements Inventory {
    public Inventory inventory = new Inventory_Store();
    public CareTaker careTaker = new CareTaker();
    public Memento memento;
    ArrayList<Command> commands = new ArrayList<>();
    ArrayList<Command> storeCommands = new ArrayList<>();
    private String cmdfile = "cmdfile";
    private FileInputStream fileIn;

    public Inventory_Decorator() {
        this.inventory = inventory;
    }


    @Override
    public void addBook(Book b) throws IOException, ClassNotFoundException {
        Command_AddBook cmd = new Command_AddBook(b);
        cmd.execute(inventory);
        commands.add(cmd);
        this.saveCommands();
    }

    @Override
    public void sellBook(int bookId, int quantity) throws IOException, ClassNotFoundException {
        Command_SellBook cmd = new Command_SellBook(bookId, quantity);
        cmd.execute(inventory);
        commands.add(cmd);
        this.saveCommands();
    }

    @Override
    public void addCopy(int bookId, int quantity) throws IOException, ClassNotFoundException {
        Command_AddCopy cmd = new Command_AddCopy(bookId, quantity);
        cmd.execute(inventory);
        commands.add(cmd);
        this.saveCommands();
    }

    @Override
    public void changeBookPrice(int bookId, double newPrice) throws IOException, ClassNotFoundException {
        Command_ChangePrice cmd = new Command_ChangePrice(bookId, newPrice);
        cmd.execute(inventory);
        commands.add(cmd);
        this.saveCommands();
    }

    public double findPrice(String name) {
        for (Book b : inventory.getBooksList()) {
            if (b.getName().equals(name)) {
                System.out.println("[Find Price]\t\tBook# " + b.getBookId() + " " + b.getName() + " Price: " + b.getPrice());
                return b.getPrice();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public double findPrice(int id) {
        for (Book b : inventory.getBooksList()) {
            if (b.getBookId() == id) {
                System.out.println("[Find Price]\t\tBook# " + b.getBookId() + " " + b.getName() + " Price: " + b.getPrice());
                return b.getPrice();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public int findQuantity(String name) {
        for (Book b : inventory.getBooksList()) {
            if (b.getName().equals(name)) {
                System.out.println("[Find Quantity]\t\tBook# " + b.getBookId() + " " + b.getName() + " Qty.: " + b.getQuantity());
                return b.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public int findQuantity(int id) {
        for (Book b : inventory.getBooksList()) {
            if (b.getBookId() == id) {
                System.out.println("[Find Quantity]\t\tBook# " + b.getBookId() + " " + b.getName() + " Qty.: " + b.getQuantity());
                return b.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public void resetCommands() {
        File file = new File(cmdfile);
        commands.clear();
        file.delete();
    }

    public void saveCommands() throws IOException, ClassNotFoundException {
        File file = new File(cmdfile);
        file.delete();
        FileOutputStream f = new FileOutputStream(cmdfile);
        ObjectOutputStream obj = new ObjectOutputStream(f);
        obj.writeObject(commands);
        f.close();
        obj.close();
    }

    public void replayCommands() throws IOException, ClassNotFoundException {
        ArrayList<Command> newCommands = new ArrayList<Command>();
        try {
            fileIn = new FileInputStream(cmdfile);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            newCommands = ((ArrayList<Command>) input.readObject());
        } catch (EOFException e) {

            try {
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            }

        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
        }
        for (Command command : newCommands) {
            command.execute(inventory);
        }
    }

    /////////////////////////////////////////////////////////////////////////
    @Override
    public void getState() throws IOException, ClassNotFoundException {
        memento = careTaker.get();
        inventory.setBooksList(memento.getState());
        this.replayCommands();
    }

    @Override
    public void saveState() throws IOException {
        memento.saveState(inventory.getBooksList());
        careTaker.add(memento);
    }
    //////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<Book> getBooksList() {
        return (ArrayList<Book>) inventory.getBooksList().clone();
    }

    @Override
    public void setBooksList(ArrayList<Book> bookList) {
        this.inventory.setBooksList(bookList);
    }

    public Memento getMemento() {

        return new Memento(((Inventory_Store) inventory).getBooksList());
    }

    public void setFromMemento(Memento memento) {
        ((Inventory_Store) inventory).setBooksList(memento.getState());
    }

    @Override
    public void print() {
        for (Book b : inventory.getBooksList()) {
            System.out.println(b);
        }
    }
}

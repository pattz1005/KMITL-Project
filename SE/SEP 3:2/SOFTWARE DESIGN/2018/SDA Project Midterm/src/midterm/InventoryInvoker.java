package midterm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class InventoryInvoker {

    private Inventory inventory;
    private CareTaker careTaker;
    private ArrayList<Observer> observers;
    private String folder_path = "C:\\Users\\viray\\Documents\\Projects\\SDA Project Midterm\\database\\";
    private String suffix = "_Command.ser";

    public InventoryInvoker() {
        inventory = new Inventory();
        careTaker = new CareTaker();
        observers = new ArrayList<Observer>();
        Memento latest = careTaker.getLatestMemento();
        if (latest != null) {
            inventory.setMemento(latest);
        }
        String latestMementoTime = careTaker.getLatestMementoTime();
        File folder = new File(folder_path);
        for (File f : folder.listFiles()) {
            if (f.getName().contains(suffix)) {
                if (latestMementoTime.compareTo(f.getName().replaceAll("[^0-9]", "")) < 0) {
                    try {
                        FileInputStream fileIn = new FileInputStream(f.toString());
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        Command c = (Command) in.readObject();
                        c.execute(inventory);
                        in.close();
                        fileIn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObserver() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void addBook(Book b) {
        Command c = new AddBookCommand(b);
        c.execute(inventory);
        saveCommand(c);
        notifyObserver();
    }

    public void sellBook(int book_id, int quantity) {
        Command c = new SellBookCommand(book_id, quantity);
        c.execute(inventory);
        saveCommand(c);
        notifyObserver();
    }

    public void editBook(int book_id, int quantity, double price, String name) {
        Command c = new EditBookCommand(book_id, quantity, price, name);
        c.execute(inventory);
        saveCommand(c);
        notifyObserver();
    }

    public ArrayList<Book> searchBook(String term) {
        return inventory.searchBook(term);
    }

    public void saveState() {
        careTaker.addMemento(inventory.createMemento());
    }

    private void saveCommand(Command c) {
        try {
            Date date = new Date();
            String filename = folder_path + date.getTime() + suffix;
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(c);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBooks() {
        return inventory.getBooks();
    }

}

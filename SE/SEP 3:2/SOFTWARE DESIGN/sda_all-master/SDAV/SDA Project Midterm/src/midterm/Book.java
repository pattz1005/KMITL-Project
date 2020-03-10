package midterm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Book implements Serializable {

    private String name;
    private double price;
    private int quantity;
    private int id;
    private static String id_counter_path = "C:\\Users\\viray\\Documents\\Projects\\SDA Project Midterm\\database\\id_counter.ser";

    public Book(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        try {
            FileInputStream fileIn = new FileInputStream(id_counter_path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.id = (Integer) in.readObject();
            in.close();
            fileIn.close();
            
            FileOutputStream fileOut = new FileOutputStream(id_counter_path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new Integer(this.id + 1));
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() {
        return id;
    }

}

///done
package Store;

import java.io.Serializable;

public class Book implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private static int id = 0;
    private int bookId;

    public Book(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bookId = id;
        this.id++;
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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Book.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String toString() {
        return "Book name: " + this.name + " | " +
                "Price: " + this.price + " | " +
                "Quantity: " + this.quantity + " | " +
                "ID: " + this.bookId;
    }
}

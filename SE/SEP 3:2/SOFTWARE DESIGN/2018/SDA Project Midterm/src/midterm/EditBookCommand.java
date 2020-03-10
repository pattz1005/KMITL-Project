package midterm;

import java.io.Serializable;

public class EditBookCommand implements Command, Serializable {
    
    private int book_id;
    private int quantity;
    private double price;
    private String name;

    public EditBookCommand(int b_id, int q, double p, String n) {
        book_id = b_id;
        quantity = q;
        price = p;
        name = n;
    }

    public void execute(Inventory i) {
        i.editBook(book_id, quantity, price, name);
    }
}

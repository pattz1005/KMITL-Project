package midterm;

import java.io.Serializable;

public class SellBookCommand implements Command, Serializable {
    
    private int book_id;
    private int quantity;

    public SellBookCommand(int b_id, int q) {
        book_id = b_id;
        quantity = q;
    }

    public void execute(Inventory i) {
        i.sellBook(book_id, quantity);
    }
}

package midterm;

import java.io.Serializable;

public class AddBookCommand implements Command, Serializable {
    
    private Book book;

    public AddBookCommand(Book b) {
        book = b;
    }

    public void execute(Inventory i) {
        i.addBook(book);
    }
}

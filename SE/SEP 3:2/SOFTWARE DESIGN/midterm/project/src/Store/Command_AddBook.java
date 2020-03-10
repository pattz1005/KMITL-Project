//done
package Store;

import java.io.IOException;
import java.io.Serializable;

public class Command_AddBook implements Command, Serializable {
    private Book book;

    public Command_AddBook(Book b) {
        book = b;
    }

    @Override
    public void execute(Inventory inventory) throws IOException, ClassNotFoundException {
        inventory.addBook(book);
    }
}

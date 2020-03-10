package Store;

import java.io.IOException;
import java.util.ArrayList;
import java.io.Serializable;


public interface Inventory {
    void addBook(Book b) throws IOException, ClassNotFoundException;

    void sellBook(int book_id, int quantity) throws IOException, ClassNotFoundException;

    void addCopy(int bookId, int quantity) throws IOException, ClassNotFoundException;

    void changeBookPrice(int bookId, double newPrice) throws IOException, ClassNotFoundException;

    double findPrice(String name);

    double findPrice(int id);

    int findQuantity(String name);

    int findQuantity(int id);

    void saveState() throws IOException;

    void getState() throws IOException, ClassNotFoundException;

    ArrayList<Book> getBooksList();

    void setBooksList(ArrayList<Book> bookList);

    void print();
}

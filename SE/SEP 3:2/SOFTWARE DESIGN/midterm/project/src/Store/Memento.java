package Store;

import java.io.Serializable;
import java.util.ArrayList;

public class Memento implements Serializable {
    public Memento() {
    }

    private ArrayList<Book> books;

    public Memento(ArrayList<Book> books) {
        this.books = new ArrayList<Book>(books);
    }

    public void saveState(ArrayList<Book> newBookList) {
        this.books = new ArrayList<>(newBookList);
    }

    public ArrayList<Book> getState() {
        return this.books;
    }
}

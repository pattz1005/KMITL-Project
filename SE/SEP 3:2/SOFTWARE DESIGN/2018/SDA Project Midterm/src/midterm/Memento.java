package midterm;

import java.io.Serializable;
import java.util.ArrayList;

public class Memento implements Serializable{

    private ArrayList<Book> books;

    public Memento(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) books.clone();
    }
    
}

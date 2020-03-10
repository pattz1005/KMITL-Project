package midterm;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {

    private ArrayList<Book> books;

    public Inventory() {
        books = new ArrayList<Book>();
    }

    public ArrayList<Book> getBooks() {
        return (ArrayList<Book>) books.clone();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public void sellBook(int book_id, int quantity) {
        for (int i = 0; i < books.size(); i++) {
            Book temp = books.get(i);
            if (temp.getID() == book_id) {
                if (temp.getQuantity() >= quantity) {
                    temp.setQuantity(temp.getQuantity() - quantity);
                }
                break;
            }
        }
    }

    public void editBook(int book_id, int quantity, double price, String name) {
        for (int i = 0; i < books.size(); i++) {
            Book temp = books.get(i);
            if (temp.getID() == book_id) {
                temp.setName(name);
                temp.setPrice(price);
                temp.setQuantity(quantity);
                break;
            }
        }
    }
    
    public ArrayList<Book> searchBook(String term) {
        ArrayList<Book> result = new ArrayList<Book>();
        int num_id = 0;
        try {
            num_id = new Integer(term);
        } catch (NumberFormatException e) {
            //Do Nothing
        }
        for (Book b : books) {
            if(b.getID() == num_id || b.getName().contains(term)) {
                result.add(b);
            }
        }
        return (ArrayList<Book>) result.clone();
    }

    public Memento createMemento() {
        return new Memento((ArrayList<Book>) books.clone());
    }

    public void setMemento(Memento m) {
        books = m.getBooks();
    }

}

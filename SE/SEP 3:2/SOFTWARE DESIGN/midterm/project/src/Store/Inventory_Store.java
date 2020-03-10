package Store;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Inventory_Store implements Inventory {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book b) {
        for (Book book : books) {
            if (b.getBookId() == book.getBookId()) {
                addCopy(book.getBookId(), b.getQuantity());
                return;
            }
        }
        books.add(b);
        System.out.println("New:\t\tBook#" + b.getBookId() + " " + b.getName()
                + " Price: " + b.getPrice() + " Quantity: " + b.getQuantity());
    }

    public void sellBook(int bookId, int quantity) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                if (b.getQuantity() >= quantity) {
                    System.out.println("Sold:\t\tBook#" + b.getBookId() + " " + b.getName()
                            + " Quantity Sold: " + quantity + " Remaining: " + b.getQuantity());
                    b.setQuantity(b.getQuantity() - quantity);
                    return;
                }
            }
        }
        System.out.println("Book not sold.");
    }

    public void addCopy(int bookId, int quantity) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                System.out.println("Added:\t\tBook#" + b.getBookId() + " " + b.getName()
                        + " Price: " + b.getPrice() + " Quantity Added: " + quantity
                        + " Remaining: " + b.getQuantity());
                b.setQuantity(b.getQuantity() + quantity);
                return;
            }
        }
    }

    public void changeBookPrice(int bookId, double newPrice) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                System.out.println("Price Chg:\tBook#" + b.getBookId() + " " + b.getName()
                        + " Old Price: " + b.getPrice() + " New Price: " + newPrice);
                b.setPrice(newPrice);
                return;
            }
        }
    }

    public double findPrice(String name) {
        for (Book b : books) {
            if (b.getName().equals(name)) {
                System.out.println("[Find Price]\t\tBook# " + b.getBookId() + " " + b.getName() + " Price: " + b.getPrice());
                return b.getPrice();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public double findPrice(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                System.out.println("[Find Price]\t\tBook# " + b.getBookId() + " " + b.getName() + " Price: " + b.getPrice());
                return b.getPrice();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public int findQuantity(String name) {
        for (Book b : books) {
            if (b.getName().equals(name)) {
                System.out.println("[Find Quantity]\t\tBook# " + b.getBookId() + " " + b.getName() + " Qty.: " + b.getQuantity());
                return b.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    public int findQuantity(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                System.out.println("[Find Quantity]\t\tBook# " + b.getBookId() + " " + b.getName() + " Qty.: " + b.getQuantity());
                return b.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not in inventory");
    }

    @Override
    public void saveState() {
    }

    @Override
    public void getState() {
    }

    public ArrayList<Book> getBooksList() {
        return (ArrayList<Book>) books.clone();
    }

    public void setBooksList(ArrayList<Book> bookList) {
        this.books = bookList;
    }

    @Override
    public void print() {
        for (Book b : books) {
            System.out.println(b);
        }
    }


//    public ArrayList<Book> searchBook(String term) {
//        ArrayList<Book> result = new ArrayList<Book>();
//        int num_id = 0;
//        try {
//            num_id = new Integer(term);
//        } catch (NumberFormatException e) {
//
//        }
//        for (Book b : books) {
//            if(b.getID() == num_id || b.getName().contains(term)) {
//                result.add(b);
//            }
//        }
//        return (ArrayList<Book>) result.clone();
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class ConcreteInventory implements Inventory{
    private ArrayList<Book> books = new ArrayList<Book>();
    
    public ArrayList<Book> getBookCollection() {
        return books;
    }

    public void setBookCollection(ArrayList<Book> books) {
        this.books = books;
    }
    
    public void newBook(Book book) throws IllegalArgumentException{
        for(Book b : books){
            if (b.getID()==book.getID()){
                throw new IllegalArgumentException("Book already exists");
            }
        }
        books.add(book);
    }
    
    public void sellBook(String name, int num) throws NoSuchElementException{
        for(Book book : books){
            if(book.getName().equals(name) && book.getQuantity() > 0){
                book.changeQuantity(-(num));
                return;
            }	
        }
        throw new NoSuchElementException("Book not found");
    }
	
    public void addCopy(String name, int num ) throws NoSuchElementException{
        for(Book book : books){
            if(book.getName().equals(name)){
                book.changeQuantity(num);
                return;	
            }
        }
        throw new NoSuchElementException("Book not found");
    }
    
    public void changePrice(String name, int price) throws NoSuchElementException{
        for(Book book : books){
            if(book.getName().equals(name)){
                book.setPrice(price);
                return;
            }
        }
        throw new NoSuchElementException("Book not found");
    }
    
    public int getPrice(String name) throws NoSuchElementException{
        for(Book book : books){
            if(book.getName().equals(name)){
                return book.getPrice();
            }
        }
            throw new NoSuchElementException("Book not found");
    }
    public int getPrice(int id) throws NoSuchElementException{
        for(Book book : books){
            if(book.getID()==id){
                return book.getPrice();
            }
        }
        throw new NoSuchElementException("Book not found");
    }
    
    public int getQuantity(String name) throws NoSuchElementException{
        for(Book book : books){
            if(book.getName().equals(name)){
                return book.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not found");
    }
    
    public int getQuantity(int id) throws NoSuchElementException{
        for(Book book : books){
            if(book.getID()==id){
                return book.getQuantity();
            }
        }
        throw new NoSuchElementException("Book not found");
    }
    public String toString(){
        String r = new String();
        for(Book book:books){
            r = r.concat(book.toString());
        }
        return r;
    }
}

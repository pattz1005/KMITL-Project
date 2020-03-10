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
public interface Inventory {
    public ArrayList<Book> getBookCollection();
    public void newBook(Book book) throws IllegalArgumentException;
    public void sellBook(String name, int num) throws NoSuchElementException;
    public void addCopy(String name, int num ) throws NoSuchElementException;
    public void changePrice(String name,int price) throws NoSuchElementException;
    public int getPrice(String name) throws NoSuchElementException;
    public int getPrice(int id) throws NoSuchElementException;
    public int getQuantity(String name) throws NoSuchElementException;
    public int getQuantity(int id) throws NoSuchElementException;
}

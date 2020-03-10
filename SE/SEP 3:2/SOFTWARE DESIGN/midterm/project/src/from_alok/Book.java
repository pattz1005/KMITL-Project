/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

/**
 *
 * @author alok
 */
public interface Book {
    public void changeQuantity(int change);
    public void setName(String name);
    public String getName();
    public void setPrice(int price);
    public int getPrice();
    public void setID(int id);
    public int getID();
    public int getQuantity();
    public void setQuantity(int quantity);
    public void addCopy();
    public void sellCopy();
}

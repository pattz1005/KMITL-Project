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
public class ConcreteBook implements Book, java.io.Serializable{
    private String name;
    private int price;
    private int id;
    private int quantity;
    public ConcreteBook(String n, int p, int i, int q){
        this.name = n;
        this.price = p;
        this.id = i;
        this.quantity = q;
    }
    public void changeQuantity(int change){
        this.quantity += change;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setID(int id){
        this.id = id;
    }
    public int getID(){
        return this.id;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void sellCopy(){
        this.quantity--;
    }
    public void addCopy(){
        this.quantity++;
    }
    public String toString(){
        String r = new String();
        r = r.concat("Name: ");
        r = r.concat(name);
        r = r.concat("\nID: ");
        r = r.concat(Integer.toString(id));
        r = r.concat("\nPrice: ");
        r = r.concat(Integer.toString(price));
        r = r.concat("\nQuantity: ");
        r = r.concat(Integer.toString(quantity));
        r = r.concat("\n\n");
        return r;
    }
}

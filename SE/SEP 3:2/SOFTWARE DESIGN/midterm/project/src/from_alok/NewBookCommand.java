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
public class NewBookCommand extends Command implements java.io.Serializable {
    private Book book;
    private String fileName = "Command.ser";

    public NewBookCommand(Book newBook){
        this.book = new ConcreteBook(newBook.getName(), newBook.getPrice(), newBook.getID(), newBook.getQuantity()); 
    }

    public void execute(Inventory newInvent) {
        try{
            newInvent.newBook(new ConcreteBook(book.getName(), book.getPrice(), book.getID(), book.getQuantity()));
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }
    
    public String toString(){
        return book.toString();
    }
}

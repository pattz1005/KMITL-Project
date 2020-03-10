/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package from_alok;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author alok
 */
public class InventoryDecorator implements Inventory{
    private Inventory inventory;
    private FileInputStream fileIn;
    private ArrayList<Command> commands;
    private String commandFileName;
    
    public InventoryDecorator(){
        inventory = new ConcreteInventory();
        commands = new ArrayList<Command>();
        commandFileName = "Command.ser";
    }
    
    public Inventory getInventory(){
        return this.inventory;
    }
    
    public ArrayList<Book> getBookCollection(){
        return inventory.getBookCollection();
    }
    
    public void setInventory(Inventory invent) {
        this.inventory = invent;
    }
    
    public void newBook(Book book){
        try{
            NewBookCommand newBook = new NewBookCommand(book);
            newBook.execute(inventory);
            commands.add(newBook);
        this.saveCommands();
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }
    }
	
    public void addCopy(String name, int num){
        try{
            AddCopyCommand addCopy = new AddCopyCommand(name, num);
            addCopy.execute(inventory);
            commands.add(addCopy);
            this.saveCommands();
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }

    public void changePrice(String name, int price){
        try{
            ChangePriceCommand changePrice = new ChangePriceCommand(name, price);
            changePrice.execute(inventory);
            commands.add(changePrice);
            this.saveCommands();
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }

    public void sellBook(String name, int num){
        try{
            SellBookCommand sellBook = new SellBookCommand(name, num);
            sellBook.execute(inventory);
            commands.add(sellBook);
            this.saveCommands();
        }
        catch(NoSuchElementException e){
            System.out.println(e);
        }
    }
    
    public int getPrice(String name){
        try{
            return inventory.getPrice(name);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public int getPrice(int id){
        try{
            return inventory.getPrice(id);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public int getQuantity(String name){
        try{
            return inventory.getQuantity(name);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public int getQuantity(int id){
        try{
            return inventory.getQuantity(id);
        }
        catch(NoSuchElementException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public void replayCommands(){
        ArrayList<Command> newCommands = new ArrayList<Command>();
        try {
            fileIn = new FileInputStream(commandFileName);
            ObjectInputStream input = new ObjectInputStream(fileIn);
            newCommands = ((ArrayList<Command>) input.readObject());
        }
        catch (EOFException e) {

            try{	
                fileIn.close();
            }
            catch(IOException i)
            {
                i.printStackTrace();
            }

        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
        catch(ClassNotFoundException c)
        {
            System.out.println("class not found");
            c.printStackTrace();
        }
        for(Command command : newCommands){
            command.execute(inventory);
        }
    }
    
    public void saveCommands(){
        File file = new File(commandFileName);
        try{
            file.delete();
            FileOutputStream fileOut = new FileOutputStream(commandFileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(commands);         
            out.close();
            fileOut.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public void resetCommands(){
        File file = new File(commandFileName);
        commands.clear();
        file.delete();
    }
    
    public void setCommandFile(String file){
        this.commandFileName = file;
    }
    
    public String getCommandFile(){
        return this.commandFileName;
    }
    
    public Memento getMemento(){

        return new Memento(((ConcreteInventory)inventory).getBookCollection());
    }
    
    public void setFromMemento(Memento memento){
        ((ConcreteInventory)inventory).setBookCollection(memento.getState());
    }
    public String toString(){
        return inventory.toString();
    }
}


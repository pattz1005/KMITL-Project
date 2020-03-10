/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alok
 */
public class CareTaker {
    private Object object;
    private String fileName = "Inventory.ser";
    private File file = new File(fileName);
    byte[] buf = new byte[1024];
    int bytesRead;
    
    public void addMemento(Memento memento, String name){
        File tempFile = new File(name);
        try{
            tempFile.delete();
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memento);         
            out.close();
            fileOut.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    
    public Memento get(String name){
        try{
            FileInputStream fileIn = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            return (Memento)object;
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return null;

        }
        catch(ClassNotFoundException c)
        {
            System.out.println("class not found");
            c.printStackTrace();
            return null;

        }
    }

    public void serialzeMemento(Memento state){

        try{
            file.delete();
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(state);         
            out.close();
            fileOut.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public Memento deserialseMemento(){	 

        try{
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            return (Memento)object;
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return null;

        }
        catch(ClassNotFoundException c)
        {
            System.out.println("class not found");
            c.printStackTrace();
            return null;

        }
    }
}

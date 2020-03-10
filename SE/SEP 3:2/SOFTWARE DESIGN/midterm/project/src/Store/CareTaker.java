///done
package Store;

import java.io.*;

public class CareTaker {

    private String fileName = "storage.ser";

    //Adds memento and write it to the file
    public void add(Memento state) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(state);
        out.close();
        fileOut.close();
    }

    //Reads from file and gets memento from it
    public Memento get() throws IOException, ClassNotFoundException {
        Object o;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        o = in.readObject();
        in.close();
        fileIn.close();
        return (Memento) o;
    }

}

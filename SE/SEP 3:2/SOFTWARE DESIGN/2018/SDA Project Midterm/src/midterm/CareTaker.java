package midterm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class CareTaker {

    private ArrayList<Memento> mementos = new ArrayList<Memento>();
    private String folder_path = "C:\\Users\\viray\\Documents\\Projects\\SDA Project Midterm\\database\\";
    private String suffix = "_Memento.ser";
    private String latestMementoTime = "";

    public CareTaker() {
        File folder = new File(folder_path);
        for (File f : folder.listFiles()) {
            if (f.getName().contains(suffix)) {
                try {
                    FileInputStream fileIn = new FileInputStream(f.toString());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    mementos.add((Memento) in.readObject());
                    latestMementoTime = f.getName().replaceAll("[^0-9]", "");
                    in.close();
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addMemento(Memento m) {
        mementos.add(m);
        try {
            Date date = new Date();
            long time = date.getTime();
            String filename = folder_path + time + suffix;
            latestMementoTime = "" + time;
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(m);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Memento getMemento(int i) {
        return mementos.get(i);
    }

    public String getLatestMementoTime() {
        return latestMementoTime;
    }

    public Memento getLatestMemento() {
        if (mementos.size() > 0) {
            return mementos.get(mementos.size() - 1);
        }
        return null;
    }
}

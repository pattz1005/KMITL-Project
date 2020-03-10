package memento;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
import javax.swing.*;

public class ScheduleGUI extends JFrame {  // Caretaker

    private ScheduleTableModel tableModel;
    private LoadDialog loadDialog;
    private static String fileLocation = "C:\\Users\\viray\\Documents\\Projects\\SDA Lab7\\database\\";
    private static String suffix = "_Memento.ser";

    public ScheduleGUI() {
        super("Schedule GUI with Memento");
        initComponents();
        pack();
        this.setSize(800, 400);
        this.setResizable(false);
    }

    private int getMementoCount() {
        int count = 0;
        File folder = new File(fileLocation);
        for (File f : folder.listFiles()) {
            if (f.getName().contains(suffix)) {
                count++;
            }
        }
        return count;
    }

    private Memento loadMemento(int index) {
        int mementoCount = getMementoCount();
        if (index > mementoCount || index < -mementoCount || 
                mementoCount == 0 || index == 0) {
            return new NullMemento();
        }

        int count = 0;
        Memento result = null;
        
        File folder = new File(fileLocation);
        Vector<File> files = new Vector<>(Arrays.asList(folder.listFiles()));
        if (index < 0) Collections.reverse(files);
        
        for (File f : files) {
            if (f.getName().contains(suffix)) {
                if (index < 0) count--;
                else count++;
                if (count == index) {
                    try {
                        FileInputStream fileIn = new FileInputStream(f);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        result = (TableMemento) in.readObject();
                        in.close();
                        fileIn.close();
                    } catch (IOException e) {

                    } catch (ClassNotFoundException e) {

                    }
                    break;
                }
            }
        }
        return result;
    }

    public void saveMemento() {
        TableMemento m = tableModel.createMemento();
        try {
            Date date = new Date();
            long time = date.getTime();
            String fileName = fileLocation + time + suffix;
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(m);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    protected void initComponents() {
        loadDialog = new LoadDialog(this, true);
        loadDialog.addObserver(new Observer() {
            public void update(int i) {
                tableModel.setMemento(loadMemento(i));
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exit();
            }
        });

        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveMemento();
            }
        }
        );

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadDialog.setTotal(getMementoCount());
                loadDialog.setVisible(true);
            }
        }
        );

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        }
        );

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        mb.add(fileMenu);
        setJMenuBar(mb);

        tableModel = new ScheduleTableModel(loadMemento(-1));
        final JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton newButton = new JButton("Snapshot");
        newButton.setPreferredSize(new Dimension(125, 27));
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveMemento();
            }
        });
        JButton revertButton = new JButton("Revert");
        revertButton.setPreferredSize(new Dimension(125, 27));
        revertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tableModel.setMemento(loadMemento(-1));
            }
        });
        JPanel panel = new JPanel();
        panel.add(newButton);
        panel.add(revertButton);
        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    protected void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new ScheduleGUI().setVisible(true);
    }
}

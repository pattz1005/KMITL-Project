package memento;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class ScheduleGUI extends JFrame {  // Caretaker

    private ScheduleTableModel tableModel;
    private String fileLocation = "file.ser";
    JFileChooser j = new JFileChooser();

    private Object[][] data = {
            {"08.00-09.00", "----", "Math", "", "", ""},
            {"09.00-10.00", "", "Math", "", "", ""},
            {"10.00-11.00", "", "", "SE 2", "", "Math"},
            {"11.00-12.00", "", "", "SE 2", "", "Math"},
            {"12.00-13.00", "----", "----", "----", "----", "----"},
            {"13.00-14.00", "SE 2", "OpSys", "", "", ""},
            {"14.00-15.00", "SE 2", "OpSys", "", "Project", ""},
            {"15.00-16.00", "", "OpSys", "", "Project", ""},
            {"16.00-17.00", "", "OpSys", "", "Project", ""},
            {"17.00-18.00", "", "", "", "Project", ""}
    };

    private String[] columnNames = {
            "Time",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday"
    };

    public ScheduleGUI() {
        super("Schedule GUI with Memento");
        initComponents();
        pack();
    }

    protected void initComponents() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });


        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableMemento m = tableModel.createMemento();
                int action = j.showSaveDialog(null);
                if (action == JFileChooser.APPROVE_OPTION) {
                    // set the label to the path of the selected file
                    fileLocation = j.getSelectedFile().getAbsolutePath();
                    System.out.println("Saved in: " + fileLocation);
                    try {
                        FileOutputStream fileOut = new FileOutputStream(fileLocation);
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(m);
                        out.close();
                        fileOut.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    }
                }
            }
        });

        fileMenu.add(saveMenuItem);


        JMenuItem LoadMenuItem = new JMenuItem("Load");
        LoadMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableMemento m = new TableMemento();
                int action = j.showOpenDialog(null);
                if (action == JFileChooser.APPROVE_OPTION) {
                    // set the label to the path of the selected file
                    fileLocation = j.getSelectedFile().getAbsolutePath();
                    System.out.println("Opened: " + fileLocation);

                    try {
                        FileInputStream fileIn = new FileInputStream(fileLocation);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        m = (TableMemento) in.readObject();
                        in.close();
                        fileIn.close();
                    } catch (IOException | ClassNotFoundException i) {
                        i.printStackTrace();
                    }
                    TableMemento temp = tableModel.createMemento();
                    tableModel.setMemento(m);
                    tableModel.revert();
                    tableModel.setMemento(temp);
                }
            }
        });

        fileMenu.add(LoadMenuItem);


        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitMenuItemActionPerformed(e);
            }
        });

        fileMenu.add(exitMenuItem);


        mb.add(fileMenu);
        setJMenuBar(mb);

        tableModel = new ScheduleTableModel(data.clone(), columnNames.clone());
        final JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);


        JButton newButton = new JButton("Snapshot");
        newButton.setPreferredSize(new Dimension(125, 27));
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Get new memento.
                // COMPLETE.

                tableModel.setMemento(tableModel.createMemento());

            }
        });
        JButton revertButton = new JButton("Revert");
        revertButton.setPreferredSize(new Dimension(125, 27));
        revertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Set memento.
                // COMPLETE.
                tableModel.revert();

            }
        });


        JPanel panel = new JPanel();
        panel.add(newButton);
        panel.add(revertButton);
        getContentPane().add(panel, BorderLayout.SOUTH);

    }

    protected void exitMenuItemActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    protected void exitForm(WindowEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new ScheduleGUI().setVisible(true);
    } // end of main ()

}

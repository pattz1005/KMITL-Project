package memento2;

 

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ScheduleGUI extends JFrame {  // Caretaker

    private ScheduleTableModel tableModel;
    private String fileLocation = "file.ser";

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
        addWindowListener (new WindowAdapter () {
            public void windowClosing (WindowEvent evt) {
                exitForm (evt);
            }
        });
        

	JMenuBar mb = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	// Add "Save As.." menu item here; register corresponding
	// listener (optional);
	// COMPLETE.
        JMenuItem saveMenuItem = new JMenuItem("Save");
	saveMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                TableMemento m = tableModel.createMemento();
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
        });

	fileMenu.add(saveMenuItem);

        
        JMenuItem LoadMenuItem = new JMenuItem("Load");
	LoadMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                TableMemento m = new TableMemento();
                try {
                    FileInputStream fileIn = new FileInputStream(fileLocation);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    m = (TableMemento) in.readObject();
                    in.close();
                    fileIn.close();
                } catch (IOException i) {
                    i.printStackTrace();
                } catch (ClassNotFoundException c) {
                    c.printStackTrace();
                }
                TableMemento temp = tableModel.createMemento();
                tableModel.setMemento(m);
                tableModel.revert();
                tableModel.setMemento(temp);
            }
        });

	fileMenu.add(LoadMenuItem);


	JMenuItem exitMenuItem = new JMenuItem("Exit");
	exitMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                exitMenuItemActionPerformed (e);
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
        newButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent evt) {
		// Get new memento.
		// COMPLETE.
                
                tableModel.setMemento(tableModel.createMemento());

            }
        });
	JButton revertButton = new JButton("Revert");
	revertButton.setPreferredSize(new Dimension(125, 27));
        revertButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent evt) {
		// Set memento.
		// COMPLETE.
                tableModel.revert();

            }
        });
        
        

	JPanel panel = new JPanel();
	panel.add(newButton);
	panel.add(revertButton);
	getContentPane().add(panel, BorderLayout.SOUTH);
        
        
        
        JPanel panel2 = new JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JTextField jTextField1 = new javax.swing.JTextField();
        jTextField1.setPreferredSize( new Dimension( 200, 24 ) );
        panel2.add(jTextField1);
        panel2.add(jLabel1);
        javax.swing.JButton jButton1;
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Change Directory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                fileLocation = jTextField1.getText();
            }
        });
        panel2.add(jButton1);
        getContentPane().add(panel2, BorderLayout.WEST);

	// Take a memento for the initial state of the table.
	// COMPLETE.



    }

    protected void exitMenuItemActionPerformed (ActionEvent evt) {
        System.exit(0);
    }

    protected void exitForm(WindowEvent e) {
        System.exit (0);
    }

    public static void main (String[] args) {
        new ScheduleGUI().setVisible (true);
    } // end of main ()
    
}

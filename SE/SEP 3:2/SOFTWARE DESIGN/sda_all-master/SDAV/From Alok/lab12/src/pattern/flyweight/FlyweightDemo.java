/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.flyweight;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class FlyweightDemo extends JFrame
    implements MouseMotionListener
{
    private ArrayList items;
    private IconFactory factory;
    private final int Top = 30, Left = 30;
    private final int  W = 50, H = 30;
    private final int VSpace = 80, HSpace=70, HCount = 3;
    private String selectedName = "";

    private class Item {
	String type;
	String name;
	AbstractIcon icon;
	Item(String type, String name, AbstractIcon icon) {
	    this.type = type;
	    this.name = name;
	    this.icon = icon;
	}
    }

    public FlyweightDemo() {
        super("Flyweight Demo");
        setSize(new Dimension(250,300));
        JPanel jp = new JPanel();
        getContentPane().add(jp, BorderLayout.CENTER);
        addMouseMotionListener(this);
	addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {System.exit(0);}
	    });

        loadItems();

        setVisible(true);
    }

    private void loadItems() {
	// Get the icon factory.
	// COMPLETE.
        factory = IconFactory.getInstance();


	// Create a bunch of different kind of items.
	// COMPLETE.
        items = new ArrayList();
        items.add(new Item("folder", "soidjf", factory.createIcon("folder")));
        items.add(new Item("java", "fdsaf", factory.createIcon("java")));
        items.add(new Item("pdf", "dsfgd", factory.createIcon("pdf")));
        items.add(new Item("picture", "dsg", factory.createIcon("picture")));
        items.add(new Item("text", "sdfgs", factory.createIcon("text")));
        items.add(new Item("unknown", "ftvds", factory.createIcon("unknown")));



    }


    public void paint(Graphics g) {
	// super.paint(g);
        //Folder f;
        Item item;

        int j = 0;      //count number in row
        int row = Top;  //start in upper left
        int x = Left;

        //go through all the items
        for (int i = 0; i< items.size(); i++) {
	    // Get next item, and draw its icon and name:
	    // COMPLETE.
            item = (Item) items.get(i);
            item.icon.draw(g, x, row, item.name, rootPaneCheckingEnabled);







	    // Recalculation of part of the extrinsic state:
            x = x + HSpace;          //change to next posn
            j++;
            if (j >= HCount) {       //reset for next row
                j = 0;         
                row += VSpace;
                x = Left;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        int j = 0;      //count number in row
        int row = Top;  //start in upper left
        int x = Left;

        //go through all the items
        for (int i = 0; i< items.size(); i++) {
            //see if this item area contains the mouse
            Rectangle r = new Rectangle(x,row,W,H);
            if (r.contains(e.getX(), e.getY())) {
                selectedName = ((Item)items.get(i)).name;
                repaint();
            }
            x = x + HSpace;          //change to next posn
            j++;
            if (j >= HCount) {       //reset for next row
                j = 0;         
                row += VSpace;
                x = Left;
            }
        }
    }
    public void mouseDragged(MouseEvent e) {
    }

    public static  void main(String[] args) {
        new FlyweightDemo();
    }
}

package e1.flyweight;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FlyweightDemo extends JFrame implements MouseMotionListener {

    private ArrayList items;
    private IconFactory factory;
    private final int Top = 30, Left = 30;
    private final int W = 50, H = 30;
    private final int VSpace = 80, HSpace = 70, HCount = 10;
    private String selectedName = "";
    private String[] iconType = {
        "folder", "pdf", "java", "picture", "text", "unknown"
    };

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
        setSize(new Dimension(750, 600));
        JPanel jp = new JPanel();
        getContentPane().add(jp, BorderLayout.CENTER);
        addMouseMotionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        loadItems();
        setVisible(true);
    }

    private void loadItems() {
        factory = new IconFactory();
        items = new ArrayList();
        for (int i = 0; i < 70; i++) {
            String type = genRandomType();
            items.add(new Item(type, String.valueOf(i), factory.createIcon(type)));
        }
    }
    
    private String genRandomType() {
        Random random = new Random();
        return iconType[random.nextInt(iconType.length)];
    }

    public void paint(Graphics g) {
        Item item;

        int j = 0;      //count number in row
        int row = Top;  //start in upper left
        int x = Left;
        
        boolean selected;
        for (int i = 0; i < items.size(); i++) {
            item = (Item) items.get(i);
            if (selectedName.equals(item.name)) {
                selected = true;
            } else {
                selected = false;
            }
            item.icon.draw(this.getGraphics(), x, row, item.name, selected);

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
        int j = 0;
        int row = Top;
        int x = Left;

        for (int i = 0; i < items.size(); i++) {
            //see if this item area contains the mouse
            Rectangle r = new Rectangle(x, row, W, H);
            if (r.contains(e.getX(), e.getY())) {
                selectedName = ((Item) items.get(i)).name;
                repaint();
            }
            x = x + HSpace;
            j++;
            if (j >= HCount) {
                j = 0;
                row += VSpace;
                x = Left;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public static void main(String[] args) {
        new FlyweightDemo();
    }
}

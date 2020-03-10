package Bridge.src_lab;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class XMLWriter extends JComponent {

  public void write_line(int x1, int y1, int x2, int y2) {
    System.out.println("XML Writer writing line");
  }

  public void write_circle(int x, int y, int r) {
    System.out.println("XML Writer writing circle");
  }

}

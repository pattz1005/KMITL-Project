import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Printer extends JComponent {

  private static Printer printer;

  public static Printer getPrinter() {
    if (printer == null) {
      printer = new Printer();
    }
    return printer;
  }

  private List<Arc>  arcs;
  private List<Line> lines;

  private JFrame frame;

  private Printer() {
    super();

    frame = new JFrame("Printer");
    frame.setSize(400, 400);
    frame.setLocation(500, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add( this );

    arcs  = new LinkedList<Arc>();
    lines = new LinkedList<Line>();

    frame.setVisible(true);
  }

  public synchronized void drawline(int x1, int x2, int y1, int y2) {
    lines.add(new Line(x1, y1, x2, y2));
    repaint();
  }

  public synchronized void drawcircle(int x, int y, int r) {
    arcs.add(new Arc(x, y, r));
    repaint();
  }

  public synchronized void paint(Graphics g) {
    g.setColor(Color.red);
    for (Line line : lines) {
      g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }

    for (Arc arc : arcs) {
      g.drawArc(arc.x, arc.y, arc.r*2, arc.r*2, 0, 360);
    }
  }

}

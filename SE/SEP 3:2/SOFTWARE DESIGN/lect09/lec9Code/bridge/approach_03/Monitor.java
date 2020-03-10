import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class Monitor extends JComponent {

  private static Monitor monitor;

  public static Monitor getMonitor() {
    if (monitor == null) {
      monitor = new Monitor();
    }
    return monitor;
  }

  private List<Arc>  arcs;
  private List<Line> lines;

  private JFrame frame;

  private Monitor() {
    super();

    frame = new JFrame("Monitor");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add( this );

    arcs  = new LinkedList<Arc>();
    lines = new LinkedList<Line>();

    addMouseMotionListener(new MouseInputAdapter() {
      public void mouseMoved(MouseEvent e) {
         System.out.println("Mouse moved: (" + e.getX() +
                            ", " + e.getY() +
                            ")");
      }
    });

    addMouseListener(new MouseInputAdapter() {
      public void mouseClicked(MouseEvent e) {
         System.out.println("Mouse clicked at: (" + e.getX() +
                            ", " + e.getY() +
                            ")");
      }
      public void mouseEntered(MouseEvent e) {
         System.out.println("Mouse entered at: (" + e.getX() +
                            ", " + e.getY() +
                            ")");
      }
      public void mouseExited(MouseEvent e) {
         System.out.println("Mouse left at: (" + e.getX() +
                            ", " + e.getY() +
                            ")");
      }
    });
 


    frame.setVisible(true);
  }

  public synchronized void draw_a_line(int x1, int y1, int x2, int y2) {
    lines.add(new Line(x1, y1, x2, y2));
    repaint();
  }

  public synchronized void draw_a_circle(int x, int y, int r) {
    arcs.add(new Arc(x, y, r));
  }

  public synchronized void paint(Graphics g) {
    for (Line line : lines) {
      g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }

    for (Arc arc : arcs) {
      g.drawArc(arc.x, arc.y, arc.r*2, arc.r*2, 0, 360);
    }
  }

}

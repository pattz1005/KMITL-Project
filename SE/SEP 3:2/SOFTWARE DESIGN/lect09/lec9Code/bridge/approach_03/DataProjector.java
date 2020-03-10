import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DataProjector extends JComponent {

  private static DataProjector data_projector;

  public static DataProjector getDataProjector() {
    if (data_projector == null) {
      data_projector = new DataProjector();
    }
    return data_projector;
  }

  private List<Arc>  arcs;
  private List<Line> lines;

  private JFrame frame;

  private DataProjector() {
    super();

    frame = new JFrame("DataProjector");
    frame.setSize(400, 400);
    frame.setLocation(1000, 0);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add( this );

    arcs  = new LinkedList<Arc>();
    lines = new LinkedList<Line>();

    frame.setVisible(true);
  }

  public synchronized void drawLine(int x1, int y1, int x2, int y2) {
    lines.add(new Line(x1, y1, x2, y2));
    repaint();
  }

  public synchronized void drawCircle(int x, int y, int r) {
    arcs.add(new Arc(x, y, r));
  }

  public synchronized void paint(Graphics g) {
    g.setColor(Color.blue);
    for (Line line : lines) {
      g.drawLine(line.x1, line.y1, line.x2, line.y2);
    }

    for (Arc arc : arcs) {
      g.drawArc(arc.x, arc.y, arc.r*2, arc.r*2, 0, 360);
    }
  }

}

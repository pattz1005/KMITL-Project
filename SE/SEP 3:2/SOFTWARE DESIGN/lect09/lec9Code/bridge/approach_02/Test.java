import java.util.LinkedList;
import java.util.List;

public class Test {

  public static void main(String[] args) {

    List<Shape> shapes = new LinkedList<Shape>();

    shapes.add(new MonitorRect(10, 10, 100, 100));
    shapes.add(new MonitorRect(110, 110, 200, 200));
    shapes.add(new MonitorCircle(250, 250, 30));

    shapes.add(new PrinterRect(10, 10, 100, 100));
    shapes.add(new PrinterRect(110, 110, 200, 200));
    shapes.add(new PrinterCircle(250, 250, 30));

    for (Shape s : shapes) {
      s.draw();
    }
  }
}

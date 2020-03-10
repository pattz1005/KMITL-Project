package Bridge.src_lab;
import java.util.LinkedList;
import java.util.List;

public class Test {

  public static void main(String[] args) throws InterruptedException {

    DrawingService wrap_p = new WrapPrinter();
    DrawingService wrap_x = new WrapXMLWriter();
    DrawingService wrap_s = new WrapScreen();

    Shape r = new Rectangle(10, 10, 100, 100);
    Shape p = new Polygon(110, 110, 200, 200);
    Shape c = new Circle(250, 250, 30);

    r.setDrawingService(wrap_p);
    p.setDrawingService(wrap_x);
    c.setDrawingService(wrap_s);

    r.draw();
    p.draw();
    c.draw();

  }
}

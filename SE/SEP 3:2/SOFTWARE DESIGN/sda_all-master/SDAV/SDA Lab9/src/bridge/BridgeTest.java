
package bridge;

public class BridgeTest {
    
    public static void main(String[] args) {
        
        DrawingService screen = new WrapScreen();
        DrawingService printer = new WrapPrinter();
        DrawingService xmlWriter = new WrapXmlWriter();
        
        Shape rectangle = new Rectangle(screen);
        rectangle.draw();
        
        Shape circle = new Circle(printer);
        circle.draw();
        
        Shape polygon = new Polygon(xmlWriter);
        polygon.draw();
    }

}

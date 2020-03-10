
package bridge;

public class Circle extends Shape {
    
    public Circle(DrawingService drawingService) {
        super(drawingService);
    }

    @Override
    public void draw() {
        drawingService.drawCircle(0, 0, 0);
    }
    
}

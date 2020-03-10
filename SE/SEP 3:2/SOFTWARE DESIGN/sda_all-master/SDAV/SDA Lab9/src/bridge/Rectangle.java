
package bridge;

public class Rectangle extends Shape {
    
    public Rectangle(DrawingService drawingService) {
        super(drawingService);
    }

    @Override
    public void draw() {
        drawingService.drawLine(0, 0, 0, 0);
    }

}

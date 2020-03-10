
package bridge;

public class Polygon extends Shape {
    
    public Polygon(DrawingService drawingService) {
        super(drawingService);
    }

    @Override
    public void draw() {
        drawingService.drawPixel(0, 0);
    }

}

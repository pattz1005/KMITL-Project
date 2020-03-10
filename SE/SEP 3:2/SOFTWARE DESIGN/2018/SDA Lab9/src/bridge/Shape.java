
package bridge;

public abstract class Shape {
    
    protected DrawingService drawingService;
    
    protected Shape(DrawingService drawingService) {
        this.drawingService = drawingService;
    }
    
    public abstract void draw();
    
}

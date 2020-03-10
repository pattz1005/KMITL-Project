
package Bridge.src_lab;

public class WrapScreen extends DrawingService {
    
    private Screen screen;
    
    public WrapScreen() {
        this.screen = new Screen();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.screen.draw_line(x1, y1, x2, y2);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        this.screen.draw_circle(x, y, r);
    }

}

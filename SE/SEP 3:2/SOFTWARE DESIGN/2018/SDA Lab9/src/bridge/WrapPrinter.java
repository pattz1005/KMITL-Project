
package bridge;

public class WrapPrinter extends DrawingService {
    
    private Printer printer;
    
    public WrapPrinter() {
        this.printer = new Printer();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.printer.print_line(x1, y1, x2, y2);
    }

    @Override
    public void drawPixel(int x, int y) {
        this.printer.print_pixel(x, y);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        this.printer.print_circle(x, y, r);
    }

}

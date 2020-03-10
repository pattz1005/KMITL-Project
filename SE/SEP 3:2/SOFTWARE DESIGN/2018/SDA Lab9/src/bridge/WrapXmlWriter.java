
package bridge;

public class WrapXmlWriter extends DrawingService {
    
    private XmlWriter xmlWriter;
    
    public WrapXmlWriter() {
        this.xmlWriter = new XmlWriter();
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        this.xmlWriter.write_line(x1, y1, x2, y2);
    }

    @Override
    public void drawPixel(int x, int y) {
        this.xmlWriter.write_pixel(x, y);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        this.xmlWriter.write_circle(x, y, r);
    }

}

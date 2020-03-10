package Bridge.src_lab;


public class WrapXMLWriter extends DrawingService {

  private XMLWriter xmlWriter;

  public WrapXMLWriter() {
    this.xmlWriter = new XMLWriter();
  }

  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    this.xmlWriter.write_line(x1, y1, x2, y2);
  }

  @Override
  public void drawCircle(int x, int y, int r) {
    this.xmlWriter.write_circle(x, y, r);
  }

}
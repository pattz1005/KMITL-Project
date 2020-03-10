public abstract class PrinterShapes extends Shape {

  private Printer printer;

  public PrinterShapes(int x, int y) {
    super(x, y);
    printer = Printer.getPrinter();
  }

  protected void drawLine(int x1, int y1, int x2, int y2) {
     printer.drawline(x1, x2, y1, y2);
  }

  protected void drawCircle(int x, int y, int r) {
     printer.drawcircle(x, y, r);
  }

}

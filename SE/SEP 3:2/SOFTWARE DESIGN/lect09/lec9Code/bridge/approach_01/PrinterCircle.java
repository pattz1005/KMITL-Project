public class PrinterCircle extends Circle {

  private Printer printer;

  public PrinterCircle(int x, int y, int r) {
    super(x, y, r);
    printer = Printer.getPrinter();
  }

  protected void drawCircle(int x, int y, int r) {
     printer.drawcircle(x, y, r);
  }

}

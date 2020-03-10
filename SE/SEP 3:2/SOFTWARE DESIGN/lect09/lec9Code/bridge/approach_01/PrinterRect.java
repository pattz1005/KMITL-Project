public class PrinterRect extends Rectangle {

  private Printer printer;

  public PrinterRect(int x, int y, int x2, int y2) {
    super(x, y, x2, y2);
    printer = Printer.getPrinter();
  }

  protected void drawLine(int x1, int y1, int x2, int y2) {
     printer.drawline(x1, x2, y1, y2);
  }

}

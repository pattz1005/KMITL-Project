public class PrinterCircle extends PrinterShapes {

  protected int r;

  public PrinterCircle(int x, int y, int r) {
    super(x, y);
    this.r = r;
  }

  public void draw() {
     drawCircle(x, y, r);
  }

}

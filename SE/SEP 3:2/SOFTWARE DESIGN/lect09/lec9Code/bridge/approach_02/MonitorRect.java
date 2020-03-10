public class MonitorRect extends MonitorShapes {

  protected int x2;
  protected int y2;

  public MonitorRect(int x, int y, int x2, int y2) {
    super(x, y);
    this.x2 = x2;
    this.y2 = y2;
  }

  public void draw() {
    drawLine(x, y, x2, y);
    drawLine(x2, y, x2, y2);
    drawLine(x2, y2, x, y2);
    drawLine(x, y2, x, y);
  }

}

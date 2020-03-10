public class MonitorCircle extends MonitorShapes {

  protected int r;

  public MonitorCircle(int x, int y, int r) {
    super(x, y);
    this.r = r;
  }

  public void draw() {
     drawCircle(x, y, r);
  }

}

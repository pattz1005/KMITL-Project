public abstract class MonitorShapes extends Shape {

  private Monitor monitor;

  public MonitorShapes(int x, int y) {
    super(x, y);
    monitor = Monitor.getMonitor();
  }

  protected void drawLine(int x1, int y1, int x2, int y2) {
     monitor.draw_a_line(x1, y1, x2, y2);
  }

  protected void drawCircle(int x, int y, int r) {
     monitor.draw_a_circle(x, y, r);
  }

}

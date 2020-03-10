public class MonitorCircle extends Circle {

  private Monitor monitor;

  public MonitorCircle(int x, int y, int r) {
    super(x, y, r);
    monitor = Monitor.getMonitor();
  }

  protected void drawCircle(int x, int y, int r) {
     monitor.draw_a_circle(x, y, r);
  }

}

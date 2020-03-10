public class MonitorRect extends Rectangle {

  private Monitor monitor;

  public MonitorRect(int x, int y, int x2, int y2) {
    super(x, y, x2, y2);
    monitor = Monitor.getMonitor();
  }

  protected void drawLine(int x1, int y1, int x2, int y2) {
     monitor.draw_a_line(x1, y1, x2, y2);
  }

}

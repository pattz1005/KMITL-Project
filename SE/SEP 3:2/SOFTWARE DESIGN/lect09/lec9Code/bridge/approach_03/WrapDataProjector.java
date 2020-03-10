public class WrapDataProjector extends DrawingService {

  private DataProjector data_projector;

  public WrapDataProjector() {
    data_projector = DataProjector.getDataProjector();
  }

  public void drawLine(int x1, int y1, int x2, int y2) {
    data_projector.drawLine(x1, y1, x2, y2);
  }

  public void drawCircle(int x, int y, int r) {
    data_projector.drawCircle(x, y, r);
  }

}

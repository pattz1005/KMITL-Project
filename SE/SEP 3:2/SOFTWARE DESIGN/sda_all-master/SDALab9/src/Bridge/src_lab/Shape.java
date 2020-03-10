package Bridge.src_lab;
public abstract class Shape {

  protected int x;
  protected int y;

  protected DrawingService draw;

  public Shape(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public abstract void draw();

  public void setDrawingService(DrawingService draw) {
    this.draw = draw;
  }

}

public abstract class Circle extends Shape {

  protected int r;

  public Circle(int x, int y, int r) {
    super(x, y);
    this.r = r;
  }

  protected abstract void drawCircle(int x, int y, int r);

  public void draw() {
    drawCircle(x, y, r);
  }

}

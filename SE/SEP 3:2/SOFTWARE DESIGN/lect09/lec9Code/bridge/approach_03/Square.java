public class Square extends Shape {

  protected int length;

  public Square(int x, int y, int length) {
    super(x, y);
    this.length = length;
  }

  public void draw() {
    draw.drawLine(x, y, x + length, y);
    draw.drawLine(x + length, y, x + length, y + length);
    draw.drawLine(x + length, y + length, x, y + length);
    draw.drawLine(x, y + length, x, y);
  }

}

package Bridge.src_lab;

public class Printer {

  private static Printer printer;
  public void print_line(int x1, int y1, int x2, int y2) {
    System.out.println("Printer printing line");
  }

  public void print_circle(int x, int y, int r) {
    System.out.println("Printer printing circle");
  }


}

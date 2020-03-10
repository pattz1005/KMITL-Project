package L11_2;

public class VisitorDemo {

    static public void main(String[] args) {
        Car car = new Car();
        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
    }
}

package visitor;

public class CarElementDoVisitor implements CarElementVisitor {

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Kicking " + wheel.getName() + " wheel");
    }

    @Override
    public void visit(Engine engine) {
        System.out.println("Starting Engine");
    }

    @Override
    public void visit(Body body) {
        System.out.println("Waxing Body");
    }

    @Override
    public void visit(Car car) {
        System.out.println("Staring car");
    }
    
}

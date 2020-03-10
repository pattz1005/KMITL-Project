package L11_2;

interface CarElementVisitor {

    void visit(Wheel wheel);

    void visit(Engine engine);

    void visit(Body body);

    void visit(Car car);
}

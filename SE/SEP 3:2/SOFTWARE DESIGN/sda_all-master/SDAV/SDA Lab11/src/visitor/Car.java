package visitor;

class Car implements CarElement {

    CarElement[] elements;

    public Car() {
        this.elements = new CarElement[]{new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left"), new Wheel("back right"),
            new Body(), new Engine()};
    }

    public void accept(CarElementVisitor visitor) {
        CarElement[] temp = elements.clone();
        for (int i = 0; i < temp.length; i++) {
            temp[i].accept(visitor);
        }
        visitor.visit(this);
    }

}

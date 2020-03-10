package L11_2;

class Body implements CarElement {

    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}

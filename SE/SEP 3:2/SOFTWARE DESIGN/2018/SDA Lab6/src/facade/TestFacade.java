package facade;

public class TestFacade {

    public static void main(String[] args) {
        TeaFacade teaMaker = new TeaFacade(new TeaCup(), new Water(), new TeaInfuser());
        teaMaker.makeTea("Earl Grey");
    }
}

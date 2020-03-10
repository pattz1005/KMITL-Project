package facade;

public class TeaFacade {

    private TeaCup teacup;
    private Water water;
    private TeaInfuser teaInfuser;

    public TeaFacade(TeaCup teacup, Water water, TeaInfuser teaInfuser) {
        this.teacup = teacup;
        this.water = water;
        this.teaInfuser = teaInfuser;
    }

    public void makeTea(String teaType) {
        Tea tea = new Tea(teaType);
        teaInfuser.addTea(tea);
        water.boilWater();
        teacup.addWater(water);
        teacup.setSteepTime(15);
        teacup.steep();
    }
}

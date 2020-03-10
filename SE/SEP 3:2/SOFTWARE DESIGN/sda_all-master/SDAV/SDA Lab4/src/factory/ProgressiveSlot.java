package factory;

public class ProgressiveSlot extends Slot {

    SlotComponentFactory componentFactory;

    public ProgressiveSlot(SlotComponentFactory componentFactory) {
        this.componentFactory = componentFactory;
    }

    void build() {
        cabinet = componentFactory.createCabinet();
        display = componentFactory.createDisplay();
        software = componentFactory.createOS();
        payment = componentFactory.createPayment();
        gpu = componentFactory.createGPU();
    }
}

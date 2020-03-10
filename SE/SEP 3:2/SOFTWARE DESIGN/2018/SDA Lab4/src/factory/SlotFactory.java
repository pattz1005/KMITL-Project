package factory;

public abstract class SlotFactory {

    public Slot orderSlot(String type) {
        Slot slot = makeSlot(type);
        System.out.println("--- Making a " + slot.getName() + " ---");
        slot.build();
        return slot;
    }

    abstract Slot makeSlot(String type);
}

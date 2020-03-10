package factory;

public class NVSlotStore extends SlotStore {

    protected Slot makeSlot(slottype type) {
        if (type.equals(slottype.progressive)) {
            return new NVSlotProgressive();
        } else if (type.equals(slottype.straight)) {
            return new NVSlotStraight();
        } else if (type.equals(slottype.bonus)) {
            return new NVSlotBonus();
        } else {
            return null;
        }
    }
}

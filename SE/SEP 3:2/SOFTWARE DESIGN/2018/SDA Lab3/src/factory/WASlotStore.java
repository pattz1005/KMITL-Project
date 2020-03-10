package factory;

public class WASlotStore extends SlotStore {

    protected Slot makeSlot(slottype type) {
        if (type.equals(slottype.progressive)) {
            return new WASlotProgressive();
        } else if (type.equals(slottype.straight)) {
            return new WASlotStraight();
        } else if (type.equals(slottype.bonus)) {
            return new WASlotBonus();
        } else {
            return null;
        }
    }
}

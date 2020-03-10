package factory;

public class SlotMachineApp {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SlotFactory njFactory = new NJSlotFactory();
        SlotFactory nvFactory = new NVSlotFactory();
        SlotFactory waFactory = new WASlotFactory();
        
        Slot slot = nvFactory.orderSlot("straight");
        System.out.println("Taj Mahal ordered a " + slot + "\n");
        Slot slot2 = njFactory.orderSlot("bonus");
        System.out.println("Peppermill ordered a " + slot2 + "\n");
        Slot slot3 = waFactory.orderSlot("progressive");
        System.out.println("Yakuza ordered a " + slot3 + "\n");
    }
}

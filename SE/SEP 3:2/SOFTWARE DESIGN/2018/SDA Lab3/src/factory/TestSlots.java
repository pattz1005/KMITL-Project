package factory;

public class TestSlots {
	public static void main(String[] args) {
		SlotStore NVStore = new NVSlotStore();
		SlotStore NJStore = new NJSlotStore();
                SlotStore WAStore = new WASlotStore();
                
		Slot slot = NVStore.orderSlot(slottype.bonus);
		System.out.println("Taj Mahal ordered a "+ slot.getName());
                
                System.out.println("*************************");
                
		slot = NJStore.orderSlot(slottype.straight);
		System.out.println("Borgata ordered a "+ slot.getName());
                
                System.out.println("*************************");
                
		slot = WAStore.orderSlot(slottype.progressive);
		System.out.println("Ceasar ordered a "+ slot.getName());
	}

}

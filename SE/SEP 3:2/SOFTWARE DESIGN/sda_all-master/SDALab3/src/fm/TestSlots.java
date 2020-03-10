package fm;

public class TestSlots {
	public static void main(String[] args) {
		SlotStore NVStore = new NVSlotStore();
		SlotStore NJStore = new NJSlotStore();
		SlotStore WAStore = new WASlotStore();

		System.out.println("CASE 1:");
		Slot slot = NVStore.orderSlot(slottype.bonus);
		System.out.println("Taj Mahal ordered a "+ slot.getName());

		System.out.println("\nCASE 2:");
		slot = NJStore.orderSlot(slottype.straight);
		System.out.println("Borgata ordered a "+ slot.getName());

		System.out.println("\nCASE 3:");
		slot = WAStore.orderSlot(slottype.progressive);
		System.out.println("Jaxar ordered a "+ slot.getName());
	}
}

package iterator;

import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator {

    private ArrayList menuItems;
    private int counter = 0;

    public PancakeHouseMenuIterator(ArrayList menuItems) {
        this.menuItems = new ArrayList(menuItems);
    }

    public boolean hasNext() {
        return counter < menuItems.size();
    }

    public Object next() {
        return menuItems.get(counter++);
    }

}

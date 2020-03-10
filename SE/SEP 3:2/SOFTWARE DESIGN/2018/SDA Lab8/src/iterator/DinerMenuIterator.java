package iterator;

public class DinerMenuIterator implements Iterator {
    
    private MenuItem[] menuItems;
    private int counter = 0;
    
    public DinerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems.clone();
    }

    public boolean hasNext() {
        return counter < menuItems.length;
    }

    public Object next() {
        return menuItems[counter++];
    }
    
}

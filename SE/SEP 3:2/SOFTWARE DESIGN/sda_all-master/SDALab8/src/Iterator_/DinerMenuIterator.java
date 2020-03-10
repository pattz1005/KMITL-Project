package Iterator_;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator {
    int idx = 0;
    MenuItem[] mi;

    DinerMenuIterator(MenuItem[] mi){
        this.mi = mi.clone();
    }

    @Override
    public boolean hasNext() {
        return idx < mi.length;
    }

    @Override
    public Object next() {
        return mi[idx++];
    }
}

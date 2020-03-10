package composite;

import java.util.ArrayList;
import java.util.Iterator;

public class Composite extends Prim {
    
    private ArrayList<Prim> prims;
    
    public Composite() {
        prims = new ArrayList<>();
    }
    
    public void add(Prim m) {
        prims.add(m);
    }

    public void render() {
        for (Prim m : prims) {
            m.render();
        }
    }

    public float volume() {
        float result = 0;
        for (Prim m : prims) {
            result += m.volume();
        }
        return result;
    }

    public Iterator createIterator() {
        return new CompositeIterator(prims.iterator());
    }
    
}

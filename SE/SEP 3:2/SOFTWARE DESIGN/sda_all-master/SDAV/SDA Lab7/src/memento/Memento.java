package memento;

import java.io.Serializable;
import java.util.Vector;

public abstract class Memento implements Serializable {
    
    public abstract Vector getColumnIdentifiers();
    public abstract Vector getDataVector();
    
}

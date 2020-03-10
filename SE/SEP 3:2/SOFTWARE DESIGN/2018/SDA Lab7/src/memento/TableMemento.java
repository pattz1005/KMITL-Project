package memento;

import java.io.Serializable;
import java.util.*;

public class TableMemento extends Memento implements Serializable {

    private Vector columnIdentifiers;
    private Vector dataList;

    public TableMemento(Vector columnIdentifiers, Vector dataList) {
        this.columnIdentifiers = new Vector(columnIdentifiers);
        this.dataList = new Vector(dataList);
    }

    @Override
    public Vector getColumnIdentifiers() {
        return this.columnIdentifiers;
    }

    @Override
    public Vector getDataVector() {
        return this.dataList;
    }

    @Override
    public String toString() {
        return "[ci=" + columnIdentifiers.toString() + "\n"
                + "dl=" + dataList.toString() + "]";
    }
}

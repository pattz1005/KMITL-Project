package memento;


import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.*;

public class ScheduleTableModel extends DefaultTableModel {
    public TableMemento memento;
    // DefaultTableModel uses:
    // - a list of column identifiers
    // - a list of lists of Object values
    // This fact is reflected by the Memento class.

    public ScheduleTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public TableMemento createMemento() {
        // COMPLETE.
        ArrayList tempIdentifier = new ArrayList();
        ArrayList tempData = new ArrayList();
        for (Object object : this.columnIdentifiers) {
            object = (String) object;
            tempIdentifier.add(object.toString());
        }

        for (Object object : this.dataVector) {
            Vector vector = (Vector) object;
            ArrayList temp = new ArrayList();
            for (Object object2 : vector) {
                temp.add(object2.toString());
            }
            tempData.add(temp);
        }
        return new TableMemento(tempIdentifier, tempData);

    }

    public void setMemento(TableMemento memento) {
        // COMPLETE.
        this.memento = memento;

    }

    public void revert() {
        Vector tempIdentifier = new Vector();
        Vector tempData = new Vector();
        for (Object object : memento.getColumnIdentifiers()) {
            tempIdentifier.add(object);
        }
        for (Object object : memento.getDataVector()) {
            ArrayList list = (ArrayList) object;
            Vector temp = new Vector();
            for (Object object2 : list) {
                temp.add(object2);
            }
            tempData.add(temp);
        }
        this.setDataVector(tempData, tempIdentifier);
    }
}

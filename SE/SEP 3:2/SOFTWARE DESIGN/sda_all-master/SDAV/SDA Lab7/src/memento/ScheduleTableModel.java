package memento;

import javax.swing.table.*;

public class ScheduleTableModel extends DefaultTableModel {

    public ScheduleTableModel(Memento m) {
        super(m.getDataVector(), m.getColumnIdentifiers());
    }

    public TableMemento createMemento() {
        return new TableMemento(this.columnIdentifiers, this.dataVector);
    }

    public void setMemento(Memento memento) {
        this.setDataVector(memento.getDataVector(), memento.getColumnIdentifiers());
    }
}

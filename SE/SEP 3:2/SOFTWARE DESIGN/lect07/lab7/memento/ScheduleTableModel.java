package memento;

 

import javax.swing.table.*;

public class ScheduleTableModel extends DefaultTableModel {

    // DefaultTableModel uses:
    // - a list of column identifiers
    // - a list of lists of Object values
    // This fact is reflected by the Memento class.

    public ScheduleTableModel(Object[][] data, Object[] columnNames)
    {
	super(data, columnNames);
    }

    public TableMemento createMemento()
    {
	// COMPLETE.
        throw new UnsupportedOperationException();


    }

    public void setMemento(TableMemento memento)
    {
	// COMPLETE.
        throw new UnsupportedOperationException();



    }
}

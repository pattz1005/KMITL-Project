package memento;

 

import java.util.*;


// Value object.
//
public class TableMemento implements java.io.Serializable {

    private List columnIdentifiers;
    private List dataList;

    // Package visibility to only allow access for
    // the Originator.
    // We receive from the TableModel:
    // - a List of column identifiers
    // - a List of Lists of Object values (assumption: Strings)

    TableMemento(ArrayList columnIdentifiers, ArrayList dataList)
    {
	// COMPLETE.


    }

    // Package visibility to only allow access for
    // the Originator.
    ArrayList getColumnIdentifiers()
    {
	// COMPLETE.
        throw new UnsupportedOperationException();


    }

    ArrayList getDataVector()
    {
	// COMPLETE.
        throw new UnsupportedOperationException();



    }

    @Override
    public String toString() {
	return "[ci=" + columnIdentifiers.toString() + "\n" +
	    "dl=" + dataList.toString() + "]";
    }

    // Other private (static) helper methods.
    // COMPLETE if necessary.

}

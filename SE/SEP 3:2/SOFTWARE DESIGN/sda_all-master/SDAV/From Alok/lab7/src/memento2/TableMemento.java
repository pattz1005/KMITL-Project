package memento2;

 

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
    
    TableMemento(){
        
    }

    TableMemento(ArrayList columnIdentifiers, ArrayList dataList)
    {
	// COMPLETE.
        this.columnIdentifiers = columnIdentifiers;
        this.dataList = dataList;


    }

    // Package visibility to only allow access for
    // the Originator.
    ArrayList getColumnIdentifiers()
    {
	// COMPLETE.
        return (ArrayList)this.columnIdentifiers;


    }

    ArrayList getDataVector()
    {
	// COMPLETE.
        return (ArrayList)this.dataList;



    }

    @Override
    public String toString() {
	return "[ci=" + columnIdentifiers.toString() + "\n" +
	    "dl=" + dataList.toString() + "]";
    }

    // Other private (static) helper methods.
    // COMPLETE if necessary.

}

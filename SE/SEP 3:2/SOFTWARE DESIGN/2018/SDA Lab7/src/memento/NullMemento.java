package memento;

import java.util.Vector;

public class NullMemento extends Memento {
    
    private static final Object[][] data = {
        {"08.00-09.00", "----", "Math", "", "", ""},
        {"09.00-10.00", "", "Math", "", "", ""},
        {"10.00-11.00", "", "", "SE 2", "", "Math"},
        {"11.00-12.00", "", "", "SE 2", "", "Math"},
        {"12.00-13.00", "----", "----", "----", "----", "----"},
        {"13.00-14.00", "SE 2", "OpSys", "", "", ""},
        {"14.00-15.00", "SE 2", "OpSys", "", "Project", ""},
        {"15.00-16.00", "", "OpSys", "", "Project", ""},
        {"16.00-17.00", "", "OpSys", "", "Project", ""},
        {"17.00-18.00", "", "", "", "Project", ""}
    };

    private static final String[] columnNames = {
        "Time",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"
    };

    @Override
    public Vector getColumnIdentifiers() {
        Vector result = new Vector();
        for (String s : columnNames) {
            result.add(s);
        }
        return result;
    }

    @Override
    public Vector getDataVector() {
        Vector<Vector<Object>> result = new Vector<Vector<Object>>();
        for (int i = 0; i < data.length; i++) {
            Vector<Object> temp = new Vector<Object>();
            for (int j = 0; j < data[i].length; j++) {
                temp.add(data[i][j]);
            }
            result.add(temp);
        }
        return result;
    }
}

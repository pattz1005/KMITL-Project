package Adapter;

public class RecordAdapter extends Database {
    
    private Records records;
    
    public RecordAdapter(Records r) {
        records = r;
    }
    
    @Override
    public void addEmployee(Employee e) {
        super.addEmployee(e);
    }
    
    @Override
    public void deleteEmployee(long e_num) {
        records.remove(e_num);
        super.deleteEmployee(e_num);
    }
    
    public boolean isEmployee(long e_num) {
        boolean result = records.isEmployee(e_num);
        if (result) return result;
        for(Employee e: employees) {
            if(e.getEmpNum() == e_num) return true;
        }
        return result;
    }
    
    @Override
    public void printEmployee() {
        records.printEmployee();
        super.printEmployee();
    }
}

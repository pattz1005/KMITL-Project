package Adapter;

import java.util.Vector;

public class Database {

    protected Vector<Employee> employees; //Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void deleteEmployee(long emp_num) {
        for(Employee e:employees) {
            if(e.getEmpNum() == emp_num) {
                employees.remove(e);
                return;
            }
        }
    }
    
    public void printEmployee() {
        for (Employee e: employees) {
            System.out.println(e.getEmpNum()+ " " + e.getName() + " " + e.getSurname());
        }
    }

}

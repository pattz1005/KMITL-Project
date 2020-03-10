package Adapter;

import java.util.ArrayList;

public class Records {

    private ArrayList<Employee> employees; //Stores the employees

    public Records() {
        employees = new ArrayList<>();
    }

    public void insert(Employee employee) {
        employees.add(employee);
    }

    public void remove(long emp_num) {
        for (Employee e : employees) {
            if (e.getEmpNum() == emp_num) {
                employees.remove(e);
                return;
            }
        }
    }

    public boolean isEmployee(long emp_num) {
        boolean result = false;
        for (Employee e : employees) {
            if (e.getEmpNum() == emp_num) {
                result = true;
            }
        }
        return result;
    }
    
    public void printEmployee() {
        for (Employee e: employees) {
            System.out.println(e.getEmpNum()+ " " + e.getName() + " " + e.getSurname());
        }
    }

}

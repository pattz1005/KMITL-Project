package Adapter;

public class Employee {
 
    private String name;
    private String surname;
    private long emp_num;
    private double salary;
 
    public Employee(String name, String surname, long emp_num, double salary) {
        this.name = name;
        this.surname = surname;
        this.emp_num = emp_num;
        this.salary = salary;
    }

 
    public String getName() {
        return name;
    }
 
    public String getSurname() {
        return surname;
    }

    public long getEmpNum() {
        return emp_num;
    }
  
    public double getSalary() {
        return salary;
    }
  
}

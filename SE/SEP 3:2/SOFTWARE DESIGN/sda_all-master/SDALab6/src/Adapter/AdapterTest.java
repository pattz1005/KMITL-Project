package Adapter;

public class AdapterTest {
    
    public static void main(String[] args) {
        Records r = new Records();
        System.out.println("Insert from Records");
        r.insert(new Employee("Emp1", "One", 1, 110));
        r.insert(new Employee("Emp2", "Two", 2, 125));
        
        RecordAdapter ra = new RecordAdapter(r);
        System.out.println("Insert from Record Adapter");
        ra.addEmployee(new Employee("Emp3", "Three", 3,136));
        ra.addEmployee(new Employee("Emp4", "Four", 4, 148));

        System.out.println("Print all employees:");
        ra.printEmployee();
        
        System.out.println("Delete Employee number 2, 3");
        ra.deleteEmployee(2);
        ra.deleteEmployee(3);
        ra.printEmployee();
          
        System.out.println("Check Employee id 1: " + ra.isEmployee(1));
        System.out.println("Check Employee id 4: " + ra.isEmployee(2));
    }
}

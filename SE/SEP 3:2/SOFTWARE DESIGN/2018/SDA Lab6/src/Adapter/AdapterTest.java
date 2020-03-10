package Adapter;

public class AdapterTest {
    
    public static void main(String[] args) {
        Records r = new Records();
        r.insert(new Employee("John", "Scoop", 1, 1000));
        r.insert(new Employee("Leslie", "Chow", 2, 2000));
        
        RecordAdapter ra = new RecordAdapter(r);
        ra.addEmployee(new Employee("Jason", "Statham", 3,3000));
        ra.addEmployee(new Employee("Mark", "Webb", 4, 4000));
        
        ra.printEmployee();
        
        System.out.println("*******************************");
        System.out.println("Delete Employee 2, 4");
        ra.deleteEmployee(2);
        ra.deleteEmployee(4);
        ra.printEmployee();
          
        System.out.println("*******************************");
        System.out.println("Check Employee id 1: " + ra.isEmployee(1));
        System.out.println("Check Employee id 2: " + ra.isEmployee(2));
    }
}

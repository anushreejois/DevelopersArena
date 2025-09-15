class Employee {
    private String name;
    private int id;
    private double salary;
    private String department;

    // Constructor
    public Employee(String name, int id, double salary, String department) {
        this.name = name;
        this.id = id;
        setSalary(salary); // Using setter for validation
        this.department = department;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Invalid name!");
        }
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            System.out.println("Salary must be positive!");
            this.salary = 0;
        }
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void displayEmployee() {
        System.out.println("Employee Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + salary);
        System.out.println("Department: " + department);
    }
}

public class EmployeeTest {
    public static void main(String[] args) {
        Employee emp = new Employee("John Doe", 101, 50000, "IT");
        emp.displayEmployee();

        // Using getters
        System.out.println("\nUsing getters:");
        System.out.println("Name: " + emp.getName());
        System.out.println("Salary: $" + emp.getSalary());

        // Using setters
        emp.setSalary(55000);
        emp.setDepartment("HR");
        emp.displayEmployee();

        // Testing validation
        emp.setSalary(-1000); // Should show error
    }
}

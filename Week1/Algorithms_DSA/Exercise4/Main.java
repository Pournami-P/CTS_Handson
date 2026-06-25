//Exercise 4: Employee Management System
class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
}

class EmployeeSystem {
    Employee[] emp = new Employee[100];
    int size = 0;

    void add(Employee e) {
        emp[size] = e;
        size++;
    }

    int search(int id) {
        for (int i = 0; i < size; i++) {
            if (emp[i].employeeId == id)
                return i;
        }
        return -1;
    }


    void traverse() {
        System.out.println("Employee Details:");
        for (int i = 0; i < size; i++) {
            System.out.println(
                    emp[i].employeeId + " "
                    + emp[i].name + " "
                    + emp[i].position + " "
                    + emp[i].salary);
        }
    }

    void delete(int id) {
        int index = search(id);

        if (index == -1) {
            System.out.println("Employee not found.");
            return;
        }

        for (int i = index; i < size - 1; i++) {
            emp[i] = emp[i + 1];
        }

        size--;
        System.out.println("Employee deleted successfully.");
    }
}

public class Main {
    public static void main(String[] args) {

        EmployeeSystem es = new EmployeeSystem();

        es.add(new Employee(101, "Alice", "Manager", 60000));
        es.add(new Employee(102, "Bob", "Developer", 50000));
        es.add(new Employee(103, "Charlie", "Tester", 45000));
        es.add(new Employee(104, "David", "HR", 40000));

        System.out.println("All Employees:");
        es.traverse();

        int index = es.search(102);

        if (index != -1)
            System.out.println("\nEmployee Found: " + es.emp[index].name);
        else
            System.out.println("\nEmployee Not Found");

        System.out.println("\nDeleting Employee with ID 103");
        es.delete(103);

        System.out.println("\nEmployees after deletion:");
        es.traverse();
    }
}
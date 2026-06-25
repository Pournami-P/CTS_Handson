//Exercise 11: Implementing Dependency Injection
import java.util.HashMap;
import java.util.Map;

class Customer {
    private int id;
    private String name;

    public Customer(int id, String name) { this.id = id; this.name = name; }

    public int getId()     { return id; }
    public String getName() { return name; }
}

interface CustomerRepository {
    Customer findCustomerById(int id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    private Map<Integer, Customer> db = new HashMap<>();

    public CustomerRepositoryImpl() {
        db.put(1, new Customer(1, "Alice"));
        db.put(2, new Customer(2, "Bob"));
        db.put(3, new Customer(3, "Charlie"));
    }

    public Customer findCustomerById(int id) {
        return db.get(id);
    }
}

class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void printCustomer(int id) {
        Customer c = repository.findCustomerById(id);
        if (c != null)
            System.out.println("Found Customer -> ID: " + c.getId() + ", Name: " + c.getName());
        else
            System.out.println("Customer with ID " + id + " not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo    = new CustomerRepositoryImpl();
        CustomerService    service = new CustomerService(repo);

        service.printCustomer(1);
        service.printCustomer(2);
        service.printCustomer(99);
    }
}
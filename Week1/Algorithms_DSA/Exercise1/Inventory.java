//Exercise 1: Inventory Management System

import java.util.HashMap;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    Product(int id, String name, int qty, double price) {
        this.productId = id;
        this.productName = name;
        this.quantity = qty;
        this.price = price;
    }
}

public class Inventory {

    HashMap<Integer, Product> map = new HashMap<>();

    void add(Product p) {
        map.put(p.productId, p);
    }

    void update(int id, int qty, double price) {
        if (map.containsKey(id)) {
            map.get(id).quantity = qty;
            map.get(id).price = price;
        }
    }

    void delete(int id) {
        map.remove(id);
    }

    void display() {
        for (Product p : map.values()) {
            System.out.println(
                p.productId + " " +
                p.productName + " " +
                p.quantity + " " +
                p.price
            );
        }
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.add(new Product(101, "Laptop", 5, 55000));
        inventory.add(new Product(102, "Mouse", 20, 700));
        inventory.add(new Product(103, "Keyboard", 15, 1200));

        System.out.println("Initial Inventory:");
        inventory.display();

        inventory.update(101, 8, 53000);

        System.out.println("\nAfter Update:");
        inventory.display();

        inventory.delete(102);

        System.out.println("\nAfter Delete:");
        inventory.display();
    }
}
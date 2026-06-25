//Exercise 2: E-commerce Platform Search Function
class Product {
    int productId;
    String productName;
    String category;

    Product(int id, String name, String cat) {
        this.productId = id;
        this.productName = name;
        this.category = cat;
    }
}

public class SearchProduct {

    static int linearSearch(Product[] arr, int targetId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].productId == targetId) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Product[] arr, int targetId) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].productId == targetId) {
                return mid;
            } else if (arr[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(103, "Keyboard", "Accessories"),
                new Product(101, "Laptop", "Electronics"),
                new Product(104, "Monitor", "Electronics"),
                new Product(102, "Mouse", "Accessories"),
                new Product(105, "Printer", "Office")
        };

        int target = 102;

        int linearResult = linearSearch(products, target);

        if (linearResult != -1) {
            System.out.println("Linear Search:");
            System.out.println("Product Found: "
                    + products[linearResult].productName);
        } else {
            System.out.println("Product Not Found");
        }

        Product[] sortedProducts = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mouse", "Accessories"),
                new Product(103, "Keyboard", "Accessories"),
                new Product(104, "Monitor", "Electronics"),
                new Product(105, "Printer", "Office")
        };

        int binaryResult = binarySearch(sortedProducts, target);

        if (binaryResult != -1) {
            System.out.println("\nBinary Search:");
            System.out.println("Product Found: "
                    + sortedProducts[binaryResult].productName);
        } else {
            System.out.println("Product Not Found");
        }
    }
}
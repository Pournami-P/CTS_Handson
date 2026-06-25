class Order {
    int orderId;
    String customerName;
    double totalPrice;

    Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
}

public class Main {

    static void bubbleSort(Order[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].totalPrice > arr[j + 1].totalPrice) {
                    Order temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Order[] arr, int low, int high) {

        double pivot = arr[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].totalPrice <= pivot) {
                i++;

                Order temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Order temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void display(Order[] arr) {
        System.out.println("OrderID\tCustomer\tTotal Price");

        for (Order o : arr) {
            System.out.println(o.orderId + "\t" + o.customerName + "\t\t" + o.totalPrice);
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Order[] orders = {
                new Order(101, "Alice", 5000),
                new Order(102, "Bob", 2500),
                new Order(103, "Charlie", 7000),
                new Order(104, "David", 4500)
        };

        System.out.println("Before Bubble Sort:");
        display(orders);

        bubbleSort(orders);

        System.out.println("After Bubble Sort:");
        display(orders);

        // New array for Quick Sort
        Order[] orders2 = {
                new Order(101, "Alice", 5000),
                new Order(102, "Bob", 2500),
                new Order(103, "Charlie", 7000),
                new Order(104, "David", 4500)
        };

        System.out.println("Before Quick Sort:");
        display(orders2);

        quickSort(orders2, 0, orders2.length - 1);

        System.out.println("After Quick Sort:");
        display(orders2);
    }
}
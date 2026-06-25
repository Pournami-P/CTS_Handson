//Exercise 6: Library Management System
class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
}

public class Main {

    static int linearSearch(Book[] books, String title) {

        for (int i = 0; i < books.length; i++) {
            if (books[i].title.equals(title))
                return i;
        }

        return -1;
    }

    static int binarySearch(Book[] books, String title) {

        int low = 0;
        int high = books.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int cmp = books[mid].title.compareTo(title);

            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    static void display(Book b) {
        System.out.println("Book ID : " + b.bookId);
        System.out.println("Title   : " + b.title);
        System.out.println("Author  : " + b.author);
    }

    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "C Programming", "Dennis Ritchie"),
                new Book(102, "Data Structures", "Mark Allen"),
                new Book(103, "Java Programming", "James Gosling"),
                new Book(104, "Python Programming", "Guido van Rossum")
        };

        System.out.println("Linear Search:");

        int index = linearSearch(books, "Java Programming");

        if (index != -1)
            display(books[index]);
        else
            System.out.println("Book Not Found");

        System.out.println("\nBinary Search:");

        index = binarySearch(books, "Java Programming");

        if (index != -1)
            display(books[index]);
        else
            System.out.println("Book Not Found");
    }
}
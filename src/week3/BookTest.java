class Book {
    String title;
    String author;
    double price;

    // Default constructor
    Book() {
        title = "Unknown";
        author = "Unknown";
        price = 0.0;
        System.out.println("Default constructor called");
    }

    // Parameterized constructor
    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        System.out.println("Parameterized constructor called");
    }

    void displayBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("-------------------");
    }
}

public class BookTest {
    public static void main(String[] args) {
        Book book1 = new Book(); // Default constructor
        Book book2 = new Book("Java Programming", "John Smith", 29.99); // Parameterized

        book1.displayBook();
        book2.displayBook();
    }
}

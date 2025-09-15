class Rectangle {
    double length;
    double width;

    // Default constructor - creates unit square
    Rectangle() {
        length = 1.0;
        width = 1.0;
    }

    // Constructor with one parameter - creates square
    Rectangle(double side) {
        length = side;
        width = side;
    }

    // Constructor with two parameters - creates rectangle
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    double calculateArea() {
        return length * width;
    }

    void displayInfo() {
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Area: " + calculateArea());
        System.out.println("-------------------");
    }
}

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();           // Default
        Rectangle r2 = new Rectangle(5.0);       // Square
        Rectangle r3 = new Rectangle(4.0, 6.0);  // Rectangle

        r1.displayInfo();
        r2.displayInfo();
        r3.displayInfo();
    }
}


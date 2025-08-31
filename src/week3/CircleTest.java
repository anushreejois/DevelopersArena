class Circle {
    private double radius;
    private static final double PI = 3.14159;

    public Circle(double radius) {
        setRadius(radius);
    }

    // Getter
    public double getRadius() {
        return radius;
    }

    // Setter with validation
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            System.out.println("Radius must be positive!");
            this.radius = 1.0; // Default value
        }
    }

    // Calculated properties (read-only)
    public double getArea() {
        return PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * PI * radius;
    }

    public void displayCircle() {
        System.out.println("Circle Details:");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + getArea());
        System.out.println("Circumference: " + getCircumference());
        System.out.println("-------------------");
    }
}

public class CircleTest {
    public static void main(String[] args) {
        Circle c1 = new Circle(5.0);
        c1.displayCircle();

        // Testing getters
        System.out.println("Current radius: " + c1.getRadius());

        // Testing setters
        c1.setRadius(7.0);
        c1.displayCircle();

        // Testing validation
        c1.setRadius(-3.0); // Should show error and set default
        c1.displayCircle();
    }
}


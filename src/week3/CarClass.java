class Car {
    String brand;
    String model;
    int year;

    void startEngine() {
        System.out.println(brand + " " + model + " engine started!");
    }

    void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }
}

public class CarClass {
    public static void main(String[] args) {
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.model = "Camry";
        car1.year = 2023;

        car1.displayDetails();
        car1.startEngine();
    }
}

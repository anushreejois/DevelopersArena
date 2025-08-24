public class Functions {



    // Function without parameters
    public static void greet() {
        System.out.println("Hello, welcome to Java!");
    }

    // Function with parameters
    public static int add(int a, int b) {
        return a + b;
    }

    // Function with return type
    public static String getMessage(String name) {
        return "Hello " + name + ", have a great day!";
    }

    public static void main(String[] args) {
        greet();  // call function

        int sum = add(10, 20);
        System.out.println("Sum = " + sum);

        String msg = getMessage("Anuu");
        System.out.println(msg);
    }
}

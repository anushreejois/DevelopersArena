public class OperatorsDemo {
    public static void main(String[] args) {
        int a = 10, b = 3;

        // Arithmetic
        System.out.println("Addition: " + (a + b));
        System.out.println("Modulo: " + (a % b));

        // Relational
        System.out.println("Is a > b? " + (a > b));

        // Logical
        boolean x = true, y = false;
        System.out.println("x AND y: " + (x && y));

        // Ternary
        String result = (a > b) ? "a is bigger" : "b is bigger";
        System.out.println(result);
    }
}

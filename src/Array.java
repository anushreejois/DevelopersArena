import java.util.Scanner;

    public class Array {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int[] numbers = new int[5];

            // Input values
            System.out.println("Enter 5 numbers:");
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = sc.nextInt();
            }

            // Display values
            System.out.println("You entered:");
            for (int num : numbers) {
                System.out.print(num + " ");
            }

            sc.close();
        }
    }



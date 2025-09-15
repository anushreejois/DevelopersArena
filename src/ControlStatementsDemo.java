public class ControlStatementsDemo {
    public static void main(String[] args) {
        int marks = 85;

        // if-else
        if (marks >= 90) {
            System.out.println("Grade A+");
        } else if (marks >= 75) {
            System.out.println("Grade A");
        } else {
            System.out.println("Keep practicing!");
        }

        // switch
        int day = 3;
        switch(day) {
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            default -> System.out.println("Another day");
        }
    }
}

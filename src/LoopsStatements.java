public class LoopsStatements{
    public static void main(String[] args) {
        // for loop
        for(int i=1; i<=5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // while loop
        int j = 1;
        while(j <= 5) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();

        // do-while loop
        int k = 1;
        do {
            System.out.print(k + " ");
            k++;
        } while(k <= 5);
    }
}

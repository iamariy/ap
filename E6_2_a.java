import java.util.Scanner;

public class E6_2_a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter size of array");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        int i;
        for (i = 0; i < arr.length; i++) {
            System.out.println("enter array elements");
            arr[i] = scanner.nextInt();
        }

        int max = arr[0], min = arr[0];
        for (i = 0; i < args.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        System.out.println("max is"+ max);
        System.out.println("min is"+ min);
    }
}

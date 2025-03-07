import java.util.Scanner;

public class E6_2_d {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter size of array");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        int i;
        for (i = 0; i < arr.length; i++)
        {
            System.out.println("enter array elements");
            arr[i] = scanner.nextInt();
        }

        for (i=0;i< arr.length;i++)
        {
            if (arr[i]==arr[i+1]) {
                if (i==0 || arr[i]!=arr[i-1]) System.out.println(arr[i] + " ");
            }
        }
    }
}

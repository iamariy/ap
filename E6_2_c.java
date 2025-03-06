import java.util.Scanner;

public class E6_2_c {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter size of array");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        int i;
        for (i = 0; i < n; i++)
        {
            System.out.println("enter array elements");
            arr[i] = scanner.nextInt();
        }
        int sum=0;
        for (i=0;i<n;i++)
        {
            sum+=arr[i];
            System.out.println(sum);
        }
    }
}

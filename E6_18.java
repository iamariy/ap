import java.util.Scanner;

public class E6_18 {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter number for lentgh");
        int n=scanner.nextInt();

        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n-i-1;j++)
            {
                System.out.print(" ");
            }
            for (int j=0;j<2*i+1;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i=n-2;i>=0;i--)
        {
            for (int j=0;j<n-i-1;j++)
            {
                System.out.print(" ");
            }
            for (int j=0;j<2*i+1;j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

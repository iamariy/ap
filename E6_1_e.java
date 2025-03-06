import java.util.Scanner;

public class E6_1_e {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.print("enter number");
        int n=scanner.nextInt();

        int sum=0;

        while (n>0)
        {
            sum+=n%10;
            n=n/10;
        }

        System.out.println("Sum is:"+ sum);
    }
}

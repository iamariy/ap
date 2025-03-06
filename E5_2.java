import java.util.Scanner;

public class E5_2 {
    public static void main(String[] args)
    {
        float n;
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter number");
        n = scanner.nextFloat();


        if (Math.abs(n)<1 && n>0) System.out.println("Small Positive");
        else if (Math.abs(n)<1 && n<0) System.out.println("Small Negative");
        else if (Math.abs(n)>1000000 && n>0) System.out.println("Large Positive");
        else if (Math.abs(n)>1000000 && n<0) System.out.println("Large Negative");
        else if (n==0) System.out.println("zero");
        else System.out.println("the number is not available");
    }
}

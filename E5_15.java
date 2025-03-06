import java.util.Scanner;

public class E5_15 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.print("enter solary");
        int n=scanner.nextInt();

        if (n==50000) System.out.println(n*0.01);
        if (n>50000 && n<75000) System.out.println(n*0.02);
        if (n>75000 && n<100000) System.out.println(n*0.03);
        if (n>100000 && n<250000) System.out.println(n*0.04);
        if (n>250000 && n<500000) System.out.println(n*0.05);
        if (n>500000) System.out.println(n*0.06);
    }
}

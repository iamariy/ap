import java.util.Scanner;

public class E6_13 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("enter number");
        int num=scanner.nextInt();

        StringBuilder bin=new StringBuilder();

        while (num>0)
        {
            bin.append(num%2);
            num/=2;
        }
        String recerving=new  StringBuilder(bin).reverse().toString();

        for (int i=0;i<recerving.length();i++)
        {
            System.out.println(recerving.charAt(i));
        }
    }
}

import java.util.Scanner;

public class E6_3_b {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter string");
        String str = scanner.nextLine();

        for (int i=1;i<str.length();i+=2)
        {
            System.out.println(str.charAt(i));
        }
    }
}

import java.util.Scanner;

public class E6_3_a {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter string");
        String str = scanner.nextLine();

        for (int i = 0; i < str.length(); i++)
        {
            if (Character.isUpperCase(str.charAt(i))) System.out.println(str.charAt(i));
        }
    }
}

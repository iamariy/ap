import java.util.Scanner;

public class E6_3_c {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter string");
        String str1 = scanner.nextLine();

        String str2=str1.replaceAll("[AEIOUaeiou]","_");
        System.out.println(str2);
    }
}

import java.util.Arrays;
import java.util.Scanner;

public class E5_18 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("enter first string");
        String str1=scanner.nextLine();
        System.out.println("enter second string");
        String str2=scanner.nextLine();
        System.out.println("enter third string");
        String str3=scanner.nextLine();

        String[] strings={str1,str2,str3};
        Arrays.sort(strings);

        System.out.println("Sorted Strings:");
        for (int i=0;i<strings.length;i++)
        {
            System.out.println(strings[i]);
        }
    }
}

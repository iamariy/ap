import java.util.Scanner;

public class E6_3_d {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter string");
        String str= scanner.nextLine();

        int count=0;
        for (int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if (ch=='a' || ch=='e' || ch=='o' || ch=='i' || ch=='u' || ch=='A' || ch=='E' || ch=='O' || ch=='I' || ch=='U')
                count++;
        }
        System.out.println("number of vowels: "+ count);
    }
}

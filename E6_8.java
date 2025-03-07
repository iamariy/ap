import java.util.Scanner;

public class E6_8 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("enter word");
        String word=scanner.nextLine();

        for (int i=0;i<word.length();i++)
        {
            System.out.println(word.charAt(i));
        }
    }
}

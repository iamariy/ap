import java.util.Scanner;

public class E6_9 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);

        System.out.println("enter word");
        String word=scanner.nextLine();
        String reserving=new StringBuilder(word).reverse().toString();

        System.out.println("reserved word is "+ reserving);
    }
}

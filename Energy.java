import java.util.Scanner;

public class Energy {
    public static void main(String[] args)
    {
        final float g=9.8f;

        Scanner scanner=new Scanner(System.in);

        System.out.print("enter mass");
        float m=scanner.nextFloat();

        System.out.print("enter height");
        float h=scanner.nextFloat();

        float U=m*g*h;

        System.out.print("Potential Energy:" +U);
    }
}
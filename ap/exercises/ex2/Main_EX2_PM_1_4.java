package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_4 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        while (true)
        {
            System.out.println("enter character:");
            String input=scanner.nextLine();
            switch (input)
            {
                case "w":
                    System.out.println("UP");
                    break;
                case "a":
                    System.out.println("LEFT");
                    break;
                case "s":
                    System.out.println("DOWN");
                    break;
                case "d":
                    System.out.println("RIGHT");
                    break;
                case "q":
                    System.out.println("EXIT");
                    return;
                default:
                    System.out.println("WARNING");
                    break;
            }
        }
    }
}

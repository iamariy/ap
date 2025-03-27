package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_2_1 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter k");
        int k=scanner.nextInt();
        scanner.nextLine();

        char[][] square=new char[k+2][k+2];
        for (int i=0;i<k+2;i++)
        {
            for (int j=0;j<k+2;j++)
            {
                if (i==0 || i==k+1 || j==0 || j==k+1) square[i][j]='*';
                else square[i][j]=' ';
            }
        }

        int xDot=k/2;
        int yDot=k/2;
        square[xDot][yDot]='X';
        print(square);

        while (true)
        {
            System.out.println("enter character:");
            String input=scanner.nextLine();

            int newX=xDot;
            int newY=yDot;
            switch (input)
            {
                case "w":
                    newX--;
                    break;
                case "d" :
                    newY++;
                    break;
                case "s":
                    newX++;
                    break;
                case "a":
                    newY--;
                    break;
                case "q":
                    System.out.println("EXIT");
                    return;
                default:
                    System.out.println("WARNING");
                    break;
            }
            if (square[newX][newY]=='*')
            {
                System.out.println("Hitting the game wall");
                return;
            }
            else
            {
                square[xDot][yDot]=' ';
                xDot=newX;
                yDot=newY;
                square[xDot][yDot]='X';
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            print(square);
        }
    }
    public static void print(char[][] matrix)
    {
        for (int i=0;i< matrix.length;i++)
        {
            for (int j=0;j< matrix.length;j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}

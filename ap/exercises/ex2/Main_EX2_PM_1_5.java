package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_5 {
    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter k");
        int k=scanner.nextInt();

        char[][] square=new char[k+2][k+2];
        for (int i=0;i<k+2;i++)
        {
            for (int j=0;j<k+2;j++)
            {
                if (i==0 || i==k+1 || j==0 || j==k+1) square[i][j]='*';
                else square[i][j]=' ';
            }
        }

        Random random=new Random();
        int xDot=k/2;
        int yDot=k/2;
        square[xDot][yDot]='X';
        print(square);

        while (true)
        {
            int carry=random.nextInt(4);
            System.out.println("the random number is :"+ carry);

            int newX=xDot;
            int newY=yDot;

            switch (carry)
            {
                case 0:
                    newX--;
                    break;
                case 1:
                    newY++;
                    break;
                case 2:
                    newX++;
                    break;
                case 3:
                    newY--;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
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

package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter k");
        int k = scanner.nextInt();

        char[][] square = new char[k + 2][k + 2];
        for (int i = 0; i < k + 2; i++) {
            for (int j = 0; j < k + 2; j++) {
                if (i == 0 || i == k + 1 || j == 0 || j == k + 1) square[i][j] = '*';
                else square[i][j] = ' ';
            }
        }

        System.out.println("enter c");
        int c = scanner.nextInt();
        scanner.nextLine();
        while (c > k * k) {
            System.out.println("invalid number,please enter again c:");
            c = scanner.nextInt();
            scanner.nextLine();
        }
        int count = 0;
        Random random = new Random();
        while (count < c) {
            int i = random.nextInt(k) + 1;
            int j = random.nextInt(k) + 1;
            if (square[i][j] == ' ') {
                square[i][j] = '.';
                count++;
            }
        }
        int xDot=k/2;
        int yDot=k/2;
        int score=0;
        square[xDot][yDot]='X';
        print(square);

        long start = System.currentTimeMillis();

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
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    System.out.println("EXIT");
                    System.out.println("Score is:"+ score +"\nTimer is:"+ timeElapsed);
                    return;
                default:
                    System.out.println("WARNING");
                    break;
            }
            if (square[newX][newY]=='*')
            {
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("Hitting the game wall");
                System.out.println("Score is:"+ score +"\nTimer is:"+ timeElapsed);
                return;
            }
            else if (square[newX][newY]=='.')
            {
                score++;
                c--;
            }

            square[xDot][yDot]=' ';
            xDot=newX;
            yDot=newY;
            square[xDot][yDot]='X';

            if (c==0)
            {
                System.out.println("You won!");
                long finish = System.currentTimeMillis();
                long timeElapsed = finish - start;
                System.out.println("Final score is:+"+ score +"\nTimer is:"+ timeElapsed);
                return;
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

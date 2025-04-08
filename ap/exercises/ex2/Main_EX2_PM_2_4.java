package ap.exercises.ex2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main_EX2_PM_2_4 {

    public static class PacmanEngine
    {
        Random random=new Random();
        char[][] square;
        int k,c,xDot,yDot,score,count=0;
        long start;

        public PacmanEngine(int k,int c)
        {
            this.k=k;
            this.c=c;
            square=new char[k+2][k+2];
            int i=0,j=0;

            for (i = 0; i < k + 2; i++)
            {
                for (j = 0; j < k + 2; j++)
                {
                    if (i == 0 || i == k + 1 || j == 0 || j == k + 1) square[i][j] = '*';
                    else square[i][j] = ' ';
                }
            }
            while (count<c) {
                i = random.nextInt(k) + 1;
                j = random.nextInt(k) + 1;
                if (square[i][j] == ' ') {
                    square[i][j] = '.';
                    count++;
                }
            }
            xDot=k/2;
            yDot=k/2;
            start=System.currentTimeMillis();
            this.score=0;
            square[xDot][yDot]='X';
        }
        public void printMatrix()
        {
            for (int i=0;i<k+2;i++)
            {
                for (int j=0;j<k+2;j++)
                {
                    System.out.print(square[i][j]);
                }
                System.out.println();
            }
        }
        public void printScore()
        {
            System.out.println("Score is:"+ score);
        }
        public void printRemainTime()
        {
            long finish=System.currentTimeMillis();
            long timeElapsed=finish-start;
            System.out.println("Time is:"+ timeElapsed);
        }
        public void move(int direction)
        {
            int newX=xDot;
            int newY=yDot;

            switch (direction)
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
                default:
                    return;
            }
            System.out.println("Direction is:"+ direction);
            if (square[newX][newY]=='*')
            {
                System.out.println("Hitting the game wall");
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

        }
        public void save()
        {
            String filepath="pacman.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath)))
            {
                writer.write(xDot + " " + yDot + " " + score + " " + k + " " + c + " " + start + "\n");
                for (int i=0;i< square.length;i++)
                {
                    for (int j = 0; j < square[i].length; j++) {
                        writer.write(square[i][j]);
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error to saving file." + e.getMessage());
            }
        }
    }
}

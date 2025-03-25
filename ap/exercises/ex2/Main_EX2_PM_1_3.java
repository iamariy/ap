package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_3 {
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

        System.out.println("enter c");
        int c=scanner.nextInt();
        while (c>k*k)
        {
            System.out.println("invalid number,please enter again c:");
            c=scanner.nextInt();
        }
        int count=0;
        Random random=new Random();
        while (count<c)
        {
            int i=random.nextInt(k)+1;
            int j=random.nextInt(k)+1;
            if (square[i][j]==' ')
            {
                square[i][j]='.';
                count++;
            }
        }

        for (int i=0;i<k+2;i++)
        {
            for (int j=0;j<k+2;j++)
            {
                System.out.print(square[i][j]);
            }
            System.out.println();
        }
    }
}

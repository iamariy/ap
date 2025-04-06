package ap.exercises.ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main_EX3_LM_1_2_A {
    public static void main(String[] args) {
        String str1=CreatingFileB();
        String str2=CreatingFileS();

        Scanner scanner=new Scanner(System.in);
        int n=4;
        String[] arr1=new String[n];

        int m=3;
        String[] arr2=new String[m];

        for (int i=0;i<n;i++)
        {
            System.out.println("enter arr1 "+ (i+1) +":");
            arr1[i]=scanner.nextLine();
        }
        WriteToFileB(arr1,str1);
        for (int j=0;j<m;j++)
        {
            System.out.println("enter arr2 "+ (j+1) +":");
            arr2[j]=scanner.nextLine();
        }
        WriteToFileS(arr2,str2);
    }

    public static String CreatingFileB()
    {
        String filepath = "Book.txt";
        File file=new File(filepath);

        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
                System.out.println("Created the fileB.");
            } catch (IOException e) {
                System.out.println("Error to creating file!");
                e.printStackTrace();
            }
        } else System.out.println("Created the file before.");
        return filepath;
    }
    public static void WriteToFileB(String[] arr1, String filepath1)
    {
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filepath1)))
        {
            for (int i=0;i< arr1.length;i++)
            {
                writer.write(arr1[i]);
                writer.newLine();
            }
            System.out.println("Book saved successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error to saving file.");
            e.printStackTrace();
        }
    }

    public static String CreatingFileS()
    {
        String filepath = "Student.txt";
        File file=new File(filepath);

        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
                System.out.println("Created the file.");
            } catch (IOException e) {
                System.out.println("Error to creating file!");
                e.printStackTrace();
            }
        } else System.out.println("Created the file before.");
        return filepath;
    }
    public static void WriteToFileS( String[] arr2,String filepath2 )
    {
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filepath2)))
        {
            for (int j=0;j< arr2.length;j++)
            {
                writer.write(arr2[j]);
                writer.newLine();
            }
            System.out.println("Student saved successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error to saving file.");
            e.printStackTrace();
        }
    }
}


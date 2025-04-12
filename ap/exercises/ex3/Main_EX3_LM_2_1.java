package ap.exercises.ex3;

import java.io.*;
import java.util.ArrayList;

public class Main_EX3_LM_2_1 {
    public static void main(String[] args)
    {
        String str=CreatingFile();

        Library[] libraries=new  Library[2];
        libraries[0]=new Library("Sama Zolfkhani",245636,"Math1","1404/1/12","1404/2/20");
        libraries[1]=new Library("Neda Rashtchi",467432,"Physics2","1404/1/18","1404/2/26");

        WriteToFile(libraries,str);

        ArrayList<Library> loadedlibraries=ReadToFile(libraries,str);
        System.out.println("Information:");
        for(int i=0;i<libraries.length;i++)
        {
            System.out.println(libraries[i]);
        }
    }

    public static String CreatingFile()
    {
        String filepath = "Library.txt";
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

    public static void WriteToFile(Library[] libraries, String filepath)
    {
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filepath)))
        {
            for (int i=0;i< libraries.length;i++)
            {
                writer.write(libraries[i].toString());
                writer.newLine();
            }
            System.out.println("Library information saved successfully!");
        }
        catch (IOException e)
        {
            System.out.println("Error to saving file.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Library> ReadToFile(Library[] libraries, String filepath)
    {
        ArrayList<Library> loadedList=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(filepath)))
        {
            String line;
            while ((line= reader.readLine())!=null)
            {
                String[] str1=line.split(",");
                if (str1.length==5)
                {
                    String studentName=str1[0];
                    int id=Integer.parseInt(str1[1]);
                    String bookName=str1[2];
                    String date1=str1[3];
                    String date2=str1[4];
                    Library lib=new Library(studentName,id,bookName,date1,date2);
                    loadedList.add(lib);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error to reading file!"+ e.getMessage());
            e.printStackTrace();
        }
        return loadedList;
    }

    static class Library
    {
        String studentName;
        int id;
        String bookName;
        String date1;
        String date2;

        public Library(String studentName,int id,String bookName,String date1,String date2)
        {
            this.studentName=studentName;
            this.id=id;
            this.bookName=bookName;
            this.date1=date1;
            this.date2=date2;
        }
        public String toString()
        {
            return studentName +","+ id +","+ bookName +","+ date1 +","+ date2;
        }
    }
}

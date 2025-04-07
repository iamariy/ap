package ap.exercises.ex3;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main_EX3_LM_1_2_A {
    public static void  main(String[] args) {

        Book[] books = new Book[4];
        books[0] = new Book("Cosette", "Victor Hugo", 582, 1862);
        books[1] = new Book("Anna Shirley", "Lucy Maund Montgomery", 3544, 1908);
        books[2] = new Book("Le Petir Prince", "Antoine de Saint-Exupery", 103, 1944);
        books[3] = new Book("The Forty Rules of Love", "Elif Safak", 511, 2010);

        Main_EX3_LM1_2_C.Student[] students= new Main_EX3_LM1_2_C.Student[3];
        students[0]= new Main_EX3_LM1_2_C.Student("Maryam","Rezaei","Computer",763435);
        students[1]= new Main_EX3_LM1_2_C.Student("Sama","Zolfkhani","Computer",478432);
        students[2]= new Main_EX3_LM1_2_C.Student("Neda","Rashtchi","Computer",964532);



        String filepath1 = "Book.txt";
        File file1 = new File(filepath1);

        if (!file1.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath1))) {
                System.out.println("Created the fileB.");
            } catch (IOException e) {
                System.out.println("Error to creating file!");
                e.printStackTrace();
            }
        } else System.out.println("Created the file before.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath1))) {
            for (int i = 0; i < books.length; i++) {
                writer.write(books[i].toString());
                writer.newLine();
            }
            System.out.println("Book saved successfully!");
        } catch (IOException e) {
            System.out.println("Error to saving file.");
            e.printStackTrace();
        }

        String filepath2 = "Student.txt";
        File file2 = new File(filepath2);

        if (!file2.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath2))) {
                System.out.println("Created the file.");
            } catch (IOException e) {
                System.out.println("Error to creating file!");
                e.printStackTrace();
            }
        } else System.out.println("Created the file before.");

        try (BufferedWriter writer=new BufferedWriter(new FileWriter(filepath2)))
        {
            for (int j=0;j< students.length;j++)
            {
                writer.write(students[j].toString());
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

    static class Book
    {
        String bookname;
        String authornam;
        int counter;
        int year;

        public Book(String bookname,String authornam,int counter,int year)
        {
            this.bookname=bookname;
            this.authornam=authornam;
            this.counter=counter;
            this.year=year;
        }

        public String toString()
        {
            return bookname +","+ authornam +","+ counter +","+ year;
        }

        public void setBookName(String bookName)
        {
            this.bookname = bookName;
        }
        public void setAuthorName(String authorName)
        {
            this.authornam = authorName;
        }
        public void setCounter(int counter)
        {
            this.counter = counter;
        }
        public void setYear(int year)
        {
            this.year = year;
        }
    }
    class Student {
        String firstname;
        String lastname;
        String major;
        int id;

        public Student(String firstname, String lastname, String major, int id) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.major = major;
            this.id = id;
        }

        public String toString()
        {
            return firstname +","+ lastname +","+ major +","+ id;
        }

        public void setFirstName(String firstName) {
            this.firstname = firstName;
        }

        public void setLastName(String lastName) {
            this.lastname = lastName;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setMajor(String major) {
            this.major = major;
        }
    }
}


package ap.exercises.project;

import java.io.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    String filepath1="student.txt";
    String filepath2="librarian.txt";

    public void saveStudents(List<Student> students){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath1))){
            for (Student student : students){
                writer.println(student.getName() +","+ student.getStudentId() +","+ student.getUsername() +","+ student.getPassword());
            }
        } catch (IOException e){
            System.out.println("Error to save students"+ e.getMessage());
        }
    }

    public void loadStudents(List<Student> students){
        File file=new File(filepath1);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==4){
                    students.add(new Student(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim()));
                }
            }
        } catch (IOException e){
            System.out.println("Error to load students"+ e.getMessage());
        }
    }

    public void saveLibrarian(List<Librarian> librarians){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath2))){
            for (Librarian librarian : librarians){
                writer.println(librarian.getUsrename() +","+ librarian.getPassword());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadLibrarian(List<Librarian> librarians){
        File file=new File(filepath2);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==2){
                    librarians.add(new Librarian(parts[0].trim(),parts[1].trim()));
                }
            }
        } catch (IOException e){
            System.out.println("Error to load librarians"+ e.getMessage());
        }
    }
}

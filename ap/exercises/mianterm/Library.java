package ap.exercises.mianterm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Library {
    String libraryName="Znu Library";

    ArrayList<Librarian> librarians=new ArrayList<>();
    public  void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Lbrarians are added");
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    ArrayList<Book> books=new ArrayList<>();
    public void addBook(Book book){
        books.add(book);
        System.out.println("Books are added");
    }

    public ArrayList<Book> searchByName(String name) {
        ArrayList<Book> result=new ArrayList<>();
        for (Book book : books){
            if (book.getName().toLowerCase().contains(name.toLowerCase()));
            result.add(book);
        }
        return result;
    }

    public ArrayList<Book> searchByAuthor(String author){
        ArrayList<Book> result=new ArrayList<>();
        for (Book book : books){
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase()));
            result.add(book);
        }
        return result;
    }

    ArrayList<Student> students=new ArrayList<>();
    public boolean registerStudent(String firstname, String lastname, String major, String password,int id){
        for (Student s : students){
            if (Objects.equals(s.getId(), id)){
                System.out.println("Student is registered before!");
                return false;
            }
        }

        Student student=new Student(firstname,lastname,major,password,id);
        students.add(student);
        System.out.println("Student registered successfully!");
        return true;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
}

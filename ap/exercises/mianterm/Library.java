package ap.exercises.mianterm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private LibraryData libraryData;
    private Trust trust;
    private Library library;
    private LibrarySystem librarySystem;

    String libraryName="Znu Library";

    ArrayList<Librarian> librarians;
    ArrayList<Student> students;
    ArrayList<Book> books;
    ArrayList<Trust> borrowRequests;
    ArrayList<Trust> returnRequest;
    ArrayList<Trust> requesst;
    ArrayList<Trust> returnBook;


    public Library() {
        this.libraryData = new LibraryData();
        this.books = libraryData.loadBooks();
        this.books=new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.students = new ArrayList<>();
        this.borrowRequests=new ArrayList<>();
        this.returnRequest=new ArrayList<>();
        this.requesst=new ArrayList<>();
        this.returnBook=new ArrayList<>();
    }



    public void addLibrarian(Librarian librarian) {
        if (librarians == null) {
            librarians = new ArrayList<>();
        }
        librarians.add(librarian);
        System.out.println("Librarian added successfully");
        libraryData.saveLibrarians(librarians); // ذخیره مستقیم
    }

    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }
    public ArrayList<Book> getBooks(){
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        libraryData.saveBooks(books);
    }

    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
        libraryData.saveLibrarians(librarians);
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
        libraryData.saveStudents(students);
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println("Books are added");
    }

    public ArrayList<Book> searchByName(String name) {
        ArrayList<Book> result=new ArrayList<>();
        for (Book book : books){
            if (book.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchByAuthor(String author){
        ArrayList<Book> result=new ArrayList<>();
        for (Book book : books){
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean registerStudent(String firstname, String lastname, String major, String password,LocalDate date,int id){
        for (Student s : students){
            if (Objects.equals(s.getId(), id)){
                System.out.println("Student is registered before!");
                return false;
            }
        }

        Student student=new Student(firstname,lastname,major,password, id, date);
        students.add(student);
        System.out.println("Student registered successfully!");
        return true;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }

}

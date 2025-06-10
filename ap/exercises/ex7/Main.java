package ap.exercises.ex7;


import ap.exercises.mianterm.Book;
import ap.exercises.mianterm.Librarian;
import ap.exercises.mianterm.Student;
import ap.exercises.mianterm.Trust;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String storageType = ConfigReader.getStorageType();
            Storage storage;

            switch (storageType) {
                case "json":
                    storage = new JsonStorage();
                    break;
                case "sqlite":
                    storage = new SqliteStorage();
                    break;
                case "tabsplit":
                default:
                    storage = new TabSplitStorage();
                    break;
            }

            Book newBook = new Book("Math", "Tomas", 1400, 200);

            List<Book> books = new ArrayList<>();
            books.add(newBook);
            storage.saveBooks(books);

            Student student = new Student("Yekta", "Saedian", "Computer Science", "1", 1, LocalDate.now());
            List<Student> students = new ArrayList<>();
            students.add(student);
            storage.saveStudents(students);

            Librarian librarian = new Librarian("Neda", "Rashtchi", "2", 2);
            List<Librarian> librarians = new ArrayList<>();
            librarians.add(librarian);
            storage.saveLibrarians(librarians);

            Trust trust = new Trust(newBook, student, librarian, LocalDate.now(), LocalDate.now().plusDays(14), null, 0, true);
            List<Trust> trusts = new ArrayList<>();
            trusts.add(trust);
            storage.saveTrusts(trusts);

            System.out.println("Books: " + storage.loadBooks());
            System.out.println("Students: " + storage.loadStudents());
            System.out.println("Librarians: " + storage.loadLibrarians());
            System.out.println("Trusts: " + storage.loadTrusts());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

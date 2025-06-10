package ap.exercises.ex7;

import ap.exercises.mianterm.Trust;
import ap.exercises.mianterm.Book;
import ap.exercises.mianterm.Librarian;
import ap.exercises.mianterm.Student;
import java.util.List;

public interface Storage {
    void saveBooks(List<Book> books);
    List<Book> loadBooks();
    void saveStudents(List<Student> students);
    List<Student> loadStudents();
    void saveLibrarians(List<Librarian> librarians);
    List<Librarian> loadLibrarians();
    void saveTrusts(List<Trust> trusts);
    List<Trust> loadTrusts();
}

package ap.exercises.project;

import java.util.List;

/**
 * Storage contract for the library system. Implementations:
 * {@link FileDataManager} (plain text files) and {@link SqliteDataManager}.
 */
public interface DataManager {

    void saveStudents(List<Student> students);

    void loadStudents(List<Student> students);

    void saveLibrarian(List<Librarian> librarians);

    void loadLibrarian(List<Librarian> librarians);

    void saveBooks(List<Book> books);

    void loadBooks(List<Book> books);

    void saveRequest(List<BorrowRequest> requests);

    void saveRequestAll(List<BorrowRequest> requests);

    void loadRequest(List<BorrowRequest> requests);

    void loadRequestAll(List<BorrowRequest> requests);

    void saveAccept(List<AcceptBorrow> accepts);

    void saveAcceptAll(List<AcceptBorrow> accepts);

    void loadAccept(List<AcceptBorrow> accepts);

    void loadAcceptAll(List<AcceptBorrow> accepts);

    void saveReturn(List<ReturnBook> returnBooks);

    void loadReturn(List<ReturnBook> returnBooks);

    void saveReturnAccept(List<AcceptReturn> acceptReturns);

    void loadReturnAccept(List<AcceptReturn> acceptReturns);
}

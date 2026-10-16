package ap.exercises.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    private static final String STUDENTS_FILE = "student.txt";
    private static final String LIBRARIANS_FILE = "librarian.txt";
    private static final String BOOKS_FILE = "book.txt";
    private static final String REQUESTS_FILE = "Requests.txt";
    private static final String ACCEPTS_FILE = "Accepts.txt";
    private static final String ALL_REQUESTS_FILE = "All Requests.txt";
    private static final String ALL_ACCEPTS_FILE = "All Accepts.txt";
    private static final String RETURNS_FILE = "Return Request.txt";
    private static final String ACCEPT_RETURNS_FILE = "Accept return.txt";

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // ---- students ----

    public void saveStudents(List<Student> students) {
        List<String> lines = new ArrayList<>();
        for (Student student : students) {
            lines.add(student.getName() + "," + student.getStudentId() + "," + student.getUsername()
                    + "," + student.getPassword() + "," + student.isActive());
        }
        writeLines(STUDENTS_FILE, lines);
    }

    public void loadStudents(List<Student> students) {
        students.clear();
        for (String line : readLines(STUDENTS_FILE)) {
            try {
                String[] p = line.split(",");
                students.add(new Student(p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(),
                        Boolean.parseBoolean(p[4].trim())));
            } catch (RuntimeException e) {
                skipLine(STUDENTS_FILE);
            }
        }
    }

    // ---- librarians ----

    public void saveLibrarian(List<Librarian> librarians) {
        List<String> lines = new ArrayList<>();
        for (Librarian librarian : librarians) {
            lines.add(librarian.getUsername() + "," + librarian.getPassword());
        }
        writeLines(LIBRARIANS_FILE, lines);
    }

    public void loadLibrarian(List<Librarian> librarians) {
        librarians.clear();
        for (String line : readLines(LIBRARIANS_FILE)) {
            try {
                String[] p = line.split(",");
                librarians.add(new Librarian(p[0].trim(), p[1].trim()));
            } catch (RuntimeException e) {
                skipLine(LIBRARIANS_FILE);
            }
        }
    }

    // ---- books ----

    public void saveBooks(List<Book> books) {
        List<String> lines = new ArrayList<>();
        for (Book book : books) {
            lines.add(book.getName() + "," + book.getAuthor() + "," + book.getYear() + ","
                    + book.getPagecounter() + "," + book.getCount() + ","
                    + book.getLibrarian().getUsername());
        }
        writeLines(BOOKS_FILE, lines);
    }

    public void loadBooks(List<Book> books) {
        books.clear();
        for (String line : readLines(BOOKS_FILE)) {
            try {
                String[] p = line.split(",");
                books.add(new Book(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()),
                        Integer.parseInt(p[3].trim()), Integer.parseInt(p[4].trim()),
                        new Librarian(p[5].trim())));
            } catch (RuntimeException e) {
                skipLine(BOOKS_FILE);
            }
        }
    }

    // ---- borrow requests ----

    public void saveRequest(List<BorrowRequest> requests) {
        saveRequests(requests, REQUESTS_FILE);
    }

    public void saveRequestAll(List<BorrowRequest> requests) {
        saveRequests(requests, ALL_REQUESTS_FILE);
    }

    private void saveRequests(List<BorrowRequest> requests, String filepath) {
        List<String> lines = new ArrayList<>();
        for (BorrowRequest request : requests) {
            lines.add(toLine(request));
        }
        writeLines(filepath, lines);
    }

    public void loadRequest(List<BorrowRequest> requests) {
        loadRequests(requests, REQUESTS_FILE);
    }

    public void loadRequestAll(List<BorrowRequest> requests) {
        loadRequests(requests, ALL_REQUESTS_FILE);
    }

    private void loadRequests(List<BorrowRequest> requests, String filepath) {
        requests.clear();
        for (String line : readLines(filepath)) {
            try {
                requests.add(parseRequest(line));
            } catch (RuntimeException e) {
                skipLine(filepath);
            }
        }
    }

    private String toLine(BorrowRequest request) {
        return request.getStudent().getName() + "," + request.getStudent().getStudentId() + ","
                + request.getBook().getName() + "," + request.getBook().getAuthor() + ","
                + request.getStart() + "," + request.getEnd();
    }

    private BorrowRequest parseRequest(String line) {
        String[] p = line.split(",");
        Student student = new Student(p[0].trim(), p[1].trim());
        Book book = new Book(p[2].trim(), p[3].trim());
        return new BorrowRequest(student, book,
                LocalDate.parse(p[4].trim(), DATE_FORMAT),
                LocalDate.parse(p[5].trim(), DATE_FORMAT));
    }

    // ---- accepted borrows ----

    public void saveAccept(List<AcceptBorrow> accepts) {
        saveAccepts(accepts, ACCEPTS_FILE);
    }

    public void saveAcceptAll(List<AcceptBorrow> accepts) {
        saveAccepts(accepts, ALL_ACCEPTS_FILE);
    }

    private void saveAccepts(List<AcceptBorrow> accepts, String filepath) {
        List<String> lines = new ArrayList<>();
        for (AcceptBorrow accept : accepts) {
            lines.add(toLine(accept));
        }
        writeLines(filepath, lines);
    }

    public void loadAccept(List<AcceptBorrow> accepts) {
        loadAccepts(accepts, ACCEPTS_FILE);
    }

    public void loadAcceptAll(List<AcceptBorrow> accepts) {
        loadAccepts(accepts, ALL_ACCEPTS_FILE);
    }

    private void loadAccepts(List<AcceptBorrow> accepts, String filepath) {
        accepts.clear();
        for (String line : readLines(filepath)) {
            try {
                accepts.add(parseAccept(line));
            } catch (RuntimeException e) {
                skipLine(filepath);
            }
        }
    }

    private String toLine(AcceptBorrow accept) {
        BorrowRequest request = accept.getBorrowRequest();
        return request.getStudent().getName() + "," + request.getStudent().getStudentId() + ","
                + request.getBook().getName() + "," + request.getBook().getAuthor() + ","
                + accept.getLibrarian().getUsername() + ","
                + accept.getStartDate() + "," + accept.getEndDate();
    }

    private AcceptBorrow parseAccept(String line) {
        String[] p = line.split(",");
        BorrowRequest request = new BorrowRequest(new Student(p[0].trim(), p[1].trim()),
                new Book(p[2].trim(), p[3].trim()));
        Librarian librarian = new Librarian(p[4].trim());
        return new AcceptBorrow(request, librarian,
                LocalDate.parse(p[5].trim(), DATE_FORMAT),
                LocalDate.parse(p[6].trim(), DATE_FORMAT));
    }

    // ---- return requests ----

    public void saveReturn(List<ReturnBook> returnBooks) {
        List<String> lines = new ArrayList<>();
        for (ReturnBook returnBook : returnBooks) {
            lines.add(returnBook.getStudent().getName() + "," + returnBook.getStudent().getStudentId() + ","
                    + returnBook.getAcceptBorrow().getBorrowRequest().getBook().getName() + ","
                    + returnBook.getAcceptBorrow().getBorrowRequest().getBook().getAuthor() + ","
                    + returnBook.getReturnDate());
        }
        writeLines(RETURNS_FILE, lines);
    }

    public void loadReturn(List<ReturnBook> returnBooks) {
        returnBooks.clear();
        for (String line : readLines(RETURNS_FILE)) {
            try {
                returnBooks.add(parseReturn(line));
            } catch (RuntimeException e) {
                skipLine(RETURNS_FILE);
            }
        }
    }

    private ReturnBook parseReturn(String line) {
        String[] p = line.split(",");
        Student student = new Student(p[0].trim(), p[1].trim());
        Book book = new Book(p[2].trim(), p[3].trim());
        return new ReturnBook(student, new AcceptBorrow(new BorrowRequest(book)),
                LocalDate.parse(p[4].trim(), DATE_FORMAT));
    }

    // ---- accepted returns ----

    public void saveReturnAccept(List<AcceptReturn> acceptReturns) {
        List<String> lines = new ArrayList<>();
        for (AcceptReturn acceptReturn : acceptReturns) {
            lines.add(toLine(acceptReturn));
        }
        writeLines(ACCEPT_RETURNS_FILE, lines);
    }

    public void loadReturnAccept(List<AcceptReturn> acceptReturns) {
        acceptReturns.clear();
        for (String line : readLines(ACCEPT_RETURNS_FILE)) {
            try {
                acceptReturns.add(parseReturnAccept(line));
            } catch (RuntimeException e) {
                skipLine(ACCEPT_RETURNS_FILE);
            }
        }
    }

    private String toLine(AcceptReturn acceptReturn) {
        ReturnBook returnBook = acceptReturn.getReturnBook();
        return returnBook.getStudent().getName() + "," + returnBook.getStudent().getStudentId() + ","
                + returnBook.getAcceptBorrow().getBorrowRequest().getBook().getName() + ","
                + returnBook.getAcceptBorrow().getBorrowRequest().getBook().getAuthor() + ","
                + acceptReturn.getLibrarian().getUsername() + "," + acceptReturn.getReturned();
    }

    private AcceptReturn parseReturnAccept(String line) {
        String[] p = line.split(",");
        Student student = new Student(p[0].trim(), p[1].trim());
        Book book = new Book(p[2].trim(), p[3].trim());
        ReturnBook returnBook = new ReturnBook(student, new AcceptBorrow(new BorrowRequest(book)));
        Librarian librarian = new Librarian(p[4].trim());
        return new AcceptReturn(returnBook, librarian, LocalDate.parse(p[5].trim(), DATE_FORMAT));
    }

    // ---- low-level helpers ----

    private List<String> readLines(String filepath) {
        File file = new File(filepath);
        List<String> lines = new ArrayList<>();
        if (!file.exists()) {
            return lines; // first run: no data saved yet
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + filepath + ": " + e.getMessage());
        }
        return lines;
    }

    private void writeLines(String filepath, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error writing " + filepath + ": " + e.getMessage());
        }
    }

    private void skipLine(String filepath) {
        System.out.println("Skipping corrupted line in " + filepath);
    }
}

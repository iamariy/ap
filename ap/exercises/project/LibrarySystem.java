package ap.exercises.project;

import java.time.LocalDate;
import java.util.Scanner;

public class LibrarySystem implements Connection, HandleData, Getter {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private LibrarianManager librarianManager;
    private BookManager bookManager;
    private Scanner scanner;

    public LibrarySystem() {
        this.scanner = new Scanner(System.in);
        this.studentManager = new StudentManager();
        this.librarianManager = new LibrarianManager();
        this.bookManager = new BookManager(scanner);
        this.menuHandler = new MenuHandler(this, scanner);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    @Override
    public int getBookCount() {
        return this.bookManager.getBookCount();
    }

    @Override
    public int getLoanCount() {
        return this.bookManager.getLoanCount();
    }

    @Override
    public int activeLoans() {
        return this.bookManager.activeLoans();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        while (true) {
            System.out.println("\n=== Edit Information ===");
            System.out.println("1. Edit username");
            System.out.println("2. Edit password");
            System.out.println("3. Exit");

            int choice = getIntInput(1, 3);

            switch (choice) {
                case 1:
                    student.setUsername(InputHelper.readField(scanner, "New username: "));
                    break;
                case 2:
                    student.setPassword(InputHelper.readField(scanner, "New password: "));
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    public void borrowBook(Student student) {
        bookManager.borrowBook(student, LocalDate.now());
    }

    public void returnBook(Student student) {
        bookManager.returnBook(student, LocalDate.now());
    }

    public void displayAvailableBooks() {
        String name = InputHelper.readField(scanner, "Enter book name, author or year: ");
        bookManager.isAvailable(name);
    }

    public void start() {
        menuHandler.menu();
    }

    public void saveStudents() {
        studentManager.saving();
    }

    public void loadStudents() {
        studentManager.loading();
    }

    public void addLibrarian(String username, String password) {
        librarianManager.addLibrarian(username, password);
    }

    public void saveLibrarians() {
        librarianManager.saving();
    }

    public void loadLibrarians() {
        librarianManager.loading();
    }

    public Librarian authenticateLibrarian(String username, String password) {
        return librarianManager.authenticateLibrarian(username, password);
    }

    public void editLibrarianPassword(Librarian librarian) {
        librarian.setPassword(InputHelper.readField(scanner, "Enter new password: "));
        System.out.println("Password changed successfully.");
    }

    @Override
    public void addBook(String name, String author, int year, int pagecounter, int count, Librarian librarian) {
        bookManager.addBook(name, author, year, pagecounter, count, librarian);
    }

    @Override
    public void saveBooks() {
        bookManager.saveBooks();
    }

    @Override
    public void loadBooks() {
        bookManager.loadBooks();
    }

    @Override
    public void searchBook(String name) {
        bookManager.searchBook(name);
    }

    public void editBooks(String name) {
        while (true) {
            System.out.println("\n=== Edit book: " + name + " ===");
            System.out.println("1. Edit book name");
            System.out.println("2. Edit author");
            System.out.println("3. Edit year");
            System.out.println("4. Edit page count");
            System.out.println("5. Edit count");
            System.out.println("6. Exit");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    bookManager.editName(name, InputHelper.readField(scanner, "New name: "));
                    break;
                case 2:
                    bookManager.editAuthor(name, InputHelper.readField(scanner, "New author: "));
                    break;
                case 3:
                    bookManager.editYear(name, InputHelper.readInt(scanner, "New year: "));
                    break;
                case 4:
                    bookManager.editPage(name, InputHelper.readInt(scanner, "New page count: "));
                    break;
                case 5:
                    bookManager.editCount(name, InputHelper.readInt(scanner, "New count: "));
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    public void activing() {
        String studentId = InputHelper.readField(scanner, "Enter student ID: ");
        studentManager.changeActiving(studentId, scanner);
    }

    public void accept(Librarian librarian) {
        bookManager.accept(librarian, LocalDate.now());
    }

    public void acceptReturn(Librarian librarian) {
        bookManager.acceptReturn(librarian, LocalDate.now());
    }

    @Override
    public void print() {
        bookManager.print();
    }

    @Override
    public int delay() {
        return bookManager.delay();
    }

    @Override
    public void loadRequest() {
        bookManager.loadRequest();
    }

    @Override
    public void loadAccept() {
        bookManager.loadAccept();
    }

    @Override
    public void loadAllR() {
        bookManager.loadAllR();
    }

    @Override
    public void loadAllA() {
        bookManager.loadAllA();
    }

    @Override
    public void loadReturn() {
        bookManager.loadReturn();
    }

    @Override
    public void loadAcceptReturn() {
        bookManager.loadAcceptReturn();
    }

    @Override
    public void librarianHistory(String username) {
        bookManager.librarianHistory(username);
    }

    @Override
    public int requestCounter() {
        return this.bookManager.requestCounter();
    }

    @Override
    public long daysCounter() {
        return bookManager.daysCounter();
    }

    @Override
    public void delayStudents() {
        bookManager.delayStudents();
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}

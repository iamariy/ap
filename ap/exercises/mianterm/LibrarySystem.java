package ap.exercises.mianterm;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class LibrarySystem {
    private Student student;
    private boolean logS = false;
    private Librarian librarian;
    private boolean logL = false;
    private Manager manager;
    private boolean logM = false;
    private Menu menu;
    private Library library;
    private Trust trust;
    private Input input;
    private LibraryData libraryData;

    protected List<Trust> request = new ArrayList<>();
    protected List<Trust> borrowRequests = new ArrayList<>();
    protected List<Trust> returnRequest = new ArrayList<>();
    private ArrayList<Trust> returnBook = new ArrayList<>();

    Random random = new Random();

    public void setMenu(Menu menu) {
        this.menu = Objects.requireNonNull(menu, "Menu cannot be null");
    }

    public void setManager(Manager manager) {
        this.manager = Objects.requireNonNull(manager, "Manager cannot be null");
        if (library != null) {
            library.setManager(manager);
        }
    }

    public Manager getManager() {
        return this.manager;
    }

    public Librarian getLibrarian() {
        return this.librarian;
    }


    public void setLibrarian(Librarian librarian) {
        this.librarian = Objects.requireNonNull(librarian, "Librarian cannot be null");
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = Objects.requireNonNull(student, "Student cannot be null");
    }

    public void setLibrary(Library library) {
        this.library = Objects.requireNonNull(library, "Library cannot be null");
    }

    public void setTrust(Trust trust) {
        this.trust = Objects.requireNonNull(trust, "Trust cannot be null");
    }

    public boolean loginStudent(int id, String password) {
        for (Student student : library.getStudents()) {
            if (student.logIn(id, password)) {
                this.student = student;
                logS = true;
                System.out.println("Student login successful.");
                menu.setCurrentStudent(student);
                menu.setStudent(student);
                menu.studentMenu();
                return true;
            } else {
                System.out.println("The password or passcode is incorrect.");
                return false;
            }
        }
        System.out.println("No student found with ID: " + id);
        return false;
    }

    public void logoutStudent() {
        logS = false;
        System.out.println("The exit was successful.");
    }

    public boolean loginLibrarian(int id, String password) {
        for (Librarian lib : library.getLibrarians()) {
            if (lib.getId() == id) {
                if (Objects.equals(lib.getPassword(), password)) {
                    this.librarian = lib;
                    logL = true;
                    System.out.println("Librarian login successful. Welcome, " + lib.getFirstname());
                    menu.setLoggedInLibrarian(lib);
                    menu.librarianMenu();
                    return true;
                } else {
                    System.out.println("Incorrect password for ID: " + id);
                    return false;
                }
            }
        }
        System.out.println("No librarian found with ID: " + id);
        return false;
    }


    public void logoutLibrarian() {
        logL = false;
        System.out.println("The exit was successful.");
    }

    public boolean loginManager(int id, String password) {
        Manager manager = this.getManager();
        if (manager == null) {
            System.out.println("Manager not initialized!");
            return false;
        }
        if (manager != null && manager.getId() == id &&
                Objects.equals(manager.getPassword(), password)) {
            this.manager = manager;
            logM = true;
            System.out.println("Manager login successful.");
            menu.managerMenu();
            return true;
        }
        System.out.println("The password or passcode is incorrect.");
        return false;
    }

    public void logoutManager() {
        logM = false;
        System.out.println("The exit was successful.");
    }


    public void requestBorrow(String bookname) {
        ArrayList<Book> found = library.searchByName(bookname);
        if (found.isEmpty()) {
            System.out.println("Book is not found");
            return;
        }
        for (Book book : found) {
            if (!book.isBorrow()) {
                Trust trust1 = new Trust(book, student);
                request.add(trust1);
                returnBook.add(trust1);
                libraryData.saveTrust(returnBook);
                System.out.println("Request send to librarian");
                return;
            }
        }
        System.out.println("Book is already borrowed");
    }

    public void acceptBorrow() {
        if (request.isEmpty()) {
            System.out.println("No request");
            return;
        }
        for (Trust trust1 : request) {
            Book book = trust1.getBook();
            Student student = trust1.getStudent();
            System.out.println("Request by student" + student.getFirstname() + student.getLastname() + "for book" + book.getName());
            System.out.println("y or n?");
            String a = input.inputString();
            if (a.equals("y")) {
                ArrayList<Librarian> librarians = library.getLibrarians();
                Librarian randomLibrarian = librarians.get(random.nextInt(librarians.size()));
                LocalDate borrowDate = LocalDate.now();
                LocalDate returnDate = borrowDate.plusDays(20);
                Trust trust2 = new Trust(book, student, randomLibrarian, borrowDate, returnDate, true);
                request.remove(trust1);
                borrowRequests.add(trust2);
                returnBook.add(trust2);
                libraryData.saveTrust(returnBook);
            }
        }
    }

    public void showBorrowing(Student student) {
        boolean found2 = false;
        for (Trust trust2 : borrowRequests) {
            if (trust2.getStudent().equals(student) && trust2.isBorrowBook()) {
                System.out.println("Book:" + trust2.getBook().getName());
                found2 = true;
            }
        }
        if (!found2) {
            System.out.println(student.getFirstname() + " " + student.getLastname() + "is not borrowing book");
        }
    }

    public void requestReturn(String bookname, Book book) {
        boolean found = false;
        for (Trust trust2 : borrowRequests) {
            if (trust2.getBook().getName().equals(bookname) && trust2.isBorrowBook()) {
                returnRequest.add(trust2);
                libraryData.saveTrust(returnBook);
                System.out.println("Return request" + book.getName() + "book added successfully");
            }
        }
        System.out.println("You do not borrow" + book.getName());
    }

    public void acceptReturn() {
        if (returnRequest.isEmpty()) {
            System.out.println("No request");
        }
        for (Trust trust2 : returnRequest) {
            Book book = trust2.getBook();
            Student student = trust2.getStudent();
            LocalDate borrowDate = trust2.getBorrowDate();
            LocalDate returnDate = trust2.getReturnDate();
            System.out.println("Request by student:" + trust2.getStudent().getFirstname() + " " + trust2.getStudent().getLastname() + "for book:" + trust2.getBook().getName());
            System.out.println("y or n?");
            String b = input.inputString();
            if (b.equals("y")) {
                ArrayList<Librarian> librarians = library.getLibrarians();
                Librarian randomLibrarian = librarians.get(random.nextInt(librarians.size()));
                LocalDate receiveDate = LocalDate.now();
                long delayDate = ChronoUnit.DAYS.between(receiveDate, returnDate);
                Trust trust3 = new Trust(book, student, randomLibrarian, borrowDate, returnDate, receiveDate, delayDate, false);
                returnBook.remove(trust2);
                returnBook.add(trust3);
                borrowRequests.remove(trust2);
                returnRequest.remove(trust2);
                libraryData.saveTrust(returnBook);
            }

        }
    }
}

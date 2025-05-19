package ap.exercises.mianterm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Menu {
    private Input input;
    private LibrarySystem librarySystem;
    private Student student;
    private Student currentStudent;
    private Library library;
    private Librarian librarian;
    private Librarian currentLibrarian;
    private Book book;
    private Book currentBook;
    private Manager manager;
    private LibraryData libraryData;

    public Menu(Library library, LibraryData libraryData) {
        this.library=library;
        this.libraryData=libraryData;
    }

    public void setInput(Input input) {
        this.input = Objects.requireNonNull(input, "Input cannot be null");
    }

    public void setLibrarySystem(LibrarySystem librarySystem) {
        this.librarySystem = Objects.requireNonNull(librarySystem, "LibrarySystem cannot be null");
    }

    public void setStudent(Student student) {
        this.student = Objects.requireNonNull(student,"Students cannot be null");
    }

    public void setLibrary(Library library) {
        this.library = Objects.requireNonNull(library, "Library cannot be null");
    }

    public void setLoggedInLibrarian(Librarian librarian) {
        this.currentLibrarian = librarian;
    }
    public void setCurrentBook(Book book){
        this.currentBook =book;
    }
    public void setCurrentStudent(Student student){
        this.currentStudent=student;
    }
    public void setManager(Manager manager) {
        this.manager = Objects.requireNonNull(manager, "Manager cannot be null");
    }
    public void setBook(Book book){
        this.book=Objects.requireNonNull(book,"Books cannot be null");
    }
    public void setLibrarian(Librarian librarian ) {
        this.librarian = Objects.requireNonNull(librarian, "Librarian cannot be null");
    }

    public void showMenu() {
            System.out.println("\nMain Menu:");
            System.out.println("1.Register student");
            System.out.println("2. Enter as Student");
            System.out.println("3. Enter as Librarian");
            System.out.println("4. Enter as Manager");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.inputInteger();

            switch (choice) {
                case 1:
                    System.out.print("Enter First Name: ");
                    String firstName = input.inputString();
                    System.out.print("Enter Last Name: ");
                    String lastName = input.inputString();
                    System.out.print("Enter Major: ");
                    String major = input.inputString();
                    System.out.print("Enter Password: ");
                    String password = input.inputString();
                    System.out.print("Enter ID: ");
                    int id = input.inputInteger();
                    LocalDate date=LocalDate.now();
                    library.registerStudent(firstName, lastName, major, password, date, id);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int studentId = input.inputInteger();
                    System.out.print("Enter Password: ");
                    String studentPassword = input.inputString();
                    librarySystem.loginStudent(studentId, studentPassword);
                    break;
                case 3:
                    System.out.print("Enter Librarian ID: ");
                    int librarianId = input.inputInteger();
                    System.out.print("Enter Password: ");
                    String librarianPassword = input.inputString();
                    librarySystem.loginLibrarian(librarianId, librarianPassword);
                    break;
                case 4:
                    System.out.print("Enter Manager ID: ");
                    int managerId = input.inputInteger();
                    System.out.print("Enter Password: ");
                    String managerPassword = input.inputString();
                    librarySystem.loginManager(managerId, managerPassword);
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
        }
    }

    public void studentMenu() {
        if (currentStudent == null) {
            System.out.println("No student logged in!");
            return;
        }
            while (true){
            System.out.println("\nStudent Menu:");
            System.out.println("1.Show book information");
            System.out.println("2. Search Book by Name");
            System.out.println("3. Search Book by Author");
            System.out.println("4.Request to borrow book");
            System.out.println("5.Request to return book");
            System.out.println("6.Show borrowing book");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = input.inputInteger();

            switch (choice) {
                case 1:
                    ArrayList<Book> books=library.getBooks();
                    if (books.isEmpty()){
                        System.out.println("No books found in the library");
                    } else {
                        for (Book book : books){
                            book.print();
                        }
                    }
                case 2:
                    System.out.print("Enter Book Name: ");
                    String bookName = input.inputString();
                    student.searchingBookByName(library, bookName);
                    break;
                case 3:
                    System.out.print("Enter Author Name: ");
                    String authorName = input.inputString();
                    student.searchingBookByAuthor(library, authorName);
                    break;
                case 4:
                    System.out.println("Enter book name");
                    String bookname1=input.inputString();
                    librarySystem.requestBorrow(bookname1);
                    break;
                case 5:
                    System.out.println("Enter book name");
                    String bookname2=input.inputString();
                    librarySystem.requestReturn(bookname2,book);
                    break;
                case 6:
                    librarySystem.showBorrowing(student);
                    break;
                case 7:
                    librarySystem.logoutStudent();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void librarianMenu() {
        if (currentLibrarian == null) {
            System.out.println("No librarian logged in!");
            return;
        }

        while (true){
            System.out.println("\nLibrarian Menu:");
            System.out.println("1. Edit First Name");
            System.out.println("2. Edit Last Name");
            System.out.println("3. Edit Password");
            System.out.println("4.Show librarian information");
            System.out.println("5. Add Book");
            System.out.println("6.Show book information");
            System.out.println("7.Show student information");
            System.out.println("8.Accept borrow request");
            System.out.println("9.Accept return request");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");

            int choice = input.inputInteger();

            switch (choice) {
                case 1:
                    System.out.print("Enter New First Name: ");
                    String newFirstName = input.inputString();
                    currentLibrarian.setFirstname(newFirstName);
                    System.out.println("First name updated successfully.");
                    library.setLibrarians(library.getLibrarians());
                    break;
                case 2:
                    System.out.print("Enter New Last Name: ");
                    String newLastName = input.inputString();
                    currentLibrarian.setLastname(newLastName);
                    System.out.println("Last name updated successfully.");
                    library.setLibrarians(library.getLibrarians());
                    break;
                case 3:
                    System.out.print("Enter New Password: ");
                    String newPassword = input.inputString();
                    currentLibrarian.setPassword(newPassword);
                    System.out.println("Password updated successfully.");
                    library.setLibrarians(library.getLibrarians());
                    break;
                case 4:
                    currentLibrarian.printInfo();
                    break;
                case 5:
                    System.out.print("Enter Book Name: ");
                    String bookName = input.inputString();
                    System.out.print("Enter Author: ");
                    String author = input.inputString();
                    System.out.print("Enter Year: ");
                    int year = input.inputInteger();
                    System.out.print("Enter Page Count: ");
                    int pages = input.inputInteger();
                    Book addedBook = this.currentLibrarian.addBook(library, bookName, author, year, pages);
                    this.currentBook = addedBook;
                    break;
                case 6:
                    ArrayList<Book> books = library.getBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found in the library.");
                    } else {
                        for (Book book : books) {
                            book.print();
                        }
                    }
                    break;
                case 7:
                    ArrayList<Student> students=library.getStudents();
                    if (students.isEmpty()){
                        System.out.println("No students found in the library");
                    } else {
                        for (Student student : students){
                            student.print();
                        }
                    }
                case 8:
                    librarySystem.acceptBorrow();
                    break;
                case 9:
                    librarySystem.acceptReturn();
                    break;
                case 10:
                    librarySystem.logoutLibrarian();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void managerMenu() {
        while (true){
            System.out.println("\nManager Menu:");
            System.out.println("1. Add Librarian");
            System.out.println("2.Show student information");
            System.out.println("3.Show librarian information");
            System.out.println("4.Show book information");
            System.out.println("5.Delay list");
            System.out.println("6.Show librarian borrow and return");
            System.out.println("7.10 most book");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = input.inputInteger();

            switch (choice) {
                case 1:
                    System.out.print("Enter First Name: ");
                    String firstName = input.inputString();
                    System.out.print("Enter Last Name: ");
                    String lastName = input.inputString();
                    System.out.print("Enter Password: ");
                    String password = input.inputString();
                    System.out.print("Enter ID: ");
                    int id = input.inputInteger();

                    manager.addLibrarian(library, firstName, lastName, password, id);
                    break;
                case 2:
                    ArrayList<Student> students=library.getStudents();
                    if (students.isEmpty()){
                        System.out.println("No students found in the library");
                    } else {
                        for (Student student : students){
                            student.print();
                        }
                    }
                    break;
                case 3:
                    ArrayList<Librarian> librarians=library.getLibrarians();
                    if (librarians .isEmpty()){
                        System.out.println("No librarians found in the library");
                    } else {
                        for (Librarian librarian : librarians){
                            librarian.printInfo();
                        }
                    }
                    break;
                case 4:
                    ArrayList<Book> books=library.getBooks();
                    if (books.isEmpty()){
                        System.out.println("No book found in the library");
                    }else {
                        for (Book book : books){
                            book.print();
                        }
                    }
                    break;
                case 5:
                    librarySystem.delay();
                    break;
                case 6:
                    librarySystem.countBorrowBook();
                case 8:
                    librarySystem.logoutManager();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}

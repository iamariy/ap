package ap.exercises.project;

import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;
    private Manager manager;
    private Librarian currentLibrarian;

    public MenuHandler(LibrarySystem librarySystem, Scanner scanner) {
        this.scanner = scanner;
        this.librarySystem = librarySystem;
        this.currentUser = null;
        this.manager = new Manager("manager", "1");
        this.currentLibrarian = null;
    }

    public void menu() {
        while (true) {
            System.out.println("\n=== Choose your role ===");
            System.out.println("1. Enter as student");
            System.out.println("2. Enter as guest user");
            System.out.println("3. Enter as librarian");
            System.out.println("4. Enter as system manager");
            System.out.println("5. Exit");

            int choice = getIntInput(1, 5);

            switch (choice) {
                case 1:
                    displayMainMenu();
                    break;
                case 2:
                    guestMenu();
                    break;
                case 3:
                    logInLibrarian();
                    break;
                case 4:
                    loginManager();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }

    public void displayMainMenu() {

        librarySystem.loadStudents();

        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. View Registered Student Count");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    handleStudentRegistration();
                    librarySystem.saveStudents();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 3:
                    displayStudentCount();
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    return;
            }
            System.out.println("___________________________");
        }
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("\nTotal registered students: " + studentCount);
    }

    private void handleStudentRegistration() {
        System.out.println("\n--- New Student Registration ---");

        String name = InputHelper.readField(scanner, "Student name: ");
        String studentId = InputHelper.readField(scanner, "Student ID: ");
        String username = InputHelper.readField(scanner, "Username: ");
        String password = InputHelper.readField(scanner, "Password: ");

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        String username = InputHelper.readField(scanner, "Username: ");
        String password = InputHelper.readField(scanner, "Password: ");

        currentUser = librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
        librarySystem.loadBooks();
        librarySystem.loadRequest();
        librarySystem.loadAccept();
        librarySystem.loadAllR();
        librarySystem.loadReturn();

        while (currentUser != null) {
            System.out.println("\n=== Student Dashboard ===");
            System.out.println("1. View My Information");
            System.out.println("2. Edit My Information");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Logout");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 6);

            switch (choice) {
                case 1:
                    System.out.println("\n--- My Information ---");
                    System.out.println(currentUser);
                    break;
                case 2:
                    librarySystem.editStudentInformation(currentUser);
                    librarySystem.saveStudents();
                    break;
                case 3:
                    librarySystem.borrowBook(currentUser);
                    break;
                case 4:
                    librarySystem.returnBook(currentUser);
                    break;
                case 5:
                    librarySystem.displayAvailableBooks();
                    break;
                case 6:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
            }
        }
    }

    private void managerMenu() {
        librarySystem.loadLibrarians();
        librarySystem.loadBooks();
        librarySystem.loadAccept();
        librarySystem.loadAllA();
        librarySystem.loadAcceptReturn();
        librarySystem.loadAllR();

        System.out.println("=== Manager Menu ===");

        while (true) {
            System.out.println("\n1. Add librarian");
            System.out.println("2. View librarian performance");
            System.out.println("3. View book loan statistics");
            System.out.println("4. View student loan statistics");
            System.out.println("5. Logout");

            int choice = getIntInput(1, 5);

            switch (choice) {
                case 1:
                    addLibrarian();
                    librarySystem.saveLibrarians();
                    break;
                case 2: {
                    String username = InputHelper.readField(scanner, "Enter librarian username: ");
                    librarySystem.librarianHistory(username);
                    break;
                }
                case 3:
                    System.out.println("All requests: " + librarySystem.requestCounter()
                            + "\nTotal approved loans: " + librarySystem.getLoanCount()
                            + "\nTotal loan days: " + librarySystem.daysCounter());
                    break;
                case 4:
                    System.out.println("Student loan history:");
                    librarySystem.print();
                    System.out.println("Total loans: " + librarySystem.getLoanCount()
                            + "\nActive loans: " + librarySystem.activeLoans()
                            + "\nLate returns: " + librarySystem.delay());
                    librarySystem.delayStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private void loginManager() {
        System.out.println("=== Manager Login ===");

        String username = InputHelper.readField(scanner, "Username: ");
        String password = InputHelper.readField(scanner, "Password: ");

        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            System.out.println("Welcome!");
            managerMenu();
        } else {
            System.out.println("Invalid username or password. Please try again!");
        }
    }

    private void addLibrarian() {
        System.out.println("=== New Librarian ===");

        String username = InputHelper.readField(scanner, "Username: ");
        String password = InputHelper.readField(scanner, "Password: ");

        librarySystem.addLibrarian(username, password);
    }

    private void librarianMenu() {
        librarySystem.loadBooks();
        librarySystem.loadRequest();
        librarySystem.loadAccept();
        librarySystem.loadLibrarians();
        librarySystem.loadStudents();
        librarySystem.loadAllA();
        librarySystem.loadReturn();
        librarySystem.loadAcceptReturn();

        System.out.println("=== Librarian Menu ===");

        while (true) {
            System.out.println("\n1. Edit password");
            System.out.println("2. Add book");
            System.out.println("3. Search book");
            System.out.println("4. Edit book information");
            System.out.println("5. Review and approve borrow requests");
            System.out.println("6. View student loan history report");
            System.out.println("7. Activate/deactivate a student");
            System.out.println("8. Accept a returned book");
            System.out.println("9. Logout");

            int choice = getIntInput(1, 9);

            switch (choice) {
                case 1:
                    librarySystem.editLibrarianPassword(currentLibrarian);
                    librarySystem.saveLibrarians();
                    break;
                case 2: {
                    System.out.println("=== Add book ===");
                    String name = InputHelper.readField(scanner, "Enter name: ");
                    String author = InputHelper.readField(scanner, "Enter author: ");
                    int year = InputHelper.readInt(scanner, "Enter year: ");
                    int pagecount = InputHelper.readInt(scanner, "Enter page count: ");
                    int count = InputHelper.readInt(scanner, "Enter count: ");
                    librarySystem.addBook(name, author, year, pagecount, count, currentLibrarian);
                    librarySystem.saveBooks();
                    break;
                }
                case 3: {
                    System.out.println("=== Search book ===");
                    String bookName = InputHelper.readField(scanner, "Enter name: ");
                    librarySystem.searchBook(bookName);
                    break;
                }
                case 4: {
                    System.out.println("=== Edit book information ===");
                    String bookName = InputHelper.readField(scanner, "Enter name: ");
                    librarySystem.editBooks(bookName);
                    break;
                }
                case 5:
                    librarySystem.accept(currentLibrarian);
                    break;
                case 6:
                    System.out.println("Student loan history:");
                    librarySystem.print();
                    System.out.println("Total loans: " + librarySystem.getLoanCount()
                            + "\nActive loans: " + librarySystem.activeLoans()
                            + "\nLate returns: " + librarySystem.delay());
                    break;
                case 7:
                    librarySystem.activing();
                    break;
                case 8:
                    librarySystem.acceptReturn(currentLibrarian);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }

    private void logInLibrarian() {
        System.out.println("=== Librarian Login ===");

        librarySystem.loadLibrarians();

        String username = InputHelper.readField(scanner, "Username: ");
        String password = InputHelper.readField(scanner, "Password: ");

        currentLibrarian = librarySystem.authenticateLibrarian(username, password);

        if (currentLibrarian != null) {
            System.out.println("Login successful. Welcome, librarian " + currentLibrarian.getUsername() + "!");
            librarianMenu();
        } else {
            System.out.println("Invalid username or password. Please try again!");
        }
    }

    private void guestMenu() {
        System.out.println("Welcome, guest!");
        librarySystem.loadStudents();
        librarySystem.loadBooks();
        librarySystem.loadAllA();
        librarySystem.loadAccept();

        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("1. Number of registered students");
            System.out.println("2. Search book");
            System.out.println("3. View statistical information");
            System.out.println("4. Exit");

            int choice = getIntInput(1, 4);

            switch (choice) {
                case 1:
                    displayStudentCount();
                    break;
                case 2: {
                    String bookName = InputHelper.readField(scanner, "Enter book name: ");
                    librarySystem.searchBook(bookName);
                    break;
                }
                case 3:
                    displayStudentCount();
                    System.out.println("All books: " + librarySystem.getBookCount()
                            + "\nTotal loans: " + librarySystem.getLoanCount()
                            + "\nActive loans: " + librarySystem.activeLoans());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
            }
        }
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
}

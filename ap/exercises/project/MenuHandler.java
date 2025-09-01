package ap.exercises.project;


// MenuHandler.java
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentUser;
    private Manager manager;
    private Librarian currentUsre2;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
        this.manager=new Manager("manager","1");
        this.currentUsre2=null;
    }

    public void menu(){
        while (true){
            System.out.println("\n=== Choose your role ===");
            System.out.println("1.Enter as student");
            System.out.println("2.Enter as guest user");
            System.out.println("3.Enter as librarian");
            System.out.println("4.Enter as system manager");

            int choice=getIntInput(1,4);

            switch (choice){
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
                default:
                    System.out.println("Invalid number.Please try again!");
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

            int choice = getIntInput(1, 5);

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
                default:
                    System.out.println("Invalid option! Please try again.");
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

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentUser = librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
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
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private void managerMenu(){
        librarySystem.loadLibrarians();

        System.out.println("=== Manager Menu ===");

        while (true) {
            System.out.println("1.Add librarian");
            System.out.println("2.View librarian performance");
            System.out.println("3.View book deposit statistics");
            System.out.println("4.View student deposit statistics");
            System.out.println("5.Logout");

            int choice=getIntInput(1,5);

            switch (choice){
                case 1:
                addLibrarian();
                librarySystem.saveLibraians();
                break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid number.Please try again!");
            }
        }

    }

    private void loginManager(){
        System.out.println("=== Manager login ===");

        System.out.println("Enter username");
        String username=scanner.nextLine();
        System.out.println("Enter password");
        String password=scanner.nextLine();

        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())){
            System.out.println("Welcome!");
            managerMenu();
        } else {
            System.out.println("Information is false.Please try again!");
        }
    }

    private void addLibrarian(){
        System.out.println("=== New Librarian ===");

        System.out.println("Enter username");
        String username=scanner.nextLine();
        System.out.println("Enter password");
        String password=scanner.nextLine();

        librarySystem.addLibrarian(username,password);

    }

    private void librarianMenu() {
        librarySystem.loadBooks();

        System.out.println("=== Librarian Menu ===");

        while (true) {
            System.out.println("1.Edit password");
            System.out.println("2.Add book");
            System.out.println("3.Searching book");
            System.out.println("4.Edit book's information");
            System.out.println("5.Review and approve book loan requests");
            System.out.println("6.View a student's loan history report");
            System.out.println("7.Activating and deactivating a student");
            System.out.println("8.Receiving a borrowed book");
            System.out.println("9.Logout");

            int choice = getIntInput(1,9);

            switch (choice) {
                case 1:
                    librarySystem.editLibrarianPassword(currentUsre2);
                    librarySystem.saveLibraians();
                    break;
                case 2:
                    System.out.println("=== Add book ===");

                    System.out.println("Enter name");
                    String name=scanner.nextLine();
                    System.out.println("Enter author");
                    String author=scanner.nextLine();
                    System.out.println("Enter year");
                    int year=scanner.nextInt();
                    System.out.println("Enter page count");
                    int pagecount=scanner.nextInt();
                    System.out.println("Enter count");
                    int count=scanner.nextInt();

                    librarySystem.addBook(name,author,year,pagecount,count);
                    librarySystem.saveBooks();
                    break;
                case 3:
                    System.out.println("=== Searching book ===");
                    System.out.println("Enter name");
                    String bookName=scanner.nextLine();
                    librarySystem.searchBook(bookName);
                    break;
                case 4:
                    System.out.println("=== Edit book's information ===");
                    System.out.println("Enter name");
                    String bookn=scanner.nextLine();
                    librarySystem.editBooks(bookn);
                    librarySystem.saveBooks();
                    break;
                    case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid number.Please try again!");
            }
        }
    }

    private void logInLibrarian(){
        System.out.println("=== Login Librarian ===");

        librarySystem.loadLibrarians();

        System.out.println("Enter username");
        String username=scanner.nextLine();
        System.out.println("Enter password");
        String password=scanner.nextLine();

        currentUsre2=librarySystem.authenticateLibrarian(username,password);

        if (currentUsre2!=null){
            System.out.println("Login successful,welcome!\n Librarian"+ currentUsre2.getUserename());
            librarianMenu();
        } else {
            System.out.println("Invalid username or password.Please try again!");
        }
    }

    private void guestMenu(){
        System.out.println("Welcome!");
        librarySystem.loadStudents();

        while (true){
            System.out.println("1.Number of students registered");
            System.out.println("2.Searching book");
            System.out.println("3.View statistical information");
            System.out.println("4.Exit");

            int choice=getIntInput(1,4);

            switch (choice){
                case 1:
                    displayStudentCount();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid number.Please try again!");
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

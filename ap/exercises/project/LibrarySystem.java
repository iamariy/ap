package ap.exercises.project;

import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private LibrarianManager librarianManager;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler=new MenuHandler(this);
        this.librarianManager=new LibrarianManager();
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        while (true){
            System.out.println("\n===Edit Information===");
            System.out.println("1.Edit username: ");
            System.out.println("2.Edit password: ");
            System.out.println("3.Exit");

            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter your choice");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("New username: ");
                    String nu=scanner.nextLine();
                    student.setUsername(nu);
                    break;
                case 2:
                    System.out.println("New password: ");
                    String np=scanner.nextLine();
                    student.setPassword(np);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid number!Please try again.");
            }
        }
    }

    public void borrowBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void returnBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void displayAvailableBooks() {
        System.out.println("Not implemented.");
    }

    public void start() {
        menuHandler.menu();
    }

    public void saveStudents(){
        studentManager.saving();
    }

    public void loadStudents(){
        studentManager.loading();
    }

    public void addLibrarian(String username,String password){
        librarianManager.addLibrarian(username,password);
    }

    public void saveLibraians(){
        librarianManager.saving();
    }

    public void loadLibrarians(){
        librarianManager.loading();
    }

    public Librarian authenticateLibrarian(String username, String password) {
        return librarianManager.authenticateLibrarian(username, password);
    }

    public void editLibrarianPassword(Librarian librarian){
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter new password");
        String np=scanner.nextLine();
        librarian.setPassword(np);
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}

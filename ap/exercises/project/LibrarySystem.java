package ap.exercises.project;

import java.time.LocalDate;
import java.util.Scanner;

public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private LibrarianManager librarianManager;
    private BookManager bookManager;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler=new MenuHandler(this);
        this.librarianManager=new LibrarianManager();
        this.bookManager=new BookManager();
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public int getBookCount(){
        return this.bookManager.counters();
    }

    public int getLoanCount(){
        return this.bookManager.loans();
    }

    public int getActive(){
        return this.bookManager.activeLoans();
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
        bookManager.reqests(student,LocalDate.now());
    }

    public void returnBook(Student student) {
        bookManager.returnBook(student,LocalDate.now());
    }

    public void displayAvailableBooks() {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter book name");
        String name=scanner.nextLine();

        bookManager.isAvailable(name);
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

    public void addBook(String name,String author,int year,int pagecounter,int count,Librarian librarian){
        bookManager.addBook(name,author,year,pagecounter,count,librarian);
    }

    public void saveBooks(){
        bookManager.saving();
    }

    public void loadBooks(){
        bookManager.loading();
    }

    public void searchBook(String name){
        bookManager.searching(name);
    }

    public void editBooks(String name){
        Scanner scanner=new Scanner(System.in);

        while (true){
            System.out.println("1.Edit book name");
            System.out.println("2.Edit author");
            System.out.println("3.Edit year");
            System.out.println("4.Edit page count");
            System.out.println("5.Exit");

            System.out.println("Enter choice");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    bookManager.editName(name);
                    break;
                case 2:
                    bookManager.editAuthor(name);
                    break;
                case 3:
                    bookManager.editYear(name);
                    break;
                case 4:
                    bookManager.editPage(name);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid number.Please try again!");
            }
        }
    }

    public void activing(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter student id");
        String str=scanner.nextLine();
        studentManager.changeActiving(str);
    }

    public void accepting(Librarian librarian){
        bookManager.accept(librarian,LocalDate.now());
    }

    public void returned(Librarian librarian){
        bookManager.acceptReturn(librarian,LocalDate.now());
    }

    public void print(){
        bookManager.print();
    }

    public int delay(){
        return bookManager.delay();
    }

    public void load(){
        bookManager.loadRequest();
    }

    public void loadAccept(){
        bookManager.loadAccept();
    }

    public void loadAllR(){
        bookManager.loadAllR();
    }

    public void loadAllA(){
        bookManager.loadAllA();
    }

    public void loadReturn(){
        bookManager.loadReturn();
    }

    public void loadAcceptReturn(){
        bookManager.loadAcceptReturn();
    }

    public void librarianHistory(String username){
        bookManager.librarianHistory(username);
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }

}

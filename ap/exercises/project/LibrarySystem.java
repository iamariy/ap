package ap.exercises.project;

import java.time.LocalDate;
import java.util.Scanner;

public class LibrarySystem implements Connection,HandleData,Getter{
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private LibrarianManager librarianManager;
    private BookManager bookManager;
    private Scanner scanner;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.menuHandler=new MenuHandler(this);
        this.librarianManager=new LibrarianManager();
        this.bookManager=new BookManager();
        this.scanner=new Scanner(System.in);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }
    @Override
    public int getBookCount(){
        return this.bookManager.getBookCount();
    }
    @Override
    public int getLoanCount(){
        return this.bookManager.getLoanCount();
    }
    @Override
    public int activeLoans(){
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
        bookManager.borrowBook(student,LocalDate.now());
    }

    public void returnBook(Student student) {
        bookManager.returnBook(student,LocalDate.now());
    }

    public void displayAvailableBooks() {

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

    public void saveLibrarians(){
        librarianManager.saving();
    }

    public void loadLibrarians(){
        librarianManager.loading();
    }

    public Librarian authenticateLibrarian(String username, String password) {
        return librarianManager.authenticateLibrarian(username, password);
    }

    public void editLibrarianPassword(Librarian librarian){

        System.out.println("Enter new password");
        String np=scanner.nextLine();
        librarian.setPassword(np);
    }
    @Override
    public void addBook(String name,String author,int year,int pagecounter,int count,Librarian librarian){
        bookManager.addBook(name,author,year,pagecounter,count,librarian);
    }
    @Override
    public void saveBooks(){
        bookManager.saveBooks();
    }
    @Override
    public void loadBooks(){
        bookManager.loadBooks();
    }
    @Override
    public void searchBook(String name){
        bookManager.searchBook(name);
    }

    public void editBooks(String name){

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
        System.out.println("Enter student id");
        String str=scanner.nextLine();
        studentManager.changeActiving(str);
    }

    public void accept(Librarian librarian){
        bookManager.accept(librarian,LocalDate.now());
    }

    public void acceptReturn(Librarian librarian){
        bookManager.acceptReturn(librarian,LocalDate.now());
    }
    @Override
    public void print(){
        bookManager.print();
    }
    @Override
    public int delay(){
        return bookManager.delay();
    }
    @Override
    public void loadRequest(){
        bookManager.loadRequest();
    }
    @Override
    public void loadAccept(){
        bookManager.loadAccept();
    }
    @Override
    public void loadAllR(){
        bookManager.loadAllR();
    }
    @Override
    public void loadAllA(){
        bookManager.loadAllA();
    }
    @Override
    public void loadReturn(){
        bookManager.loadReturn();
    }
    @Override
    public void loadAcceptReturn(){
        bookManager.loadAcceptReturn();
    }
    @Override
    public void librarianHistory(String username){
        bookManager.librarianHistory(username);
    }
    @Override
    public int requestCounter(){
        return this.bookManager.requestCounter();
    }
    @Override
    public long daysCounter(){
        return bookManager.daysCounter();
    }
    @Override
    public void delayStudents(){
        bookManager.delayStudents();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
}

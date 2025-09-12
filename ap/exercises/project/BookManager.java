package ap.exercises.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books;
    private DataManager dataManager;
    private List<BorrowRequest> borrowRequests;
    private List<AcceptBorrow> acceptBorrows;
    private Book book;

    public BookManager(){
        this.books=new ArrayList<>();
        this.dataManager=new DataManager();
        this.borrowRequests=new ArrayList<>();
        this.acceptBorrows=new ArrayList<>();
        this.books=books;
    }

//    public BookManager(BorrowRequest borrowRequest){
//        this.borrowRequest=borrowRequest;
//    }

    public void addBook(String name,String author,int year,int pagecounter,int count){
        Book newBook=new Book(name,author,year,pagecounter,count);


            books.add(newBook);
            System.out.println("book %d is added successfully!");
    }

    public void saving(){
        dataManager.saveBooks(books);
    }

    public void loading(){
        dataManager.loadBooks(books);
    }

    public void loadRequest(){
        dataManager.loadRequest(borrowRequests);
    }

    public void loadAccept(){
        dataManager.loadAccept(acceptBorrows);
    }

    public void searching(String name){
        boolean found=false;

        for (Book book : books) {
            if (name.equals(book.getName())) {
                System.out.println("Name is:" + book.getName() + "   Author is:" + book.getAuthor() + "   Year is:" + book.getYear() + "   Page count is:" +book.getPagecounter());
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Book is not found");
        }
    }

    public void editName(String name){
        boolean found=false;
        int count=0;

        for (Book book : books){
            if (name.equals(book.getName())){
                Scanner scanner=new Scanner(System.in);
                System.out.println("Enter new name");
                String nm=scanner.nextLine();
                book.setName(nm);
                count++;
                found=true;
            }
        }
        if (found){
            System.out.println("Book is updated successfully!");
            saving();
        }
        else {
            System.out.println("Book is not found");
        }
    }

    public void editAuthor(String name){
        boolean found=false;
        int count=0;

        for (Book book : books){
            if (name.equals(book.getName())){
                Scanner scanner=new Scanner(System.in);
                System.out.println("Enter new author");
                String na=scanner.nextLine();
                book.setAuthor(na);
                count++;
                found=true;
            }
        }
        if (found){
            System.out.println("Book is updated successfully!");
            saving();
        }
        else {
            System.out.println("Book is not found");
        }
    }

    public void editYear(String name){
        boolean found=false;
        int count=0;

        for (Book book : books){
            if (name.equals(book.getName())){
                Scanner scanner=new Scanner(System.in);
                System.out.println("Enter new year");
                int ny=scanner.nextInt();
                book.setYear(ny);
                count++;
                found=true;
            }
        }
        if (found){
            System.out.println("Book is updated successfully!");
            saving();
        }
        else {
            System.out.println("Book is not found");
        }
    }

    public void editPage(String name){
        boolean found=false;
        int count=0;

        for (Book book : books){
            if (name.equals(book.getName())){
                Scanner scanner=new Scanner(System.in);
                System.out.println("Enter new page count");
                int nc=scanner.nextInt();
                book.setPagecounter(nc);
                count++;
                found=true;
            }
        }
        if (found){
            System.out.println("Book is updated successfully!");
            saving();
        }
        else {
            System.out.println("Book is not found");
        }
    }

    public Book isAvailable(String searchTerm) {
        for (Book book : books) {
            if (searchTerm.equals(book.getName()) ||
                    searchTerm.equals(book.getAuthor()) ||
                    searchTerm.equals(Integer.toString(book.getYear()))) {

                System.out.println("Book name is: " + book.getName() +
                        " Author is: " + book.getAuthor() +
                        " Year is: " + book.getYear() +
                        " Page count is: " + book.getPagecounter());

                if (book.getCount() > 0) {
                    System.out.println("Book is available!");
                    return book;
                } else {
                    System.out.println("Book is not available!");
                    return null;
                }
            }
        }
        System.out.println("Book is not registered!");
        return null;
    }

    public void reqests(Student student, LocalDate start){
        if (student.isActive()==false) {
            System.out.println("You can't borrow book");
            return;
        }

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter book name");
        String name=scanner.nextLine();

        Book book=isAvailable(name);

        if (book == null) {
            return;
        }

        LocalDate end=start.plusDays(1);

        BorrowRequest borrow=new BorrowRequest(student,book,start,end);
        borrowRequests.add(borrow);
        dataManager.saveRequest(borrowRequests);
    }

    public void accept(Librarian librarian,LocalDate startDate){
        LocalDate endDate=startDate.plusDays(20);
        Scanner scanner=new Scanner(System.in);

        if (borrowRequests.isEmpty()) {
            System.out.println("No borrow requests to review.");
            return;
        }

        for (int i=0;i<borrowRequests.size();i++){
            BorrowRequest borrow=borrowRequests.get(i);

            if ((startDate.isEqual(borrow.getStart()) || startDate.isAfter(borrow.getStart()))
            && (startDate.isEqual(borrow.getEnd()) || startDate.isBefore(borrow.getEnd()))){
                System.out.println(borrow.getStudent().getName() +","+ borrow.getStudent().getStudentId() +","+ borrow.getBook().getName() +","+ borrow.getBook().getAuthor());
                System.out.println("Do you want accept it?  (y/n)?");
                String input=scanner.nextLine();
                if (input.equals("y")){
                    AcceptBorrow accept=new AcceptBorrow(borrow,librarian,startDate,endDate);
                    borrowRequests.remove(borrowRequests.get(i));
                    acceptBorrows.add(accept);
                    for (Book book : books){
                        if (book.getName().equals(borrow.getBook().getName())){
                            book.setCount(book.getCount()-1);
                            saving();
                        }
                    }
                    System.out.println("Accept request");
                } else if (input.equals("n")){
                    borrowRequests.remove(i);
                    System.out.println("Not accept request");
                } else {
                    System.out.println("Invalid choice!Please try again");
                }
            }
        }
        dataManager.saveRequest(borrowRequests);
        dataManager.saveAccept(acceptBorrows);
    }
}

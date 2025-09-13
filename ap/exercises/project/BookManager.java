package ap.exercises.project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books;
    private DataManager dataManager;
    private List<BorrowRequest> borrowRequests;
    private List<AcceptBorrow> acceptBorrows;
    private List<BorrowRequest> allRequests;
    private List<AcceptBorrow> allAccepts;
    private List<ReturnBook> returnBooks;
    private List<AcceptReturn> acceptReturns;

    public BookManager(){
        this.books=new ArrayList<>();
        this.dataManager=new DataManager();
        this.borrowRequests=new ArrayList<>();
        this.acceptBorrows=new ArrayList<>();
        this.allRequests=new ArrayList<>();
        this.allAccepts=new ArrayList<>();
        this.returnBooks=new ArrayList<>();
        this.acceptReturns=new ArrayList<>();
    }


    public void addBook(String name,String author,int year,int pagecounter,int count,Librarian librarian){
        Book newBook=new Book(name,author,year,pagecounter,count,librarian);


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

    public void loadAllR(){
        dataManager.loadRequestAll(allRequests);
    }

    public void loadAllA(){
        dataManager.loadAcceptAll(allAccepts);
    }

    public void loadReturn(){
        dataManager.loadReturn(returnBooks);
    }

    public void loadAcceptReturn(){
        dataManager.loadReturnAccept(acceptReturns);
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
        allRequests.add(borrow);
        dataManager.saveRequest(borrowRequests);
        dataManager.saveRequestAll(allRequests);
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
                    allAccepts.add(accept);
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
        dataManager.saveAcceptAll(allAccepts);
    }

    public void returnBook(Student student,LocalDate returnDate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book name");
        String str = scanner.nextLine();

        boolean found = false;

        for (AcceptBorrow accept : acceptBorrows) {
            if (accept.getBorrowRequest().getBook().getName().equals(str)) {
                ReturnBook returnBook = new ReturnBook(student, accept, returnDate);
                returnBooks.add(returnBook);
                dataManager.saveReturn(returnBooks);
                found = true;

                break;
            }
        }

        if (!found) {
            System.out.println("The book is not found");
        }
    }

    public void acceptReturn(Librarian librarian,LocalDate date){
        if (returnBooks.isEmpty()) {
            System.out.println("No borrow requests to review.");
            return;
        }

        for (int i=0;i<returnBooks.size();i++){
            ReturnBook returns=returnBooks.get(i);

            AcceptReturn accept=new AcceptReturn(returns,librarian,date);
            returnBooks.remove(returnBooks.get(i));
            acceptReturns.add(accept);

            for (int j=0;j<acceptBorrows.size();j++){
                AcceptBorrow acceptBorrow=acceptBorrows.get(j);
                if (acceptBorrow.getBorrowRequest().getBook().getName().equals(accept.getReturnBook().getAcceptBorrow().getBorrowRequest().getBook().getName())){
                    acceptBorrows.remove(acceptBorrows.get(j));
                }
            }

            for (Book book : books){
                if (book.getName().equals(returns.getAcceptBorrow().getBorrowRequest().getBook().getName())){
                    book.setCount(book.getCount()+1);
                    saving();
                }
            }
        }
        dataManager.saveReturn(returnBooks);
        dataManager.saveReturnAccept(acceptReturns);
        dataManager.saveAccept(acceptBorrows);
    }

    public int counters(){
        int sum=0;
        for (Book book : books){
            sum+=book.getCount();
        }
        return sum;
    }

    public int loans(){
        return allAccepts.size();
    }

    public int activeLoans(){
        return this.acceptBorrows.size();
    }

    public void print(){
        for (AcceptBorrow all : allAccepts){
            System.out.println("Student name: " +all.getBorrowRequest().getStudent().getName()+ " Student Id: " +all.getBorrowRequest().getStudent().getStudentId()+ " Book name: " +all.getBorrowRequest().getBook().getName()+ " Author name: " +all.getBorrowRequest().getBook().getAuthor());
        }
    }

    public int delay(){
        int count=0;
        for (AcceptBorrow all : allAccepts){
            for (AcceptReturn acceptReturn : acceptReturns){
                if (ChronoUnit.DAYS.between(acceptReturn.getReturned(),all.getEndDate())<0){
                    count++;
                }
            }
        }
        return count;
    }

    public void librarianHistory(String username){
        int sum1=0,sum2=0,sum3=0;

        for (Book book : books){
            if (book.getLibrarian().getUsername().equals(username)){
                sum1+=book.getCount();
            }
        }
        for (AcceptBorrow all : allAccepts){
            if (all.getLibrarian().getUsername().equals(username)){
                sum2++;
            }
        }
        for (AcceptReturn acceptReturn : acceptReturns){
            if (acceptReturn.getLibrarian().getUsername().equals(username)){
                sum3++;
            }
        }
        System.out.println("Count of book add: " +sum1+ "\nAll accept borrow: " +sum2+ "\nAll return borrow: " +sum3);
    }

}

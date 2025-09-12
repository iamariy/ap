package ap.exercises.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books;
    private DataManager dataManager;
    private List<BorrowRequest> borrowRequests;

    public BookManager(){
        this.books=new ArrayList<>();
        this.dataManager=new DataManager();
        this.borrowRequests=new ArrayList<>();
    }

//    public BookManager(BorrowRequest borrowRequest){
//        this.borrowRequest=borrowRequest;
//    }

    public void addBook(String name,String author,int year,int pagecounter,int count){
        Book newBook=new Book(name,author,year,pagecounter,count);

        for (int i=0;i<count;i++){
            books.add(newBook);
            System.out.format("book %d is added successfully!\n", i+1);
        }
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

//    public Book isAvailable(String name) {
////        Scanner scanner = new Scanner(System.in);
////        boolean found=false;
//
//        for (Book book : books) {
//            if (name.equals(book.getName()) || name.equals(book.getAuthor()) || name.equals(Integer.toString(book.getYear()))) {
//                System.out.println("Book name is: " + book.getName() + " Author is: " + book.getAuthor() + " Year is: " + book.getYear() + " Page count is: " + book.getPagecounter());
//                if (book.getCount() > 0) {
//                    System.out.println("Book is available!");
//                } else if (book.getCount() == 0) {
//                    System.out.println("Book is not available!");
//                }
////                found=true;
////                break;
////            } if (!found){
//                System.out.println("Book is not registered!");
////            }
//        }
//    }


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
}

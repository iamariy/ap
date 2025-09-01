package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> books;
    private DataManager dataManager;
//    private Book book;

    public BookManager(){
        this.books=new ArrayList<>();
        this.dataManager=new DataManager();
//        this.books=books;
    }

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
}

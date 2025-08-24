package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books;
    private DataManager dataManager;

    public BookManager(){
        this.books=new ArrayList<>();
        this.dataManager=new DataManager();
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
}

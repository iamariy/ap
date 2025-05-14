package ap.exercises.mianterm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Student extends Person {
    private String firstname;
    private String lastname;
    private String major;
    private String password;
    private LocalDate date;
    private int id;

    public Student(String firstname, String lastname, String major, String password, int id){
        super(firstname,lastname,id);
        this.major=major;
        this.date=LocalDate.now();
        this.password=password;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getMajor(){
        return major;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getPassword(){
        return password;
    }
    public int getId(){
        return id;
    }
    @Override
    public String toString(){
        return firstname +","+ lastname +","+ major +","+ date +","+ password +","+ id;
    }

    public void searchingBookByName(Library library,String name){
        ArrayList<Book> found1 =new ArrayList<>();
        found1=library.searchByName(name);
        if (found1.isEmpty()){
            System.out.println("No books with this title were found.");
        } else {
            System.out.println("Books found:");
            for (Book book : found1){
                book.print();
            }
        }
    }
    public void serchingBookByAuthor(Library library,String author){
        ArrayList<Book> found2=new ArrayList<>();
        found2=library.searchByAuthor(author);
        if (found2.isEmpty()){
            System.out.println("No books with this title were found.");
        } else {
            System.out.println("Books found:");
            for (Book book : found2){
                book.print();
            }
        }
    }
}

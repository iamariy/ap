package ap.exercises.mianterm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Student extends Person {
    private String major;
    private String password;
    private LocalDate date;

    public Student(String firstname, String lastname, String major, String password, int id,LocalDate date){
        super(firstname,lastname,id);
        this.major=major;
        this.password=password;
        this.date=date;
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

    @Override
    public String toString(){
        return super.getFirstname() +","+ super.getLastname() +","+ getMajor() +","+ getDate() +","+ getPassword() +","+ super.getId();
    }
    public void print(){
        System.out.println("firstname:"+ super.getFirstname() +"\tlastname:"+ super.getLastname() +"\tmajor:"+ getMajor() +"\tregister date:"+ getDate() +"\tpassword:"+ getPassword() +"\tid:"+ getId());
    }
    public LocalDate getRegistrationDate() {
        return date;
    }

    public boolean logIn(int id, String password) {
        return this.getId() == id && Objects.equals(this.password, password);
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

    public void searchingBookByAuthor(Library library, String author) {
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

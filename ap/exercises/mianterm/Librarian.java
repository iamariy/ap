package ap.exercises.mianterm;

import java.util.Objects;

public class Librarian extends Person {

    private String password;


    public Librarian(String firstname,String lastname,String password,int id){
        super(firstname,lastname,id);
        this.password=password;
    }


    public String getPassword(){
        return password;
    }

    public boolean logIn(int id,String password){
        return Objects.equals(this.id,id) && Objects.equals(this.password,password);
    }


    public void setFirstname(String firstname){
        if (!firstname.isEmpty()) this.firstname=firstname;
    }
    public void setLastname(String  lastname){
        if (!lastname.isEmpty()) this.lastname=lastname;
    }
    public void setPassword(String password){
        if (!password.isEmpty()) this.password=password;
    }


    public Book addBook(Library library,String name,String author,int year,int pagecounter){
        Book book=new Book(name,author,year,pagecounter);
        library.addBook(book);
        library.setBooks(library.getBooks());
        return book;
    }

    @Override
    public String toString(){
        return super.getFirstname() +","+ super.getLastname() +","+ getPassword() +","+ getId();
    }

    public void printInfo(){
        System.out.println("firstname:"+ super.getFirstname() +"\tlastname:"+ super.getLastname());
    }
}

package ap.exercises.mianterm;

import java.util.Objects;
import java.util.Scanner;

public class Menu {
    private Input input;
    private LibrarySystem librarySystem;
    private Student student;
    private Library library;
    private Librarian librarian;
    private Book book;
    private Manager manager;

    public void setInput(Input input){
        this.input= Objects.requireNonNull(input,"Input can,t be null");
    }
    public void setLibrarySystem(LibrarySystem librarySystem) {
        this.librarySystem = Objects.requireNonNull(librarySystem, "LibrarySystem cannot be null");
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setLibrary(Library library) {
        this.library = Objects.requireNonNull(library, "Library cannot be null");
    }
    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void showMenu(){
        System.out.println("1.Enter student");
        System.out.println("2.Enter librarian");
        System.out.println("3.Enter manager");
        System.out.println("4.Exit");
        System.out.println("Enter your choice");
        int choice=input.inputInteger();

        switch (choice){
            case 1:
                librarySystem.loginStudent(input.inputIntegerId(),input.inputStringPassword());
                break;
            case 2:
                librarySystem.loginLibrarian(input.inputIntegerId(),input.inputStringPassword());
                break;
            case 3:
                librarySystem.loginManager(input.inputIntegerId(),input.inputStringPassword());
                break;
            case 4:
                System.out.println("Exit");
                return;
            default:
                System.out.println("Invalid number.try again");
        }
    }

    public void studentMenu(){
        System.out.println("Students Menu:");
        System.out.println("1.Register in site:");
        System.out.println("2.Searching book by name:");
        System.out.println("3.Seaching book by author");
        System.out.println("4.Exit");
        System.out.println("Enter your choice");
        int choice=input.inputInteger();

        switch (choice){
            case 1:
                library.registerStudent(input.inputStringNmae(),input.inputStringLast(),input.inputStringMajor(),input.inputStringPassword(),input.inputIntegerId());
                break;
            case 2:
                student.searchingBookByName(library,input.inputStringNmae());
                break;
            case 3:
                student.serchingBookByAuthor(library,input.inputStringAuthor());
                break;
            case 4:
                librarySystem.logoutStudent();
                break;
            default:
                System.out.println("Invalid number.try again");
        }
    }

    public void librarianMenu(){
        System.out.println("Librarian Menu:");
        System.out.println("1.Edit firstname:");
        System.out.println("2.Edit lastname:");
        System.out.println("3.Edit password:");
        System.out.println("4.Add book");
        System.out.println("5.Exit");
        int choice=input.inputInteger();

        switch (choice){
            case 1:
                librarian.setFirstname(input.inputStringNmae());
                break;
            case 2:
                librarian.setLastname(input.inputStringLast());
                break;
            case 3:
                librarian.setPassword(input.inputStringPassword());
                break;
            case 4:
                librarian.addBook(library,input.inputStringNmae(),input.inputStringAuthor(),input.inputIntegerYear(),input.inputIntegerPage());
                break;
            case 5:
                librarySystem.logoutLibrarian();
                break;
            default:
                System.out.println("Invalid number.try again");
        }
    }

    public void managerMenu(){
        System.out.println("Manager Menu:");
        System.out.println("1.Add librarian");
        System.out.println("2.Exit");
        int choice= input.inputInteger();

        switch (choice){
            case 1:
                manager.addLibraian(library,input.inputStringNmae(),input.inputStringLast(),input.inputStringPassword(),input.inputIntegerId());
                break;
            case 2:
                librarySystem.logoutManager();
                break;
            default:
                System.out.println("Invalid number.try again");
        }
    }
}

package ap.exercises.mianterm;

import java.util.Scanner;

public class Menu {
    private Input input;
    private LibrarySystem librarySystem;
    private Student student;
    private Library library;
    private Librarian librarian;
    private Book book;
    private Manager manager;

    public void showMenu(){
        System.out.println("1.Enter student");
        System.out.println("2.Enter librarian");
        System.out.println("3.Enter manager");
        System.out.println("4.Exit");
        System.out.println("Enter your choice");
        int choice=input.inputInteger();

        switch (choice){
            case 1:
                librarySystem.loginStudent(input.inputInteger(),input.inputString());
                break;
            case 2:
                librarySystem.loginLibrarian(input.inputInteger(),input.inputString());
                break;
            case 3:
                librarySystem.loginManager(input.inputInteger(),input.inputString());
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
                library.registerStudent(input.inputString(),input.inputString(),input.inputString(),input.inputString(),input.inputInteger());
                break;
            case 2:
                student.searchingBookByName(library,input.inputString());
                break;
            case 3:
                student.serchingBookByAuthor(library,input.inputString());
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
                librarian.setFirstname(input.inputString());
                break;
            case 2:
                librarian.setLastname(input.inputString());
                break;
            case 3:
                librarian.setPassword(input.inputString());
                break;
            case 4:
                librarian.addBook(library,input.inputString(),input.inputString(),input.inputInteger(),input.inputInteger());
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
                manager.addLibraian(library,input.inputString(),input.inputString(),input.inputString(),input.inputInteger());
                break;
            case 2:
                librarySystem.logoutManager();
                break;
            default:
                System.out.println("Invalid number.try again");
        }
    }
}

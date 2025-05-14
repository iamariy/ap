package ap.exercises.mianterm;

import javax.swing.*;
import java.util.Objects;

public class LibrarySystem {
    private Student student;
    private boolean logS=false;
    private Librarian librarian;
    private boolean logL=false;
    private Manager manager;
    private boolean logM=false;
    private Menu menu;

    public boolean loginStudent(int id,String password){
        if (Objects.equals(student.getId(),id) && Objects.equals(student.getPassword(),password)){
            logS=true;
            System.out.println("The student's entry was successful.");
            menu.studentMenu();
            return true;
        } else{
            System.out.println("The password or passcode is incorrect.");
            return false;
        }
    }
    public void logoutStudent(){
        logS=false;
        System.out.println("The exit was successful.");
    }

    public boolean loginLibrarian(int id,String password){
        if (Objects.equals(librarian.getId(),id) && Objects.equals(librarian.getPassword(),password)){
            logL=true;
            System.out.println("The student's entry was successful.");
            menu.librarianMenu();
            return true;
        } else {
            System.out.println("The password or passcode is incorrect.");
            return false;
        }
    }
    public void logoutLibrarian(){
        logL=false;
        System.out.println("The exit was successful.");
    }

    public boolean loginManager(int id,String password){
        if (Objects.equals(manager.getId(),id) && Objects.equals(manager.getPassword(),password)){
            logM=true;
            System.out.println("The student's entry was successful.");
            menu.managerMenu();
            return true;
        } else {
            System.out.println("The password or passcode is incorrect.");
            return false;
        }
    }
    public void logoutManager(){
        logM=false;
        System.out.println("The exit was successful.");
    }
}

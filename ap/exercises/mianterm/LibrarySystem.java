package ap.exercises.mianterm;

import javax.swing.*;
import java.util.Objects;

public class LibrarySystem {
    private Student student;
    private boolean logS = false;
    private Librarian librarian;
    private boolean logL = false;
    private Manager manager;
    private boolean logM = false;
    private Menu menu;
    private Library library;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return this.manager;
    }

    public boolean loginStudent(int id, String password) {
        for(Student student : library.getStudents()) {
            if (student.logIn(id, password)) {
                this.student = student;
                logS = true;
                System.out.println("Student login successful.");
                menu.studentMenu();
                return true;
            } else {
                System.out.println("The password or passcode is incorrect.");
                return false;
            }
        }
        System.out.println("No student found with ID: " + id);
        return false;
    }

    public void logoutStudent() {
        logS = false;
        System.out.println("The exit was successful.");
    }

    public boolean loginLibrarian(int id, String password) {
        for (Librarian lib : library.getLibrarians()) {
            if (lib.logIn(id, password)) {
                this.librarian = lib;
                logL = true;
                System.out.println("Librarian login successful.");
                menu.librarianMenu();
                return true;
            } else {
                System.out.println("The password or passcode is incorrect.");
                return false;
            }
        }
        System.out.println("No librarian found with ID: "+id );
        return false;
    }

    public void logoutLibrarian(){
        logL=false;
        System.out.println("The exit was successful.");
    }

    public boolean loginManager(int id,String password){
        Manager manager = this.getManager();
        if(manager != null && manager.getId() == id &&
                Objects.equals(manager.getPassword(), password)) {
            this.manager = manager;
            logM = true;
            System.out.println("Manager login successful.");
            menu.managerMenu();
            return true;
        }
        System.out.println("The password or passcode is incorrect.");
        return false;
    }
    public void logoutManager(){
        logM=false;
        System.out.println("The exit was successful.");
    }
}

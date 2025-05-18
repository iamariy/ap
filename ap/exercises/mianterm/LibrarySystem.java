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

    public void setMenu(Menu menu) {
        this.menu = Objects.requireNonNull(menu, "Menu cannot be null");
    }

    public void setManager(Manager manager) {
        this.manager = Objects.requireNonNull(manager,"Manager cannot be null");
    }

    public Manager getManager() {
        return this.manager;
    }
    public Librarian getLibrarian(){
        return this.librarian;
    }
    public void setLibrarian(Librarian librarian){
        this.librarian=Objects.requireNonNull(librarian,"Librarian cannot be null");
    }
    public Student getStudent(){
        return this.student;
    }
    public void setStudent(Student student){
        this.student=Objects.requireNonNull(student,"Student cannot be null");
    }
    public void setLibrary(Library library){
        this.library=Objects.requireNonNull(library,"Library cannot be null");
    }

    public boolean loginStudent(int id, String password) {
        for(Student student : library.getStudents()) {
            if (student.logIn(id, password)) {
                this.student = student;
                logS = true;
                System.out.println("Student login successful.");
                menu.setCurrentStudent(student);
                menu.setStudent(student);
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
            if (lib.getId() == id) {
                if (Objects.equals(lib.getPassword(), password)) {
                    this.librarian = lib;
                    logL = true;
                    System.out.println("Librarian login successful. Welcome, " + lib.getFirstname());
                    menu.setLoggedInLibrarian(lib);
                    menu.librarianMenu();
                    return true;
                } else {
                    System.out.println("Incorrect password for ID: " + id);
                    return false;
                }
            }
        }
        System.out.println("No librarian found with ID: " + id);
        return false;
    }


    public void logoutLibrarian(){
        logL=false;
        System.out.println("The exit was successful.");
    }

    public boolean loginManager(int id,String password){
        Manager manager = this.getManager();
        if (manager == null) {
            System.out.println("Manager not initialized!");
            return false;
        }
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

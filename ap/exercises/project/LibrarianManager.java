package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;

public class LibrarianManager {
    private List<Librarian> librarians;
    private DataManager dataManager;

    public LibrarianManager(){
        this.librarians=new ArrayList<>();
        this.dataManager=new DataManager();
    }

    public void addLibrarian(String username,String password){
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Librarian newLibraian=new Librarian(username,password);
        librarians.add(newLibraian);
        System.out.println("Student registration completed successfully.");
    }

    public Librarian authenticateLibrarian(String username, String password) {
        return librarians.stream()
                .filter(l -> l.getUsername().equals(username) && l.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void saving(){
        dataManager.saveLibrarian(librarians);
    }

    public void loading(){
        dataManager.loadLibrarian(librarians);
    }

    private boolean isUsernameTaken(String username) {
        return librarians.stream().anyMatch(l -> l.getUsername().equals(username));
    }

}

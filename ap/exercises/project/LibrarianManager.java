package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;

public class LibrarianManager {
    private List<Librarian> librarians;
    private DataManager dataManager;

    public LibrarianManager(DataManager dataManager){
        this.librarians=new ArrayList<>();
        this.dataManager=dataManager;
    }

    public void addLibrarian(String username,String password){
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Librarian newLibrarian = new Librarian(username, password);
        librarians.add(newLibrarian);
        System.out.println("Librarian added successfully.");
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


package ap.exercises.mianterm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Input input=new Input(scanner);
        Menu menu=new Menu();
        Library library=new Library();
        LibrarySystem librarySystem=new LibrarySystem();
        LibraryData libraryData = new LibraryData();

        library.books = libraryData.loadBooks();
        library.librarians = libraryData.loadLibrarians();
        library.students = libraryData.loadStudents();
        librarySystem.setManager(libraryData.loadManager());

        if (!libraryData.hasData()) {
            Manager manager=new Manager("Sama","Zolgkhani",input.inputStringPassword(),"PHD",1);
            librarySystem.setManager(manager);
            System.out.println("Initialized with default data");
        }

        menu.setInput(input);
        menu.setLibrary(library);
        menu.setLibrarySystem(librarySystem);

        if(library.librarians.isEmpty()) {
            library.addLibrarian(new Librarian("Neda", "Rashtch", input.inputStringPassword(), 2));
            library.addLibrarian(new Librarian("Yekta", "Saeedian", input.inputStringPassword(), 3));
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            libraryData.saveBooks(library.books);
            libraryData.saveLibrarians(library.librarians);
            libraryData.saveStudents(library.students);
            libraryData.saveManager(librarySystem.getManager());
            System.out.println("Data saved successfully");
        }));

        menu.showMenu();
    }
}

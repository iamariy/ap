package ap.exercises.mianterm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Input input = new Input(scanner);
        Library library = new Library();
        LibrarySystem librarySystem = new LibrarySystem();
        LibraryData libraryData = new LibraryData();
        Menu menu=new Menu(library,libraryData);

        librarySystem.setLibrary(library);
        menu.setLibrary(library);

        library.books = libraryData.loadBooks();
        library.librarians = libraryData.loadLibrarians();
        library.students = libraryData.loadStudents();

        Manager manager = libraryData.loadManager();
        if (manager == null) {
            System.out.println("Creating default manager...");
            System.out.print("Enter default manager password: ");
            String password = input.inputString();
            manager = new Manager("Sama", "Zolfkhani", password, "PHD", 1);
            libraryData.saveManager(manager);
        }

        librarySystem.setManager(manager);
        menu.setManager(manager);
        librarySystem.setMenu(menu);
        menu.setLibrarySystem(librarySystem);
        menu.setInput(input);

        if (library.librarians.isEmpty()) {
            System.out.println("Creating default librarians...");
            library.addLibrarian(new Librarian("Neda", "Rashtch", input.inputString(), 2));
            library.addLibrarian(new Librarian("Yekta", "Saeedian", input.inputString(), 3));
            System.out.println("System initialized with default data");
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            libraryData.saveBooks(library.books);
            libraryData.saveLibrarians(library.librarians);
            libraryData.saveStudents(library.students);
            libraryData.saveManager(librarySystem.getManager());
            System.out.println("All data saved to CSV files");
        }));

        menu.showMenu();
        scanner.close();
    }
}

package ap.exercises.mianterm;

import java.io.*;
import java.util.ArrayList;

public class LibraryData {
    private final String dataDirectory;
    private final String booksFile;
    private final String librariansFile;
    private final String studentsFile;
    private final String managerFile;

    public LibraryData() {
        this("library_data");
    }

    public LibraryData(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        this.booksFile = dataDirectory + File.separator + "books.dat";
        this.librariansFile = dataDirectory + File.separator + "librarians.dat";
        this.studentsFile = dataDirectory + File.separator + "students.dat";
        this.managerFile = dataDirectory + File.separator + "manager.dat";

        new File(dataDirectory).mkdirs();
    }

    public void saveBooks(ArrayList<Book> books) {
        saveData(books, booksFile);
    }

    public ArrayList<Book> loadBooks() {
        return loadData(booksFile, new ArrayList<>());
    }

    public void saveLibrarians(ArrayList<Librarian> librarians) {
        saveData(librarians, librariansFile);
    }

    public ArrayList<Librarian> loadLibrarians() {
        return loadData(librariansFile, new ArrayList<>());
    }

    public void saveStudents(ArrayList<Student> students) {
        saveData(students, studentsFile);
    }

    public ArrayList<Student> loadStudents() {
        return loadData(studentsFile, new ArrayList<>());
    }

    public void saveManager(Manager manager) {
        saveData(manager, managerFile);
    }

    public Manager loadManager() {
        return loadData(managerFile, null);
    }

    private <T> void saveData(T data, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error saving data to " + filePath + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T loadData(String filePath, T defaultValue) {
        if (!new File(filePath).exists()) {
            return defaultValue;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filePath))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + filePath + ": " + e.getMessage());
            return defaultValue;
        }
    }

    public boolean hasData() {
        return new File(booksFile).exists() ||
                new File(librariansFile).exists() ||
                new File(studentsFile).exists() ||
                new File(managerFile).exists();
    }
}

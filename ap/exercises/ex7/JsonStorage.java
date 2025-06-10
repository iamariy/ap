package ap.exercises.ex7;

import ap.exercises.mianterm.Trust;
import ap.exercises.mianterm.Librarian;
import ap.exercises.mianterm.Book;
import ap.exercises.mianterm.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage implements Storage {
    private final String basePath;
    private final Gson gson = new Gson();

    public JsonStorage() {
        this.basePath = "library_data";
        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    public void saveBooks(List<Book> books) {
        try (FileWriter writer = new FileWriter(basePath + "/books.json")) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @Override
    public List<Book> loadBooks() {
        File file = new File(basePath + "/books.json");
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Book>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (FileWriter writer = new FileWriter(basePath + "/students.json")) {
            gson.toJson(students, writer);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @Override
    public List<Student> loadStudents() {
        File file = new File(basePath + "/students.json");
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Student>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveLibrarians(List<Librarian> librarians) {
        try (FileWriter writer = new FileWriter(basePath + "/librarians.json")) {
            gson.toJson(librarians, writer);
        } catch (IOException e) {
            System.out.println("Error saving librarians: " + e.getMessage());
        }
    }

    @Override
    public List<Librarian> loadLibrarians() {
        File file = new File(basePath + "/librarians.json");
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Librarian>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error loading librarians: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public void saveTrusts(List<Trust> trusts) {
        try (FileWriter writer = new FileWriter(basePath + "/trusts.json")) {
            gson.toJson(trusts, writer);
        } catch (IOException e) {
            System.out.println("Error saving trusts: " + e.getMessage());
        }
    }

    @Override
    public List<Trust> loadTrusts() {
        File file = new File(basePath + "/trusts.json");
        if (!file.exists()) return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Trust>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            System.out.println("Error loading trusts: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}


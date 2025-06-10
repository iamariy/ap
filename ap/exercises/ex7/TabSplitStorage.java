package ap.exercises.ex7;

import ap.exercises.mianterm.Trust;
import ap.exercises.mianterm.Student;
import ap.exercises.mianterm.Book;
import ap.exercises.mianterm.Librarian;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TabSplitStorage implements Storage {
    private final String basePath;

    public TabSplitStorage() {
        this.basePath = "library_data";
        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    public void saveBooks(List<Book> books) {
        try (FileWriter writer = new FileWriter(basePath + "/books.txt")) {
            for (Book book : books) {
                writer.write(book.getName() + "\t" + book.getAuthor() + "\t" +
                        book.getYear() + "\t" + book.getPagecounter() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(basePath + "/books.txt");
        if (!file.exists()) return books;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (parts.length == 4) {
                    books.add(new Book(parts[0], parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (FileWriter writer = new FileWriter(basePath + "/students.txt")) {
            for (Student s : students) {
                writer.write(s.getFirstname() + "\t" + s.getLastname() + "\t" +
                        s.getMajor() + "\t" + s.getPassword() + "\t" +
                        s.getId() + "\t" + s.getRegistrationDate() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @Override
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        File file = new File(basePath + "/students.txt");
        if (!file.exists()) return students;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (parts.length == 6) {
                    students.add(new Student(parts[0], parts[1], parts[2],
                            parts[3], Integer.parseInt(parts[4]),
                            LocalDate.parse(parts[5])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    @Override
    public void saveLibrarians(List<Librarian> librarians) {
        try (FileWriter writer = new FileWriter(basePath + "/librarians.txt")) {
            for (Librarian l : librarians) {
                writer.write(l.getFirstname() + "\t" + l.getLastname() + "\t" +
                        l.getPassword() + "\t" + l.getId() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving librarians: " + e.getMessage());
        }
    }

    @Override
    public List<Librarian> loadLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        File file = new File(basePath + "/librarians.txt");
        if (!file.exists()) return librarians;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (parts.length == 4) {
                    librarians.add(new Librarian(parts[0], parts[1],
                            parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading librarians: " + e.getMessage());
        }
        return librarians;
    }


    @Override
    public void saveTrusts(List<Trust> trusts) {
        try (FileWriter writer = new FileWriter(basePath + "/trusts.txt")) {
            for (Trust t : trusts) {
                writer.write(t.getBook().getName() + "\t" +
                        t.getBook().getAuthor() + "\t" +
                        t.getBook().getYear() + "\t" +
                        t.getBook().getPagecounter() + "\t" +
                        t.getStudent().getFirstname() + "\t" +
                        t.getStudent().getLastname() + "\t" +
                        t.getStudent().getMajor() + "\t" +
                        t.getStudent().getPassword() + "\t" +
                        t.getStudent().getId() + "\t" +
                        t.getStudent().getRegistrationDate() + "\t" +
                        t.getLibrarian().getFirstname() + "\t" +
                        t.getLibrarian().getLastname() + "\t" +
                        t.getLibrarian().getPassword() + "\t" +
                        t.getLibrarian().getId() + "\t" +
                        t.getBorrowDate() + "\t" +
                        t.getReturnDate() + "\t" +
                        (t.getReceivDate() != null ? t.getReceivDate() : "null") + "\t" +
                        t.getDelayDate() + "\t" +
                        t.isBorrowBook() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving trusts: " + e.getMessage());
        }
    }

    @Override
    public List<Trust> loadTrusts() {
        List<Trust> trusts = new ArrayList<>();
        File file = new File(basePath + "/trusts.txt");
        if (!file.exists()) return trusts;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\t");
                if (parts.length == 19) {
                    Book book = new Book(parts[0], parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]));
                    Student student = new Student(parts[4], parts[5],
                            parts[6], parts[7],
                            Integer.parseInt(parts[8]),
                            LocalDate.parse(parts[9]));
                    Librarian librarian = new Librarian(parts[10], parts[11],
                            parts[12],
                            Integer.parseInt(parts[13]));
                    LocalDate borrowDate = LocalDate.parse(parts[14]);
                    LocalDate returnDate = LocalDate.parse(parts[15]);
                    LocalDate receivDate = parts[16].equals("null") ? null : LocalDate.parse(parts[16]);
                    long delayDate = Long.parseLong(parts[17]);
                    boolean isBorrowed = Boolean.parseBoolean(parts[18]);

                    trusts.add(new Trust(book, student, librarian,
                            borrowDate, returnDate,
                            receivDate, delayDate, isBorrowed));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading trusts: " + e.getMessage());
        }
        return trusts;
    }
}
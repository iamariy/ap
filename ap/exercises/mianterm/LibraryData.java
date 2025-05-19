package ap.exercises.mianterm;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryData {
    private final String basePath;

    public LibraryData() {
        this.basePath = "library_data";
        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public void saveBooks(List<Book> books) {
        try {
            FileWriter writer = new FileWriter(basePath + "/books.txt");
            for (Book book : books) {
                String line = book.getName() + "|" + book.getAuthor() + "|" + book.getYear() + "|" + book.getPagecounter();
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        File file = new File(basePath + "/books.txt");

        if (!file.exists()) return books;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String name = parts[0];
                    String author = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int pages = Integer.parseInt(parts[3]);
                    books.add(new Book(name, author, year, pages));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        return books;
    }

    public void saveStudents(List<Student> students) {
        try {
            FileWriter writer = new FileWriter(basePath + "/students.txt");
            for (Student s : students) {
                String line = s.getFirstname() + "|" + s.getLastname() + "|" + s.getMajor() + "|" +
                        s.getPassword() + "|" + s.getId() + "|" + s.getRegistrationDate();
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }


    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(basePath + "/students.txt");

        if (!file.exists()) return students;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String fname = parts[0];
                    String lname = parts[1];
                    String major = parts[2];
                    String pass = parts[3];
                    int id = Integer.parseInt(parts[4]);
                    LocalDate date = LocalDate.parse(parts[5]);
                    students.add(new Student(fname, lname, major, pass, id, date));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }




    public void saveLibrarians(List<Librarian> librarians) {
        try {
            FileWriter writer = new FileWriter(basePath + "/librarians.txt");
            for (Librarian l : librarians) {
                String line = l.getFirstname() + "|" + l.getLastname() + "|" + l.getPassword() + "|" + l.getId();
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving librarians: " + e.getMessage());
        }
    }

    public ArrayList<Librarian> loadLibrarians() {
        ArrayList<Librarian> librarians = new ArrayList<>();
        File file = new File(basePath + "/librarians.txt");

        if (!file.exists()) return librarians;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String fname = parts[0];
                    String lname = parts[1];
                    String pass = parts[2];
                    int id = Integer.parseInt(parts[3]);
                    librarians.add(new Librarian(fname, lname, pass, id));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading librarians: " + e.getMessage());
        }

        return librarians;
    }

    public void saveManager(Manager manager) {
        if (manager == null) return;

        try {
            FileWriter writer = new FileWriter(basePath + "/manager.txt");
            String line = manager.getFirstname() + "|" + manager.getLastname() + "|" + manager.getPassword() + "|" + manager.getEducation() + "|" + manager.getId();
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving manager: " + e.getMessage());
        }
    }

    public Manager loadManager() {
        File file = new File(basePath + "/manager.txt");

        if (!file.exists()) return null;

        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split("\\|");
                if (parts.length == 5) {
                    return new Manager(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading manager: " + e.getMessage());
        }

        return null;
    }

    public void saveTrust(List<Trust> trusts) {
        if (trusts == null) return;

        try (FileWriter writer = new FileWriter(basePath + "/trust.txt")) {
            for (Trust t : trusts) {
                String line = t.getBook().getName() + "|" +
                        t.getBook().getAuthor() + "|" +
                        t.getBook().getYear() + "|" +
                        t.getBook().getPagecounter() + "|" +
                        t.getStudent().getFirstname() + "|" +
                        t.getStudent().getLastname() + "|" +
                        t.getStudent().getMajor() + "|" +
                        t.getStudent().getPassword() + "|" +
                        t.getStudent().getId() + "|" +
                        t.getStudent().getRegistrationDate() + "|" +
                        t.getLibrarian().getFirstname() + "|" +
                        t.getLibrarian().getLastname() + "|" +
                        t.getLibrarian().getPassword() + "|" +
                        t.getLibrarian().getId() + "|" +
                        t.getBorrowDate() + "|" +
                        t.getReturnDate() + "|" +
                        t.getReceivDate() + "|" +
                        t.getDelayDate() + "|" +
                        t.isBorrowBook();

                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving trusts: " + e.getMessage());
        }
    }



    public ArrayList<Trust> loadTrust() {
        ArrayList<Trust> trusts = new ArrayList<>();
        File file = new File(basePath + "/trust.txt");
        if (!file.exists()) return trusts;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] p = scanner.nextLine().split("\\|");
                if (p.length == 19) {
                    Book book = new Book(p[0], p[1], Integer.parseInt(p[2]), Integer.parseInt(p[3]));
                    Student student = new Student(p[4], p[5], p[6], p[7], Integer.parseInt(p[8]), LocalDate.parse(p[9]));
                    Librarian librarian = new Librarian(p[10], p[11], p[12], Integer.parseInt(p[13]));
                    LocalDate borrowDate = LocalDate.parse(p[14]);
                    LocalDate returnDate = LocalDate.parse(p[15]);
                    LocalDate receivDate = p[16].equals("null") ? null : LocalDate.parse(p[16]);
                    long delayDate = Long.parseLong(p[17]);
                    boolean isBorrowed = Boolean.parseBoolean(p[18]);

                    trusts.add(new Trust(book, student, librarian, borrowDate, returnDate, receivDate, delayDate, isBorrowed));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading trusts: " + e.getMessage());
        }

        return trusts;
    }

}

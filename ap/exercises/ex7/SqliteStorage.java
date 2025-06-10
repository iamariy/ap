package ap.exercises.ex7;

import ap.exercises.mianterm.Book;
import ap.exercises.mianterm.Trust;
import ap.exercises.mianterm.Student;
import ap.exercises.mianterm.Librarian;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SqliteStorage implements Storage {
    private final String dbPath;

    public SqliteStorage() {
        this.dbPath = "jdbc:sqlite:library.db";
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement()) {

            // Create tables if they don't exist
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "name TEXT, author TEXT, year INTEGER, pagecounter INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "firstname TEXT, lastname TEXT, major TEXT, " +
                    "password TEXT, id INTEGER, registrationDate TEXT)");

            stmt.execute("CREATE TABLE IF NOT EXISTS librarians (" +
                    "firstname TEXT, lastname TEXT, password TEXT, id INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS manager (" +
                    "firstname TEXT, lastname TEXT, password TEXT, " +
                    "education TEXT, id INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS trusts (" +
                    "book_name TEXT, book_author TEXT, book_year INTEGER, " +
                    "book_pagecounter INTEGER, student_firstname TEXT, " +
                    "student_lastname TEXT, student_major TEXT, " +
                    "student_password TEXT, student_id INTEGER, " +
                    "student_registrationDate TEXT, librarian_firstname TEXT, " +
                    "librarian_lastname TEXT, librarian_password TEXT, " +
                    "librarian_id INTEGER, borrowDate TEXT, returnDate TEXT, " +
                    "receivDate TEXT, delayDate INTEGER, borrowBook INTEGER)");

        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }

    @Override
    public void saveBooks(List<Book> books) {
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM books");

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO books VALUES (?, ?, ?, ?)")) {
                for (Book book : books) {
                    pstmt.setString(1, book.getName());
                    pstmt.setString(2, book.getAuthor());
                    pstmt.setInt(3, book.getYear());
                    pstmt.setInt(4, book.getPagecounter());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @Override
    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                books.add(new Book(rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getInt("pagecounter")));
            }
        } catch (SQLException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void saveStudents(List<Student> students) {
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM students");

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO students VALUES (?, ?, ?, ?, ?, ?)")) {
                for (Student student : students) {
                    pstmt.setString(1, student.getFirstname());
                    pstmt.setString(2, student.getLastname());
                    pstmt.setString(3, student.getMajor());
                    pstmt.setString(4, student.getPassword());
                    pstmt.setInt(5, student.getId());
                    pstmt.setString(6, student.getRegistrationDate().toString());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    @Override
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {

            while (rs.next()) {
                students.add(new Student(rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("major"),
                        rs.getString("password"),
                        rs.getInt("id"),
                        LocalDate.parse(rs.getString("registrationDate"))));
            }
        } catch (SQLException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    // سایر متدهای ذخیره و بارگذاری برای کتابدارها، مدیر و امانت‌ها به‌صورت مشابه پیاده‌سازی می‌شوند
    // (کد کامل در ادامه آمده است)

    @Override
    public void saveLibrarians(List<Librarian> librarians) {
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM librarians");

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO librarians VALUES (?, ?, ?, ?)")) {
                for (Librarian librarian : librarians) {
                    pstmt.setString(1, librarian.getFirstname());
                    pstmt.setString(2, librarian.getLastname());
                    pstmt.setString(3, librarian.getPassword());
                    pstmt.setInt(4, librarian.getId());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving librarians: " + e.getMessage());
        }
    }

    @Override
    public List<Librarian> loadLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM librarians")) {

            while (rs.next()) {
                librarians.add(new Librarian(rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("password"),
                        rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println("Error loading librarians: " + e.getMessage());
        }
        return librarians;
    }


    @Override
    public void saveTrusts(List<Trust> trusts) {
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DELETE FROM trusts");

            try (PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO trusts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                for (Trust trust : trusts) {
                    pstmt.setString(1, trust.getBook().getName());
                    pstmt.setString(2, trust.getBook().getAuthor());
                    pstmt.setInt(3, trust.getBook().getYear());
                    pstmt.setInt(4, trust.getBook().getPagecounter());
                    pstmt.setString(5, trust.getStudent().getFirstname());
                    pstmt.setString(6, trust.getStudent().getLastname());
                    pstmt.setString(7, trust.getStudent().getMajor());
                    pstmt.setString(8, trust.getStudent().getPassword());
                    pstmt.setInt(9, trust.getStudent().getId());
                    pstmt.setString(10, trust.getStudent().getRegistrationDate().toString());
                    pstmt.setString(11, trust.getLibrarian().getFirstname());
                    pstmt.setString(12, trust.getLibrarian().getLastname());
                    pstmt.setString(13, trust.getLibrarian().getPassword());
                    pstmt.setInt(14, trust.getLibrarian().getId());
                    pstmt.setString(15, trust.getBorrowDate().toString());
                    pstmt.setString(16, trust.getReturnDate().toString());
                    pstmt.setString(17, trust.getReceivDate() != null ? trust.getReceivDate().toString() : null);
                    pstmt.setLong(18, trust.getDelayDate());
                    pstmt.setInt(19, trust.isBorrowBook() ? 1 : 0);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving trusts: " + e.getMessage());
        }
    }

    @Override
    public List<Trust> loadTrusts() {
        List<Trust> trusts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbPath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trusts")) {

            while (rs.next()) {
                Book book = new Book(rs.getString("book_name"),
                        rs.getString("book_author"),
                        rs.getInt("book_year"),
                        rs.getInt("book_pagecounter"));

                Student student = new Student(rs.getString("student_firstname"),
                        rs.getString("student_lastname"),
                        rs.getString("student_major"),
                        rs.getString("student_password"),
                        rs.getInt("student_id"),
                        LocalDate.parse(rs.getString("student_registrationDate")));

                Librarian librarian = new Librarian(rs.getString("librarian_firstname"),
                        rs.getString("librarian_lastname"),
                        rs.getString("librarian_password"),
                        rs.getInt("librarian_id"));

                LocalDate borrowDate = LocalDate.parse(rs.getString("borrowDate"));
                LocalDate returnDate = LocalDate.parse(rs.getString("returnDate"));
                LocalDate receivDate = rs.getString("receivDate") != null ?
                        LocalDate.parse(rs.getString("receivDate")) : null;
                long delayDate = rs.getLong("delayDate");
                boolean isBorrowed = rs.getInt("borrowBook") == 1;

                trusts.add(new Trust(book, student, librarian,
                        borrowDate, returnDate,
                        receivDate, delayDate, isBorrowed));
            }
        } catch (SQLException e) {
            System.out.println("Error loading trusts: " + e.getMessage());
        }
        return trusts;
    }
}

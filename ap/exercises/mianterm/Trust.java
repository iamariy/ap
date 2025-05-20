package ap.exercises.mianterm;

import java.time.LocalDate;

public class Trust {
    private Book book;
    private Student student;
    private Librarian librarian;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate receivDate;
    private long delayDate;
    private boolean borrowBook;

    public Trust(Book book,Student student){
        this.book=book;
        this.student=student;
    }

    public Trust(Book book,Student student,Librarian librarian,LocalDate borrowDate,LocalDate returnDate,boolean borrowBook){
        this.book=book;
        this.student=student;
        this.librarian=librarian;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
        this.borrowBook=borrowBook;
    }
    public Trust(Book book,Student student,Librarian librarian,LocalDate borrowDate,LocalDate returnDate,LocalDate receivDate,long delayDate,boolean borrowBook){
        this.book=book;
        this.student=student;
        this.librarian=librarian;
        this.borrowDate=borrowDate;
        this.returnDate=returnDate;
        this.receivDate=receivDate;
        this.delayDate=delayDate;
        this.borrowBook=borrowBook;
    }
    public Book getBook(){
        return book;
    }
    public Student getStudent(){
        return student;
    }
    public Librarian getLibrarian(){
        return librarian;
    }

    public LocalDate getBorrowDate(){
        return borrowDate;
    }
    public LocalDate getReturnDate(){
        return returnDate;
    }
    public long getDelayDate(){
        return delayDate;
    }
    public LocalDate getReceivDate(){
        return receivDate;
    }
    public boolean isBorrowBook(){
        return borrowBook;
    }
    public void setBorrowBook(){
        this.borrowBook=true;
    }

    public String toString(){
        return book.getName() +","+ student.getFirstname() +","+ student.getLastname() +","+ librarian.getFirstname() +","+ librarian.getLastname() +","+ borrowDate +","+ returnDate +","+ receivDate +","+ delayDate +","+ borrowBook;
    }
}

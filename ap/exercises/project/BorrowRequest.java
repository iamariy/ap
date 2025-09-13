package ap.exercises.project;

import java.time.LocalDate;

public class BorrowRequest {
    private Student student;
    private Book book;
    private LocalDate start;
    private LocalDate end;

    public BorrowRequest(Student student,Book book,LocalDate start,LocalDate end){
        this.student=student;
        this.book=book;
        this.start=start;
        this.end=end;
    }

    public BorrowRequest(Student student,Book book){
        this.student=student;
        this.book=book;
    }

    public BorrowRequest(Book book){
        this.book=book;
    }

    public Student getStudent(){
        return student;
    }

    public Book getBook(){
        return book;
    }

    public LocalDate getStart(){
        return start;
    }

    public LocalDate getEnd(){
        return end;
    }

    @Override
    public String toString(){
        return "Students" +student+ "Book Name" +book+ "Start Date" +start+ "End Date" +end;
    }

}

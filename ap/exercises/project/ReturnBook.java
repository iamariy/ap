package ap.exercises.project;

import java.time.LocalDate;

public class ReturnBook {
    private Student student;
    private AcceptBorrow acceptBorrow;
    private LocalDate returnDate;

    public ReturnBook(Student student,AcceptBorrow acceptBorrow,LocalDate returnDate){
        this.student=student;
        this.acceptBorrow=acceptBorrow;
        this.returnDate=returnDate;
    }

    public ReturnBook(Student student,AcceptBorrow acceptBorrow){
        this.student=student;
        this.acceptBorrow=acceptBorrow;
    }

    public Student getStudent(){
        return student;
    }

    public AcceptBorrow getAcceptBorrow(){
        return acceptBorrow;
    }

    public LocalDate getReturnDate(){
        return returnDate;
    }
    @Override
    public String toString(){
        return "Student: " +student+
                " | Borrow: " +acceptBorrow+
                " | Return date: " +returnDate;
    }

}

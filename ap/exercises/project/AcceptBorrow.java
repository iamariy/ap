package ap.exercises.project;

import java.time.LocalDate;

public class AcceptBorrow {
    BorrowRequest borrowRequest;
    Librarian librarian;
    LocalDate startDate;
    LocalDate endDate;

    public AcceptBorrow(BorrowRequest borrowRequest,Librarian librarian,LocalDate startDate,LocalDate endDate){
        this.borrowRequest=borrowRequest;
        this.librarian=librarian;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public BorrowRequest getBorrowRequest(){
        return borrowRequest;
    }

    public Librarian getLibrarian(){
        return librarian;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }
    @Override
    public String toString(){
        return "borrow requests: " +borrowRequest+
                " | librarian: " +librarian+
                " | start dare: " +startDate+
                " | end date: "+ endDate;
    }
}

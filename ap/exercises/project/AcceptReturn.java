package ap.exercises.project;

import java.time.LocalDate;

public class AcceptReturn {
    private ReturnBook returnBook;
    private Librarian librarian;
    private LocalDate returned;

    public AcceptReturn(ReturnBook returnBook,Librarian librarian,LocalDate returned){
        this.returnBook=returnBook;
        this.librarian=librarian;
        this.returned=returned;
    }

    public ReturnBook getReturnBook(){
        return returnBook;
    }

    public Librarian getLibrarian(){
        return librarian;
    }

    public LocalDate getReturned(){
        return returned;
    }
    @Override
    public String toString(){
        return "Return book: " +returnBook+
                " | Librarian: " +librarian+
                " | Return date: " +returned;
    }
}

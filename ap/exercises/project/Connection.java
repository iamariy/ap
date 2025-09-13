package ap.exercises.project;

public interface Connection {

    void addBook(String name,String author,int year,int pagecounter,int count,Librarian librarian);
    void searchBook(String name);
    void print();
    void librarianHistory(String username);
    void delayStudents();
}

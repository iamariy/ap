package ap.exercises.project;

import java.io.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class DataManager {

    String filepath1="student.txt";
    String filepath2="librarian.txt";
    String filepath3="book.txt";
    String filepath4="Requests.txt";
    String filepath5="Accepts.txt";
    String filepath6="All Requests.txt";
    String filepath7="All Accepts.txt";
    String filepath8="Return Request.txt";
    String filepath9="Accept return.txt";

    public void saveStudents(List<Student> students){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath1))){
            for (Student student : students){
                writer.println(student.getName() +","+ student.getStudentId() +","+ student.getUsername() +","+ student.getPassword() +","+ student.isActive());
            }
        } catch (IOException e){
            System.out.println("Error to save students"+ e.getMessage());
        }
    }

    public void loadStudents(List<Student> students){
        File file=new File(filepath1);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==5){
                    students.add(new Student(parts[0].trim(),parts[1].trim(),parts[2].trim(),parts[3].trim(),Boolean.parseBoolean(parts[4].trim())));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveLibrarian(List<Librarian> librarians){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath2))){
            for (Librarian librarian : librarians){
                writer.println(librarian.getUsername() +","+ librarian.getPassword());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadLibrarian(List<Librarian> librarians){
        File file=new File(filepath2);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==2){
                    librarians.add(new Librarian(parts[0].trim(),parts[1].trim()));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest librarians"+ e.getMessage());
        }
    }

    public void saveBooks(List<Book> books){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath3))){
            for (Book book : books){
                writer.println(book.getName() +","+ book.getAuthor() +","+ book.getYear() +","+ book.getPagecounter() +","+ book.getCount() +","+ book.getLibrarian().getUsername());
            }
        } catch (IOException e){
            System.out.println("Error to save books"+ e.getMessage());
        }
    }

    public void loadBooks(List<Book> books){
        File file=new File(filepath3);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==6){
                    Librarian librarian=new Librarian(parts[5].trim());
                    books.add(new Book(parts[0].trim(),parts[1].trim(),Integer.parseInt(parts[2].trim()),Integer.parseInt(parts[3].trim()),Integer.parseInt(parts[4].trim()),librarian));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveRequest(List<BorrowRequest> borrowRequests){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath4))){
            for (BorrowRequest borrowRequest : borrowRequests){
                writer.println(borrowRequest.getStudent().getName() +","+ borrowRequest.getStudent().getStudentId() +","+ borrowRequest.getBook().getName() +","+ borrowRequest.getBook().getAuthor() +","+ borrowRequest.getStart() +","+ borrowRequest.getEnd());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadRequest(List<BorrowRequest> borrowRequests){
        File file=new File(filepath4);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==6){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    borrowRequests.add(new BorrowRequest(student,book, LocalDate.parse(parts[4].trim(),dateTimeFormatter),LocalDate.parse(parts[5].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveAccept(List<AcceptBorrow> acceptBorrows){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath5))){
            for (AcceptBorrow acceptBorrow : acceptBorrows){
                writer.println(acceptBorrow.getBorrowRequest().getStudent().getName() +","+ acceptBorrow.getBorrowRequest().getStudent().getStudentId() +","+ acceptBorrow.getBorrowRequest().getBook().getName() +","+ acceptBorrow.getBorrowRequest().getBook().getAuthor() +","+ acceptBorrow.getLibrarian().getUsername() +","+ acceptBorrow.getStartDate() +","+ acceptBorrow.getEndDate());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadAccept(List<AcceptBorrow> acceptBorrows){
        File file=new File(filepath5);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length>=7){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    BorrowRequest borrowRequest=new BorrowRequest(student,book);
                    Librarian librarian=new Librarian(parts[4].trim());
                    acceptBorrows.add(new AcceptBorrow(borrowRequest,librarian,LocalDate.parse(parts[5].trim(),dateTimeFormatter),LocalDate.parse(parts[6].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveRequestAll(List<BorrowRequest> allRequests){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath6))){
            for (BorrowRequest borrowRequest : allRequests){
                writer.println(borrowRequest.getStudent().getName() +","+ borrowRequest.getStudent().getStudentId() +","+ borrowRequest.getBook().getName() +","+ borrowRequest.getBook().getAuthor() +","+ borrowRequest.getStart() +","+ borrowRequest.getEnd());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void saveAcceptAll(List<AcceptBorrow> allAccept){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath7))){
            for (AcceptBorrow acceptBorrow : allAccept){
                writer.println(acceptBorrow.getBorrowRequest().getStudent().getName() +","+ acceptBorrow.getBorrowRequest().getStudent().getStudentId() +","+ acceptBorrow.getBorrowRequest().getBook().getName() +","+ acceptBorrow.getBorrowRequest().getBook().getAuthor() +","+ acceptBorrow.getLibrarian().getUsername() +","+ acceptBorrow.getStartDate() +","+ acceptBorrow.getEndDate());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadRequestAll(List<BorrowRequest> allRequests){
        File file=new File(filepath6);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==6){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    allRequests.add(new BorrowRequest(student,book, LocalDate.parse(parts[4].trim(),dateTimeFormatter),LocalDate.parse(parts[5].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void loadAcceptAll(List<AcceptBorrow> allAccepts){
        File file=new File(filepath7);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length>=7){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    BorrowRequest borrowRequest=new BorrowRequest(student,book);
                    Librarian librarian=new Librarian(parts[4].trim());
                    allAccepts.add(new AcceptBorrow(borrowRequest,librarian,LocalDate.parse(parts[5].trim(),dateTimeFormatter),LocalDate.parse(parts[6].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveReturn(List<ReturnBook> returnBooks){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath8))){
            for (ReturnBook returnBook : returnBooks){
                writer.println(returnBook.getStudent().getName() +","+ returnBook.getStudent().getStudentId() +","+ returnBook.getAcceptBorrow().getBorrowRequest().getBook().getName() +","+ returnBook.getAcceptBorrow().getBorrowRequest().getBook().getAuthor() +","+ returnBook.getReturnDate());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadReturn(List<ReturnBook> returnBooks){
        File file=new File(filepath8);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==5){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    AcceptBorrow acceptBorrow=new AcceptBorrow(new BorrowRequest(book));
                    returnBooks.add(new ReturnBook(student,acceptBorrow, LocalDate.parse(parts[4].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

    public void saveReturnAccept(List<AcceptReturn> acceptReturns){
        try(PrintWriter writer=new PrintWriter(new FileWriter(filepath9))){
            for (AcceptReturn acceptReturn : acceptReturns){
                writer.println(acceptReturn.getReturnBook().getStudent().getName() +","+ acceptReturn.getReturnBook().getStudent().getStudentId() +","+ acceptReturn.getReturnBook().getAcceptBorrow().getBorrowRequest().getBook().getName() +","+ acceptReturn.getReturnBook().getAcceptBorrow().getBorrowRequest().getBook().getAuthor() +","+ acceptReturn.getLibrarian().getUsername() +","+ acceptReturn.getReturned());
            }
        } catch (IOException e){
            System.out.println("Error to save librarians"+ e.getMessage());
        }
    }

    public void loadReturnAccept(List<AcceptReturn> acceptReturns){
        File file=new File(filepath9);

        if (!file.exists()){
            System.out.println("Not found data");
            return;
        }

        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");


        try (Scanner scanner=new Scanner(file)){
            while (scanner.hasNextLine()){
                String line=scanner.nextLine().trim();
                String[] parts=line.split(",");
                if (parts.length==6){
                    Student student=new Student(parts[0].trim(),parts[1].trim());
                    Book book=new Book(parts[2].trim(),parts[3].trim());
                    ReturnBook returnBook=new ReturnBook(student,new AcceptBorrow(new BorrowRequest(book)));
                    Librarian librarian=new Librarian(parts[4].trim());
                    acceptReturns.add(new AcceptReturn(returnBook,librarian,LocalDate.parse(parts[5].trim(),dateTimeFormatter)));
                }
            }
        } catch (IOException e){
            System.out.println("Error to loadRequest students"+ e.getMessage());
        }
    }

}

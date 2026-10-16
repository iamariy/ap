package ap.exercises.project;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;

public class BookManager implements Connection, HandleData, Getter {
    private List<Book> books;
    private DataManager dataManager;
    private List<BorrowRequest> borrowRequests;   // pending borrow requests
    private List<AcceptBorrow> acceptBorrows;     // currently active loans
    private List<BorrowRequest> allRequests;      // every request ever made (history)
    private List<AcceptBorrow> allAccepts;        // every loan ever accepted (history)
    private List<ReturnBook> returnBooks;         // pending return requests
    private List<AcceptReturn> acceptReturns;     // every accepted return (history)
    private Scanner scanner;

    public BookManager(Scanner scanner) {
        this.books = new ArrayList<>();
        this.dataManager = new DataManager();
        this.borrowRequests = new ArrayList<>();
        this.acceptBorrows = new ArrayList<>();
        this.allRequests = new ArrayList<>();
        this.allAccepts = new ArrayList<>();
        this.returnBooks = new ArrayList<>();
        this.acceptReturns = new ArrayList<>();
        this.scanner = scanner;
    }

    @Override
    public void addBook(String name, String author, int year, int pagecounter, int count, Librarian librarian) {
        books.add(new Book(name, author, year, pagecounter, count, librarian));
        System.out.println("Book \"" + name + "\" added successfully!");
    }

    @Override
    public void saveBooks() {
        dataManager.saveBooks(books);
    }

    @Override
    public void loadBooks() {
        dataManager.loadBooks(books);
    }

    @Override
    public void loadRequest() {
        dataManager.loadRequest(borrowRequests);
    }

    @Override
    public void loadAccept() {
        dataManager.loadAccept(acceptBorrows);
    }

    @Override
    public void loadAllR() {
        dataManager.loadRequestAll(allRequests);
    }

    @Override
    public void loadAllA() {
        dataManager.loadAcceptAll(allAccepts);
    }

    @Override
    public void loadReturn() {
        dataManager.loadReturn(returnBooks);
    }

    @Override
    public void loadAcceptReturn() {
        dataManager.loadReturnAccept(acceptReturns);
    }

    @Override
    public void searchBook(String name) {
        for (Book book : books) {
            if (name.equals(book.getName())) {
                System.out.println("Name: " + book.getName()
                        + " | Author: " + book.getAuthor()
                        + " | Year: " + book.getYear()
                        + " | Page count: " + book.getPagecounter());
                return;
            }
        }
        System.out.println("Book is not found");
    }

    public void editName(String name, String newName) {
        editMatchingBooks(name, "name", book -> book.setName(newName));
    }

    public void editAuthor(String name, String newAuthor) {
        editMatchingBooks(name, "author", book -> book.setAuthor(newAuthor));
    }

    public void editYear(String name, int newYear) {
        editMatchingBooks(name, "year", book -> book.setYear(newYear));
    }

    public void editPage(String name, int newPageCount) {
        editMatchingBooks(name, "page count", book -> book.setPagecounter(newPageCount));
    }

    public void editCount(String name, int newCount) {
        editMatchingBooks(name, "count", book -> book.setCount(newCount));
    }

    private void editMatchingBooks(String name, String field, Consumer<Book> change) {
        int updated = 0;
        for (Book book : books) {
            if (name.equals(book.getName())) {
                change.accept(book);
                updated++;
            }
        }
        if (updated > 0) {
            System.out.println(updated + " book(s) updated (" + field + ")!");
            saveBooks();
        } else {
            System.out.println("Book is not found");
        }
    }

    public Book isAvailable(String searchTerm) {
        for (Book book : books) {
            if (searchTerm.equals(book.getName())
                    || searchTerm.equals(book.getAuthor())
                    || searchTerm.equals(Integer.toString(book.getYear()))) {

                System.out.println("Book name: " + book.getName()
                        + " | Author: " + book.getAuthor()
                        + " | Year: " + book.getYear()
                        + " | Page count: " + book.getPagecounter());

                if (book.getCount() > 0) {
                    System.out.println("Book is available!");
                    return book;
                }
                System.out.println("Book is not available!");
                return null;
            }
        }
        System.out.println("Book is not registered!");
        return null;
    }

    public void borrowBook(Student student, LocalDate start) {
        if (!student.isActive()) {
            System.out.println("You can't borrow a book because your account is deactivated.");
            return;
        }

        String name = InputHelper.readField(scanner, "Enter book name: ");
        Book book = isAvailable(name);
        if (book == null) {
            return;
        }

        BorrowRequest borrow = new BorrowRequest(student, book, start, start.plusDays(1));
        borrowRequests.add(borrow);
        allRequests.add(borrow);
        System.out.println("Borrow request sent.");
        dataManager.saveRequest(borrowRequests);
        dataManager.saveRequestAll(allRequests);
    }

    public void accept(Librarian librarian, LocalDate startDate) {
        if (borrowRequests.isEmpty()) {
            System.out.println("No borrow requests to review.");
            return;
        }

        LocalDate endDate = startDate.plusDays(20);

        Iterator<BorrowRequest> iterator = borrowRequests.iterator();
        while (iterator.hasNext()) {
            BorrowRequest borrow = iterator.next();
            boolean started = !startDate.isBefore(borrow.getStart());
            boolean notExpired = !startDate.isAfter(borrow.getEnd());
            if (!started || !notExpired) {
                continue;
            }

            System.out.println(borrow.getStudent().getName() + ", " + borrow.getStudent().getStudentId()
                    + ", " + borrow.getBook().getName() + ", " + borrow.getBook().getAuthor());
            System.out.println("Do you want to accept it? (y/n)");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                iterator.remove();
                AcceptBorrow accept = new AcceptBorrow(borrow, librarian, startDate, endDate);
                acceptBorrows.add(accept);
                allAccepts.add(accept);
                for (Book book : books) {
                    if (book.getName().equals(borrow.getBook().getName())
                            && book.getAuthor().equals(borrow.getBook().getAuthor())) {
                        book.setCount(book.getCount() - 1);
                    }
                }
                System.out.println("Request accepted.");
            } else if (input.equalsIgnoreCase("n")) {
                iterator.remove();
                System.out.println("Request rejected.");
            } else {
                System.out.println("Invalid choice! Please answer y or n.");
            }
        }

        saveBooks();
        dataManager.saveRequest(borrowRequests);
        dataManager.saveAccept(acceptBorrows);
        dataManager.saveAcceptAll(allAccepts);
    }

    public void returnBook(Student student, LocalDate returnDate) {
        String name = InputHelper.readField(scanner, "Enter book name: ");

        for (ReturnBook pending : returnBooks) {
            if (pending.getStudent().getStudentId().equals(student.getStudentId())
                    && pending.getAcceptBorrow().getBorrowRequest().getBook().getName().equals(name)) {
                System.out.println("You already have a pending return request for this book.");
                return;
            }
        }

        for (AcceptBorrow accept : acceptBorrows) {
            BorrowRequest borrow = accept.getBorrowRequest();
            if (borrow.getBook().getName().equals(name)
                    && borrow.getStudent().getStudentId().equals(student.getStudentId())) {
                returnBooks.add(new ReturnBook(student, accept, returnDate));
                dataManager.saveReturn(returnBooks);
                System.out.println("Return request sent.");
                return;
            }
        }

        System.out.println("No active loan of this book was found for your account.");
    }

    public void acceptReturn(Librarian librarian, LocalDate date) {
        if (returnBooks.isEmpty()) {
            System.out.println("No return requests to review.");
            return;
        }

        Iterator<ReturnBook> iterator = returnBooks.iterator();
        while (iterator.hasNext()) {
            ReturnBook returnRequest = iterator.next();
            BorrowRequest borrow = returnRequest.getAcceptBorrow().getBorrowRequest();

            System.out.println(returnRequest.getStudent().getName() + ", " + returnRequest.getStudent().getStudentId()
                    + ", " + borrow.getBook().getName() + ", " + borrow.getBook().getAuthor());
            System.out.println("Do you want to accept this return? (y/n)");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                iterator.remove();
                acceptReturns.add(new AcceptReturn(returnRequest, librarian, date));
                removeActiveLoan(returnRequest);
                for (Book book : books) {
                    if (book.getName().equals(borrow.getBook().getName())
                            && book.getAuthor().equals(borrow.getBook().getAuthor())) {
                        book.setCount(book.getCount() + 1);
                    }
                }
                System.out.println("Return accepted.");
            } else if (input.equalsIgnoreCase("n")) {
                iterator.remove();
                System.out.println("Return rejected.");
            } else {
                System.out.println("Invalid choice! Please answer y or n.");
            }
        }

        saveBooks();
        dataManager.saveReturn(returnBooks);
        dataManager.saveReturnAccept(acceptReturns);
        dataManager.saveAccept(acceptBorrows);
    }

    private void removeActiveLoan(ReturnBook returnRequest) {
        String studentId = returnRequest.getStudent().getStudentId();
        BorrowRequest borrow = returnRequest.getAcceptBorrow().getBorrowRequest();

        Iterator<AcceptBorrow> iterator = acceptBorrows.iterator();
        while (iterator.hasNext()) {
            BorrowRequest activeBorrow = iterator.next().getBorrowRequest();
            boolean sameStudent = activeBorrow.getStudent().getStudentId().equals(studentId);
            boolean sameBook = activeBorrow.getBook().getName().equals(borrow.getBook().getName())
                    && activeBorrow.getBook().getAuthor().equals(borrow.getBook().getAuthor());
            if (sameStudent && sameBook) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public int getBookCount() {
        int sum = 0;
        for (Book book : books) {
            sum += book.getCount();
        }
        return sum;
    }

    @Override
    public int getLoanCount() {
        return allAccepts.size();
    }

    @Override
    public int activeLoans() {
        return acceptBorrows.size();
    }

    @Override
    public void print() {
        for (AcceptBorrow accept : allAccepts) {
            BorrowRequest borrow = accept.getBorrowRequest();
            System.out.println("Student name: " + borrow.getStudent().getName()
                    + " | Student ID: " + borrow.getStudent().getStudentId()
                    + " | Book: " + borrow.getBook().getName()
                    + " | Author: " + borrow.getBook().getAuthor());
        }
    }

    @Override
    public int delay() {
        int count = 0;
        for (AcceptBorrow accept : allAccepts) {
            AcceptReturn matchingReturn = findMatchingReturn(accept);
            if (matchingReturn != null
                    && ChronoUnit.DAYS.between(matchingReturn.getReturned(), accept.getEndDate()) < 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void librarianHistory(String username) {
        int addedBooks = 0;
        int acceptedBorrows = 0;
        int acceptedReturns = 0;

        for (Book book : books) {
            if (book.getLibrarian() != null && book.getLibrarian().getUsername().equals(username)) {
                addedBooks += book.getCount();
            }
        }
        for (AcceptBorrow accept : allAccepts) {
            if (accept.getLibrarian().getUsername().equals(username)) {
                acceptedBorrows++;
            }
        }
        for (AcceptReturn acceptReturn : acceptReturns) {
            if (acceptReturn.getLibrarian().getUsername().equals(username)) {
                acceptedReturns++;
            }
        }
        System.out.println("Count of added books: " + addedBooks
                + "\nAll accepted borrows: " + acceptedBorrows
                + "\nAll accepted returns: " + acceptedReturns);
    }

    @Override
    public int requestCounter() {
        return allRequests.size();
    }

    @Override
    public long daysCounter() {
        long total = 0;
        for (AcceptBorrow accept : allAccepts) {
            AcceptReturn matchingReturn = findMatchingReturn(accept);
            if (matchingReturn != null) {
                total += Math.abs(ChronoUnit.DAYS.between(matchingReturn.getReturned(), accept.getStartDate()));
            }
        }
        return total;
    }

    @Override
    public void delayStudents() {
        Map<String, Long> lateDaysById = new HashMap<>();
        Map<String, String> nameById = new HashMap<>();

        for (AcceptBorrow accept : allAccepts) {
            AcceptReturn matchingReturn = findMatchingReturn(accept);
            if (matchingReturn == null) {
                continue;
            }
            long daysLate = ChronoUnit.DAYS.between(accept.getEndDate(), matchingReturn.getReturned());
            if (daysLate > 0) {
                Student student = accept.getBorrowRequest().getStudent();
                lateDaysById.merge(student.getStudentId(), daysLate, Long::sum);
                nameById.putIfAbsent(student.getStudentId(), student.getName());
            }
        }

        List<Map.Entry<String, Long>> top10 = lateDaysById.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .toList();

        System.out.println("Top 10 late students:");
        for (Map.Entry<String, Long> entry : top10) {
            System.out.println(nameById.get(entry.getKey()) + " (ID: " + entry.getKey()
                    + ") → Total late days: " + entry.getValue());
        }
    }

    private AcceptReturn findMatchingReturn(AcceptBorrow accept) {
        BorrowRequest borrow = accept.getBorrowRequest();
        for (AcceptReturn acceptReturn : acceptReturns) {
            ReturnBook returnBook = acceptReturn.getReturnBook();
            BorrowRequest returnedBorrow = returnBook.getAcceptBorrow().getBorrowRequest();
            boolean sameStudent = returnBook.getStudent().getStudentId().equals(borrow.getStudent().getStudentId());
            boolean sameBook = returnedBorrow.getBook().getName().equals(borrow.getBook().getName())
                    && returnedBorrow.getBook().getAuthor().equals(borrow.getBook().getAuthor());
            if (sameStudent && sameBook) {
                return acceptReturn;
            }
        }
        return null;
    }
}

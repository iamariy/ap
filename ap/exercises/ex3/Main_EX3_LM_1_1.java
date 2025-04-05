package ap.exercises.ex3;

public class Main_EX3_LM_1_1 {
    public static void main(String[] args)
    {
        Book book1=new Book("Cosette","Victor Hugo",582,1862);
        Book book2=new Book("Anne Shirley","Lucy Maund Montgomery",3544,1908);

        Student student1=new Student("Maryam","Rezaei","Computer",233512);
        Student student2=new Student("Sama","Zolfkhani","Computer",468370);

        System.out.println("Book1:"+ book1.bookname +","+ book1.authornam +","+ book1.counter +","+ book1.year);
        System.out.println("Book2:"+ book2.bookname +","+ book2.authornam +","+ book2.counter +","+ book2.year);
        System.out.println("Student1:"+ student1.firstname +","+ student1.lastname +","+ student1.major +","+ student1.id);
        System.out.println("Student2:"+ student2.firstname +","+ student2.lastname +","+ student2.major +","+ student2.id);

        book1.setBookName("Harry Potter");
        book1.setAuthorName("Joanna Rowling");
        book1.setCounter(15784);
        book1.setYear(1997);

        student1.setFirstName("Neda");
        student1.setLastName("Rashtchi");
        student1.setMajor("Computer");
        student1.setId(245678);

        System.out.println("Book1:"+ book1.bookname +","+ book1.authornam +","+ book1.counter +","+ book1.year);
        System.out.println("Student1:"+ student1.firstname +","+ student1.lastname +","+ student1.major +","+ student1.id);


    }
}
class Book
{
    String bookname;
    String authornam;
    int counter;
    int year;

    public Book(String bookname,String authornam,int counter,int year)
    {
        this.bookname=bookname;
        this.authornam=authornam;
        this.counter=counter;
        this.year=year;
    }

    public void setBookName(String bookName)
    {
        this.bookname = bookName;
    }
    public void setAuthorName(String authorName)
    {
        this.authornam = authorName;
    }
    public void setCounter(int counter)
    {
        this.counter = counter;
    }
    public void setYear(int year)
    {
        this.year = year;
    }
}
class Student
{
    String firstname;
    String lastname;
    String major;
    int id;

    public Student(String firstname,String lastname,String major,int id)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.major=major;
        this.id=id;
    }

    public void setFirstName(String firstName)
    {
        this.firstname = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastname= lastName;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setMajor(String major)
    {
        this.major = major;
    }
}

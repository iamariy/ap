package ap.exercises.FinalExam;

public class Book extends Product{
    private String title;
    private String author;

    public Book(String name,int price,String title,String author){
        super(name,price);
        this.title=title;
        this.author=author;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }


    @Override
    public String toString(){
        return "name is:" +super.getName()+ "price is:" +super.getPrice()+ "title is:" +getTitle()+ "author is:" +getAuthor();
    }
}

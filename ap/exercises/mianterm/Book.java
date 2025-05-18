package ap.exercises.mianterm;

public class Book {
    private String name;
    private String author;
    private int year;
    private int pagecounter;

    public Book(String name, String author, int year, int pagecounter) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.pagecounter = pagecounter;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
    public int getYear(){
        return year;
    }
    public int getPagecounter(){
        return pagecounter;
    }
    @Override
    public String toString(){
        return getName() +","+
                getAuthor()+","+ getYear() +","+ getPagecounter();
    }
    public void print(){
        System.out.println("Name:" +getName()+ "\tauthor:" +getAuthor()+ "\tyear:" +getYear()+ "\tpagecounter:" +getPagecounter());
    }
}

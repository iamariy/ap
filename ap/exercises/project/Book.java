package ap.exercises.project;

public class Book {
    private String name;
    private String author;
    private int year;
    private int pagecounter;
    private int count;

    public Book(String name, String author, int year,int pagecounter,int count){
        this.name=name;
        this.author=author;
        this.year=year;
        this.pagecounter=pagecounter;
        this.count=count;
    }

    public String getName(){
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public int getYear(){
        return year;
    }

    public int getPagecounter(){
        return pagecounter;
    }

    public int getCount(){
        return count;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public void setYear(int year){
        this.year=year;
    }

    public void setPagecounter(int pagecounter){
        this.pagecounter=pagecounter;
    }

    @Override
    public String toString(){
        return "name" +name+ "author" +author+ "year" +year+ "pagecounter" +pagecounter;
    }

}

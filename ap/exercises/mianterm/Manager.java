package ap.exercises.mianterm;

public class Manager extends Person {
    private String firstname;
    private String lastname;
    private String password;
    private String education;
    private int id;

    public Manager(String firstname,String lastname,String password,String education,int id){
        super(firstname,lastname,1);
        this.password=password;
        this.education=education;
    }


    public String getPassword(){
        return password;
    }
    public String getEducation(){
        return education;
    }

    public void addLibraian(Library library,String firstname,String lastname,String password,int id){
        Librarian librarian=new Librarian(firstname,lastname,password,id);
        library.addLibrarian(librarian);
    }
}
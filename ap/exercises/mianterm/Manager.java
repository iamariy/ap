package ap.exercises.mianterm;

public class Manager extends Person {
    private String password;
    private String education;


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

    public void addLibrarian(Library library, String firstname, String lastname, String password, int id) {
        Librarian librarian=new Librarian(firstname,lastname,password,id);
        library.addLibrarian(librarian);
        library.setLibrarians(library.getLibrarians());
    }

}
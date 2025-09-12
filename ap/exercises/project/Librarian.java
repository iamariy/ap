package ap.exercises.project;

public class Librarian extends Person{

    public Librarian(String userename,String password){
        super(userename,password);
    }


    @Override
    public String toString(){
        return "username: " +getUsername()+
                " | password: " +getPassword();
    }
}

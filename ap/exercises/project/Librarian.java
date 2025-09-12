package ap.exercises.project;

public class Librarian extends Person{

    public Librarian(String userename,String password){
        super(userename,password);
    }

    public Librarian(String username){
        super(username,"2222");
    }


    @Override
    public String toString(){
        return "username: " +getUsername()+
                " | password: " +getPassword();
    }
}

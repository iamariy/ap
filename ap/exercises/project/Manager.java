package ap.exercises.project;

public class Manager extends Person{

    public Manager(String username,String password){
        super(username,password);
    }

    public Manager(){
        super("Manager","1");
    }

    @Override
    public String toString(){
        return "username: " +getUsername()+
                " |password: " +getPassword();
    }
}

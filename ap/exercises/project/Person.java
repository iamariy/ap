package ap.exercises.project;

public class Person implements LogOut{
    protected String username;
    protected String password;

    public Person(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public void setUsername(String username){
        this.username=username;
    }
    @Override
    public void setPassword(String password){
        this.password=password;
    }
    @Override
    public String toString(){
        return "username" +username+ "password" +password;
    }

}

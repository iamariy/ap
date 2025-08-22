package ap.exercises.project;

public class Manager {
    private String username;
    private String password;

    public Manager(String username,String password){
        this.username="manager";
        this.password="1";
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString(){
        return "username" +username+ "password" +password;
    }
}

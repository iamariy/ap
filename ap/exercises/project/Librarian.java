package ap.exercises.project;

public class Librarian {
    private String usrename;
    private String password;

    public Librarian(String usrename,String password){
        this.usrename=usrename;
        this.password=password;
    }

    public String getUsrename(){
        return usrename;
    }

    public String getPassword(){
        return password;
    }

    public void setUsrename(String usrename){
        this.usrename=usrename;
    }

    public void setPassword(String password){
        this.password=password;
    }

    @Override
    public String toString(){
        return "username" +usrename+ "password" +password;
    }
}

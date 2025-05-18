package ap.exercises.mianterm;

public class Person {
    protected String firstname;
    protected String lastname;
    protected int id;

    public Person(String firstname,String lastname,int id){
        this.firstname=firstname;
        this.lastname=lastname;
        this.id=id;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public int getId(){
        return id;
    }
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }
}

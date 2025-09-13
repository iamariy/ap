package ap.exercises.project;

public class Student extends Person{
    private String name;
    private String studentId;
    private boolean active;

    public Student(String name, String studentId, String username, String password,boolean active) {
        super(username,password);
        this.name = name;
        this.studentId = studentId;
        this.active=active;
    }

    public Student(String name,String studentId){
        super("Maryam","1111");
        this.name=name;
        this.studentId=studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + getUsername() +
                " | password: " + getPassword() +
                " | active: " +isActive();
    }

}

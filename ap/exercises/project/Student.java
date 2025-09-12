package ap.exercises.project;

public class Student extends Person{
    private String name;
    private String studentId;

    public Student(String name, String studentId, String username, String password) {
        super(username,password);
        this.name = name;
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + getUsername() +
                " | password: " + getPassword();
    }
}

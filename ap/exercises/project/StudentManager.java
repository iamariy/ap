package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students;
    private DataManager dataManager;
    private BorrowRequest borrowRequest;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.dataManager=new DataManager();
    }

    public StudentManager(BorrowRequest borrowRequest){
        this.borrowRequest=borrowRequest;
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Student newStudent = new Student(name, studentId, username, password,true);
        students.add(newStudent);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateStudent(String username, String password) {
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }


    private boolean isUsernameTaken(String username) {
        return students.stream().anyMatch(s -> s.getUsername().equals(username));
    }

    public int getStudentCount() {
        return students.size();
    }

    public void saving(){
        dataManager.saveStudents(students);
    }

    public void loading(){
        dataManager.loadStudents(students);
    }

    public void changeActiving(String studentId) {
        Scanner scanner = new Scanner(System.in);
        boolean found = false;

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                found = true;
                System.out.println(student.isActive());

                if (student.isActive()) {
                    System.out.println("Do you want to change to di active? (y or n)?");
                    String input = scanner.nextLine();
                    if (input.equals("y")) {
                        student.setActive(false);
                        saving();
                        System.out.println("Student activity is changed to di active");
                    } else if (input.equals("n")) {
                        System.out.println("Student remains active");
                    } else {
                        System.out.println("Invalid choice! Please try again");
                    }
                } else {
                    System.out.println("Do you want to change to active? (y or n)?");
                    String input = scanner.nextLine();
                    if (input.equals("y")) {
                        student.setActive(true);
                        saving();
                        System.out.println("Student activity is changed to active");
                    } else if (input.equals("n")) {
                        System.out.println("Student remains inactive");
                    } else {
                        System.out.println("Invalid choice! Please try again");
                    }
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Student ID is incorrect");
        }
    }

}
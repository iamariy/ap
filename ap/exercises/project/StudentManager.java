package ap.exercises.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private List<Student> students;
    private DataManager dataManager;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.dataManager = new DataManager();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        students.add(new Student(name, studentId, username, password, true));
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

    public void saving() {
        dataManager.saveStudents(students);
    }

    public void loading() {
        dataManager.loadStudents(students);
    }

    public void changeActiving(String studentId, Scanner scanner) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println("Current status: " + (student.isActive() ? "active" : "inactive"));
                System.out.println("Do you want to change it? (y/n)");
                String input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("y")) {
                    student.setActive(!student.isActive());
                    saving();
                    System.out.println("Student is now " + (student.isActive() ? "active" : "inactive"));
                } else {
                    System.out.println("Student status unchanged.");
                }
                return;
            }
        }
        System.out.println("Student ID is incorrect");
    }
}


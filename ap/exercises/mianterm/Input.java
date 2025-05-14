package ap.exercises.mianterm;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String inputStringNmae() {
        System.out.println("Enter name:");
        return scanner.nextLine();
    }

    public String inputStringLast() {
        System.out.println("Enter lastname:");
        return scanner.nextLine();
    }

    public String inputStringMajor() {
        System.out.println("Enter major");
        return scanner.nextLine();
    }

    public String inputStringPassword() {
        System.out.println("Enter password:");
        return scanner.nextLine();
    }

    public String inputStringAuthor() {
        System.out.println("Enter author:");
        return scanner.nextLine();
    }

    public int inputIntegerId() {
        System.out.println("Enter id:");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a number.");
            return inputIntegerId();
        }
    }

    public int inputIntegerYear() {
        System.out.println("Enter year:");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a number.");
            return inputIntegerYear();
        }
    }

    public int inputIntegerPage() {
        System.out.println("Enter page:");
        try {
            return Integer.parseInt(scanner.nextLine()); // خواندن به صورت خط کامل
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a number.");
            return inputIntegerPage();
        }
    }
    public int inputInteger() {
        System.out.println("Enter number:");
        {
            try {
                return Integer.parseInt(scanner.nextLine()); // خواندن به صورت خط کامل
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID! Please enter a number.");
                return inputInteger();
            }
        }
    }
    public void close(){
        scanner.close();
    }
}

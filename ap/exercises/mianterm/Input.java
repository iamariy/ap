package ap.exercises.mianterm;

import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input(Scanner scanner) {
        this.scanner = scanner;
    }

    public Input( ) {
        this.scanner = new Scanner(System.in);
    }

    public String inputString(){
        return scanner.nextLine();
    }

    public int inputInteger() {
        {
            try {
                return Integer.parseInt(scanner.nextLine());
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

package ap.exercises.midtermexam;

import java.util.Scanner;

public class input {
    private Scanner scanner;
    public input(Scanner scanner){
        this.scanner=scanner;
    }
    public Scanner getScanner(){
        return scanner;
    }
    public String inputString(){
        return scanner.nextLine();
    }

}

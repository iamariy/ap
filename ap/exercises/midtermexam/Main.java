package ap.exercises.midtermexam;

import ap.exercises.mianterm.Input;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Input input= new Input(scanner);
        ArrayList<Shop> shop=new ArrayList<>();
        Laptop laptop=new Laptop("Asus",10000,"5",8);
        Case acase=new Case("intel",444,"black",5);
        Shop shops=new Shop(laptop,acase);
        shop.add(shops);
        for (Shop shop1 : shop){
            System.out.println(shop1.getLaptop());
            System.out.println(shop1.getaCase());
        }
        String s= input.inputString();
        for (Shop shop1 : shop){
            if (s.equals(shop1.getLaptop().getBrand())) System.out.println(shop1.getLaptop());
            else if (s.equals(shop1.getaCase().getBrand())) {
                System.out.println(shop1.getaCase());
            } else System.out.println("not found");
        }
    }
}

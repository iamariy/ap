import java.util.ArrayList;

public class Main1 {
    public static void main(String[] args){
        ArrayList<Pen> pens=new ArrayList<>();
        Pen p1=new Pen("red","bik",1000);
        Pen p2=new Pen("blue","bik",1000);
        pens.add(p1);
        pens.add(p2);
        ArrayList<Book> books=new ArrayList<>();
        Book b1=new Book("math",100000);
        Book b2=new Book("English",10000);
        b1.raiseSalary(10);
        b2.raiseSalary(20);
        p1.raiseSalary(5);
        p2.raiseSalary(5);
        books.add(b1);
        books.add(b2);
        for (Book b : books){
            System.out.println("Book:\nName is:"+b.getName()+"\nPrice is:"+b.getPrice());
        }
        for (Pen p : pens){
            System.out.println("Pen:\nColor is:"+p.getColor()+"\nBrand is:"+p.getBrand()+"\nPrice is"+p.getPrice());
        }
    }
}

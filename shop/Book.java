package shop;

public class Book {
    private String name;
    private double price;

    public Book(String name,double price){
        this.name=name;
        this.price=price;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public void raiseSalary(double percent){
        price-=price*(percent/100);
    }
}

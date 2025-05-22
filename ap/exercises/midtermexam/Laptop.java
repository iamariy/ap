package ap.exercises.midtermexam;

public class Laptop extends Vasile{
    private String corei;
    private int ram;
    public Laptop(String brand,int price,String corei,int ram){
        super(brand,price);
        this.corei=corei;
        this.ram=ram;
    }
    public String getCorei(){
        return corei;
    }
    public int getRam(){
        return ram;
    }
    @Override
    public String toString(){
        return getBrand() +","+ getPrice() +","+ getCorei() +","+ getRam();
    }
}

package ap.exercises.midtermexam;

public class Case extends Vasile{
    private String color;
    private int size;
    public Case(String brand,int price,String color,int size){
        super(brand,price);
        this.color=color;
        this.size=size;
    }
    public String getColor(){
        return color;
    }
    public int getSize(){
        return size;
    }
    @Override
    public String toString(){
        return getBrand() +","+ getPrice() +","+ getColor() +","+ getSize();
    }
}

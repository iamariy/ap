package ap.exercises.FinalExam;

public class Pen extends Product{
    private String color;
    private int count;

    public Pen(String name,int price,String color){
        super(name,price);
        this.color=color;
    }

    public String getColor(){
        return color;
    }

    @Override
    public String toString(){
        return "name is:" +super.getName()+ "price is:" +super.getPrice()+ "color is:" +getColor();
    }
}


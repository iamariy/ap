public class Pen {
    private String color;
    private String brand;
    private double price;

    public Pen(String color, String brand, double price) {
        this.color = color;
        this.brand = brand;
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
    public void raiseSalary(double percent){
        price-=price*(percent/100);
    }
}

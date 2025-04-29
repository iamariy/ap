package ap.exercises.ex4;

import java.util.ArrayList;

public class CashRegister {
    private ArrayList<Double> part;

    public CashRegister(){
        part=new ArrayList<Double>();
    }
    public void add(double price){
        part.add(price);
    }
    public double getTotal(){
        double total=0;
        for (int i=0;i<part.size();i++){
            total+=part.get(i);
        }
        return total;
    }
    public void printPrice(){
        String prices="";
        for (int i=0;i<part.size();i++){
            double price=part.get(i);
            prices=prices.concat(String.valueOf("part"+ (i+1) +":"+ price +"\n"));
        }
        prices=prices.concat(String.valueOf("total:"+ getTotal()));
        System.out.println(prices);
    }
}

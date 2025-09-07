package ap.exercises.FinalExam;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products=new ArrayList<>();

        products.add(new Book("algebra",1000,"math","fcf"));
        products.add(new Book("algebra",1000,"math","fcf"));
        products.add(new Pen("Bik",34,"green"));
        products.add(new Pen("Bik",1000,"green"));


        ProductTool.removing(products);

    }
}

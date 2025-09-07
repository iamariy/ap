package ap.exercises.FinalExam;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductTool {

    private Book book;
    private Pen pen;

    public ProductTool(){
        this.book=book;
        this.pen=pen;
    }

    public static List<Product> removing(List<Product> products) {
        for (int i=0;i<products.size();i++){
            for (int j=i+1;j<products.size();j++){
                if (products.get(i).getName().equals(products.get(j).getName()) && products.get(i).getPrice()==products.get(j).getPrice()){
                    products.remove(j);
                    j--;
                }
            }
        }
        List<Product> productList=products.stream().sorted(Comparator.comparingInt(Product ::getPrice).reversed().thenComparing(p -> (p instanceof Book) ? -1 : 1)).collect(Collectors.toList());

        System.out.println(productList);

        return productList;
    }



}


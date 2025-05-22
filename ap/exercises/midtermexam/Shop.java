package ap.exercises.midtermexam;

public class Shop {
    private Laptop laptop;
    private Case aCase;

    public Shop(Laptop laptop,Case aCase){
        this.laptop=laptop;
        this.aCase=aCase;
    }


    public Laptop getLaptop(){
        return laptop;
    }
    public Case getaCase(){
        return aCase;
    }

}

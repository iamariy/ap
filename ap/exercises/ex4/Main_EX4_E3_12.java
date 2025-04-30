package ap.exercises.ex4;

public class Main_EX4_E3_12 {
    private String name;
    private double salary;

    public Main_EX4_E3_12(String name, double salary){
        this.name=name;
        this.salary=salary;
    }
    public String getName(){
        return name;
    }
    public double getSalary(){
        return salary;
    }
    public void raiseSalary(double byPercent){
        salary+=salary*(byPercent/100);
    }

    public static void main(String[] args){
        Main_EX4_E3_12 harry = new Main_EX4_E3_12("Hacker, Harry", 50000);
        System.out.println("Name:"+ harry.getName() +"\nSalary:"+ harry.getSalary());

        harry.raiseSalary(10);
        System.out.println("Percent of salary:"+ harry.getSalary());
    }
}
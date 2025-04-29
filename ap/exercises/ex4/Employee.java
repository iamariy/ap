package ap.exercises.ex4;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name,double salary){
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
}
class EmployeeTester{
    public static void main(String[] args){
        Employee harry = new Employee("Hacker, Harry", 50000);
        System.out.println("Name:"+ harry.getName() +"\nSalary:"+ harry.getSalary());

        harry.raiseSalary(10);
        System.out.println("Percent of salary:"+ harry.getSalary());
    }
}
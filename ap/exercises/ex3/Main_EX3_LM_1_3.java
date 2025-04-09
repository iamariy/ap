package ap.exercises.ex3;

public class Main_EX3_LM_1_3 {
    public static void main(String[] args)
    {
        Students student1= new Students("Maryam","Rezaei","Computer",233512);
        Students student2= new Students("Sama","Zolfkhani","Computer",468370);

        Students[] students={student1,student2};

        Students found= search(students,student1.firstname,student1.lastname);

        if (found!=null)
        {
            System.out.println("Student is found.\n"+ found);
        }
        else System.out.println("Student id not found");


    }

    public static Students search(Students[] students, String firstname, String lastname)
    {
        for (int i = 0; i < students.length; i++)
        {
            if (students[i].firstname.equalsIgnoreCase(firstname) && students[i].lastname.equalsIgnoreCase(lastname))
            {
                return students[i];
            }
        }
        return null;
    }
}

class Students
{
    String firstname;
    String lastname;
    String major;
    int id;

    public Students(String firstname, String lastname, String major, int id)
    {
        this.firstname=firstname;
        this.lastname=lastname;
        this.major=major;
        this.id=id;
    }

    public String toString()
    {
        return firstname +" "+ lastname;
    }
}

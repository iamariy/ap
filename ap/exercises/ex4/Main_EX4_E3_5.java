package ap.exercises.ex4;

public class Main_EX4_E3_5 {
    public static void main(String[] args)
    {
        for (int s1=0;s1<=1;s1++)
        {
            for (int s2=0;s2<=1;s2++)
            {
                Main_EX4_E3_4 circuit=new Main_EX4_E3_4();
                if (s1==1) circuit.toggleFirstSwitch();
                if (s2==1) circuit.toggleSecondSwitch();

                int lamptest=s1^s2;
                System.out.println("First switch:"+ circuit.getFirstSwitchState() +"\nSecond switch:"+ circuit.getSecondSwitchState());
                System.out.println("Initial lamp:"+ (circuit.getLampState()==1 ? "on" : "off"));
                System.out.println("Finally lamp:"+ (lamptest==1 ? "on" : "off"));
            }
        }
    }
}

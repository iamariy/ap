package ap.exercises.ex4;

public class Main_EX4_E3_4 {

        private int switch1;
        private int switch2;
        private int lamp;

        public Main_EX4_E3_4()
        {
            this.switch1=0;
            this.switch2=0;
        }
        public int getFirstSwitchState()
        {
            return switch1;
        }
        public int getSecondSwitchState()
        {
            return switch2;
        }
        public int getLampState()
        {
            return lamp;
        }
        public void toggleFirstSwitch()
        {
            switch1=1-switch1;
            updating();
        }
        public void toggleSecondSwitch()
        {
            switch2=1-switch2;
            updating();
        }
        public void updating()
        {
            lamp=switch1^switch2;
        }
    public static void main(String[] args)
    {
        Main_EX4_E3_4 light=new Main_EX4_E3_4();
        print(light);
        light.toggleFirstSwitch();
        print(light);
        light.toggleSecondSwitch();
        print(light);

    }
        public static void print(Main_EX4_E3_4 light)
        {
            System.out.println("First Switch:"+ light.getFirstSwitchState()+"\nSecond Switch:"+ light.getSecondSwitchState() +"\nLamp:"+ (light.getLampState()==1?"on":"off"));
        }
    }

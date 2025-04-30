package ap.exercises.ex4;

public class Main_EX4_E3_6 {
    private int[] switches;
    private int lamp;

    public Main_EX4_E3_6(){
        switches=new int[2];
        updatelamp();
    }
    public int getSwitchState(int switchNum){
        if (switchNum==0 || switchNum==1){
            return switches[switchNum];
        } else {
            System.out.println("Invalid switch number.");
            return -1;
        }
    }
    public int getLampState(){
        return lamp;
    }
    public void toggleSwitch(int switchNum){
        if (switchNum==0 || switchNum==1){
            int index=switchNum;
            switches[index]=1-switches[index];
            updatelamp();
        } else {
            System.out.println("Invalid switch number.");
        }
    }
    public void updatelamp(){
        lamp=switches[0]^switches[1];
    }
}

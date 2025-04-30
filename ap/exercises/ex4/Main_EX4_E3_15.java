package ap.exercises.ex4;

public class Main_EX4_E3_15 {
    private String from;
    private String to;
    private String body;

    public Main_EX4_E3_15(String from, String to){
        this.from=from;
        this.to=to;
        this.body="";
    }
    public void addLine(String line){
        body=body.concat(line).concat("\n");
    }
    public String getText(){
        String text="";
        text=text.concat("Dear "+ to +":\n\n");
        text=text.concat(body +"\n");
        text=text.concat("Sincerely,\n\n");
        text=text.concat(from);
        return text;
    }

    public static void main(String[] args){
        Main_EX4_E3_15 letter=new Main_EX4_E3_15("Marry","John");

        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.getText());
    }
}

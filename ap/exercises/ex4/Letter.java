package ap.exercises.ex4;

public class Letter {
    private String from;
    private String to;
    private String body;

    public Letter(String from,String to){
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
}
class LetterPrinter{
    public static void main(String[] args){
        Letter letter=new Letter("Marry","John");

        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.getText());
    }
}

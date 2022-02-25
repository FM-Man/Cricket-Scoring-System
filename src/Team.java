import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private int run;
    private int balls;
    private int wicket;
    private OnPitch onPitch;
    private Player Bowling;
    private ArrayList<Player> outed;
    private ArrayList<Player> yetToBat;

    public String teamName(){
        return name;
    }
    public int run(){
        return run;
    }
    public String over(){
        return balls/6.0+"."+balls%6;
    }
    public int wicket(){
        return wicket;
    }
    public String onStrike(){
        return onPitch.onStrike().name();
    }
    public String nonStrike(){
        return onPitch.nonStrike().name();
    }


    private void addRun(int run){
        this.run += run;
        onPitch.onStrike().addRun(run);
        if(run==1||run==3||run==5){
            onPitch.switchStrike();
        }
    }
    private void addBall(){
        balls++;
        onPitch.onStrike().addBall();
    }

    public void play(String thisBall){
        switch(thisBall){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                addRun(INT(thisBall));
                addBall();
                break;
            case "0ws":
            case "1ws":
            case "2ws":
            case "3ws":
            case "4ws":
            case "5ws":
            case "0wn":
            case "1wn":
            case "2wn":
            case "3wn":
            case "4wn":
            case "5wn":
                addRun(INT(thisBall,2));
                addBall();
                //if()
                break;
            case "ws":
                break;
            case "wn":
                break;
            case "wd":
                break;
            case "1wd":
                break;
            case "2wd":
                break;
            case "3wd":
                break;
            case "4wd":
                break;
            case "5wd":
                break;
            case "nb":
                break;
            case "1nb":
                break;
            case "2nb":
                break;
            case "3nd":
                break;
            case "4nb":
                break;
            case "5nb":
                break;
            case "6nb":
                break;
            case "0":
                break;
            case "1lb":
                break;
            case "2lb":
                break;
            case "3lb":
                break;
            case "4lb":
                break;
            case "5lb":
                break;
            case "6lb":
                break;
            case "b":
                break;
            case "1b":
                break;
            case "2b":
                break;
            case "3b":
                break;
            case "4b":
                break;
            case "5b":
                break;
        }
    }

    private int INT(String s){
        return Integer.parseInt(s.substring(0,1));
    }
    private int INT(String s, int i){
        return Integer.parseInt(s.substring(i));
    }
}


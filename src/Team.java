import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private int run;
    private int overs;
    private int balls;
    private int extras;
    private int wicket;
    private OnPitch onPitch;
    private Player bowling;
    private ArrayList<Player> outed;
    private ArrayList<Player> yetToBat;
    public boolean battingFirst = false;
    private Team opponent;

    public Team(String name, ArrayList<Player> players){
        this.name = name;
        this.players = players;
        outed = new ArrayList<>();
        //this.match = match;

        for (Player player : this.players) {
            player.setTeam(this);
        }
        yetToBat = this.players;

    }
    public void onBatting(){
        onPitch = new OnPitch(yetToBat.remove(0),yetToBat.remove(0));
        bowling = null;
    }
    private boolean targetPassed(){
        return !battingFirst && opponent.run() < run;
    }
    public boolean stillBatting(){
        return overs < 50 && wicket < 10 && !targetPassed();
    }
    public void onBowling(){
        switchBowler();
    }


    public String teamName(){
        return name;
    }
    public int run(){
        return run;
    }
    public String over(){
        return overs+"."+balls;
    }
    public int wicket(){
        return wicket;
    }
    public Player onStrike(){
        return onPitch.onStrike();
    }
    public Player nonStrike(){
        return onPitch.nonStrike();
    }
    public int extra(){
        return extras;
    }


    private void addRun(int run){
        this.run += run;
        onPitch.onStrike().addRun(run);
        if(run==1||run==3||run==5){
            onPitch.switchStrike();
        }
    }
    private void addExtra(int run){
        this.run+=run;
        extras+=run;
    }
    private void addBall(){
        balls++;
        onPitch.onStrike().addBall();
    }
    public Player bowler(){
        return bowling;
    }
    private void changeBatter(boolean isOnStrike){
        if(isOnStrike){
            outed.add(onPitch.onStrike());
        } else {
            outed.add(onPitch.nonStrike());
        }
        onPitch.changeBatter(yetToBat.remove(0),isOnStrike);
        wicket++;
    }
    private void switchBowler(){
        System.out.println("Enter Bowler ID:");
        Scanner scanner = new Scanner(System.in);
        int bowlerID = scanner.nextInt();
        Player flag = null;
        for (Player player : players) {
            if (bowlerID == player.id()) {
                flag = player;
                break;
            }
        }
        if(flag != null && flag != bowling && !flag.over().startsWith("10") ){
            if(bowling!=null) bowling.endBowling();
            bowling = flag;
            bowling.bowling();
        }
        else switchBowler();
    }
    public void opponent(Team team){
        opponent = team;
    }


    public void play(String thisBall){
        switch(thisBall){
            case "1": case "2": case "3": case "4": case "5": case "6":
                addBall();
                addRun(INT(thisBall));
                opponent.bowler().bowled();
                opponent.bowler().addRunsGiven(INT(thisBall));
                break;
            case "1ws": case "3ws": case "5ws": case "0ws": case "2ws":case "4ws":
                addBall();
                addRun(INT(thisBall));
                onPitch.switchStrike();
                changeBatter(true);
                opponent.bowler().bowled();
                opponent.bowler().addRunsGiven(INT(thisBall));
                break;
            case "0wn": case "1wn": case "2wn": case "3wn": case "4wn": case "5wn":
                addBall();
                addRun(INT(thisBall));
                onPitch.switchStrike();
                changeBatter(false);
                opponent.bowler().bowled();
                opponent.bowler().addRunsGiven(INT(thisBall));
                break;
            case "ws":
                addBall();
                changeBatter(true);
                opponent.bowler().bowled();
                opponent.bowler().gotWicket();
                break;
            case "wn":
                addBall();
                onPitch.switchStrike();
                changeBatter(false);
                opponent.bowler().bowled();
                opponent.bowler().gotWicket();
                break;
            case "0wd": case "2wd": case "4wd":
                addExtra(INT(thisBall)+1);
                opponent.bowler().addRunsGiven(INT(thisBall)+1);
                break;
            case "1wd": case "3wd": case "5wd":
                addExtra(INT(thisBall) + 1);
                onPitch.switchStrike();
                opponent.bowler().addRunsGiven(INT(thisBall)+1);
                break;
            case "0nb": case "1nb": case "2nb": case "3nd": case "4nb": case "5nb": case "6nb":
                onPitch.onStrike().addBall();
                addRun(INT(thisBall));
                addExtra(1);
                opponent.bowler().addRunsGiven(INT(thisBall)+1);
                break;
            case "0":
                addBall();
                opponent.bowler().bowled();
                break;
            case "1lb": case "3lb": case "5lb":case "1b":case "3b":case "5b":
                addExtra(INT(thisBall));
                addBall();
                onPitch.switchStrike();
                opponent.bowler().bowled();
                break;
            case "2lb": case "4lb": case "6lb": case "2b": case "4b":
                addExtra(INT(thisBall));
                addBall();
                opponent.bowler().bowled();
                break;
            default:
                System.out.println("Invalid input");
        }
        if(balls==6){
            balls = 0;
            overs++;
            if(overs < 50){
                onPitch.switchStrike();
                switchBowler();
            }
        }
    }

    private int INT(String s){
        return Integer.parseInt(s.substring(0,1));
    }
}


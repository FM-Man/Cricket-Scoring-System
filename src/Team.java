import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private int run;
    private int wicket;
    private OnPitch onPitch;

    public String teamName(){
        return name;
    }
    public int run(){
        return run;
    }
    public int wicket(){
        return wicket;
    }

}


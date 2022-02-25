public class Player {
    private final int id;
    private final String name;
    private int run = 0;
    private int ballsFaced = 0;
    private int fours = 0;
    private int sixes = 0;
    private BattingStatus batingStatus;
    private int ballsBowled = 0;
    private int runsGiven = 0;
    private int wicketsTaken = 0;
    private BallingStatus ballingStatus;
    private Team team;

    public Player(int id, String name, Team team){
        this.id=id;
        this.name=name;
        this.team = team;
        batingStatus = BattingStatus.NotBatted;
        ballingStatus = BallingStatus.NotBowled;
    }
    public Player(int id, String name){
        this.id=id;
        this.name=name;
        batingStatus = BattingStatus.NotBatted;
        ballingStatus = BallingStatus.NotBowled;
    }


    public void addRun(int run){
        if(batingStatus.equals(BattingStatus.NotOut_OnStrike)){
            this.run+=run;
            if(run==4){
                fours++;
            } else if(run==6){
                sixes++;
            }
        }
        else {
            System.out.println("Wrong batsman");
        }
    }
    public void addBall(){
        if (batingStatus.equals(BattingStatus.NotOut_OnStrike))
            ballsFaced++;
        else
            System.out.println("Wrong batsman faced ball");
    }
    public void bowled(){
        ballsBowled++;
    }
    public void addRunsGiven(int run){
        runsGiven += run;
    }
    public void gotWicket(){
        wicketsTaken++;
    }
    public void setTeam(Team team){
        this.team = team;
    }



    public int run(){
        return run;
    }
    public int balls(){
        return ballsFaced;
    }
    public int sixes(){
        return sixes;
    }
    public int fours(){
        return fours;
    }
    public String team(){
        return team.teamName();
    }
    public String name(){
        return name;
    }
    public int id(){
        return id;
    }
    public BattingStatus battingStatus(){
        return batingStatus;
    }
    public String over(){
        return ballsBowled/6+"."+ballsBowled%6;
    }
    public int runGiven(){
        return runsGiven;
    }
    public int wickets(){
        return wicketsTaken;
    }
    public double runrate(){
        if (ballingStatus == BallingStatus.NotBowled)
            return 0.0;
        else
            return  (double) runsGiven/ballsBowled*6.0;
    }
    public double strikeRate(){
        if(ballsFaced!=0)
            return ((double) run/ballsFaced) * 100;
        else
            return 0.0;
    }


    public void batting(boolean onStrike){
        if(onStrike)
            batingStatus = BattingStatus.NotOut_OnStrike;
        else batingStatus = BattingStatus.NotOut_NonStrike;

        run = 0;
        ballsFaced = 0;
        fours = 0;
        sixes = 0;
    }
    public void out(){
        batingStatus = BattingStatus.Out;
    }
    public void onStrike(){
        batingStatus = BattingStatus.NotOut_OnStrike;
    }
    public void nonStrike(){
        batingStatus = BattingStatus.NotOut_NonStrike;
    }
    public void bowling(){
        ballingStatus = BallingStatus.Bowling;
    }
    public void endBowling(){
        ballingStatus = BallingStatus.Bowled;
    }
}

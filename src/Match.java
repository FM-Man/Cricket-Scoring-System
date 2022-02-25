import java.util.Scanner;

public class Match {
    private final Team[] teams = new Team[2];
    public Match(Team t1, Team t2){
        teams[0] = t1;
        teams[1] = t2;
        teams[0].opponent(teams[1]);
        teams[1].opponent(teams[0]);

        teams[0].battingFirst = true;
        teams[0].onBatting();
        teams[1].onBowling();

        do {
            System.out.print("Enter this ball: ");
            Scanner sc = new Scanner(System.in);
            teams[0].play(sc.nextLine());
            printScore(teams[0],teams[1]);
        } while (teams[0].stillBatting());

        teams[0].onBowling();
        teams[1].onBatting();

        do {
            System.out.print("Enter this ball: ");
            Scanner sc = new Scanner(System.in);
            teams[1].play(sc.nextLine());
            printScore(teams[1], teams[0]);
        } while (teams[1].stillBatting());

        if(teams[0].run() > teams[1].run()){
            System.out.println("team 1 won");
        }else if(teams[1].run() > teams[0].run()){
            System.out.println("team 2 won");
        }else{
            System.out.println("tied");
        }
    }

    private void printScore(Team battingTeam, Team bowlingTeam){
        System.out.println("________________________________________________________________________________________________________");
        System.out.println(battingTeam.teamName()+": "+battingTeam.run()+"/"+battingTeam.wicket());
        System.out.println(battingTeam.onStrike().name()+"**  "+ battingTeam.onStrike().run()+"("+battingTeam.onStrike().balls()+")");
        System.out.println(battingTeam.nonStrike().name()+"  "+ battingTeam.nonStrike().run()+"("+battingTeam.nonStrike().balls()+")");
        System.out.println(bowlingTeam.bowler().name()+"  "+bowlingTeam.bowler().over()+"-"+bowlingTeam.bowler().runGiven()+"-"+bowlingTeam.bowler().wickets());
        System.out.println("________________________________________________________________________________________________________");
    }
}

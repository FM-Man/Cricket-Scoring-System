import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Driver {
    public static String[] playerNames = new String[]
            {
                    "A",
                    "B",
                    "C",
                    "D",
                    "E",
                    "F",
                    "G",
                    "H",
                    "I",
                    "J",
                    "K",
                    "L",
                    "M",
                    "N",
                    "O",
                    "P",
                    "Q",
                    "R",
                    "S",
                    "T",
                    "U",
                    "V"
            };
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Player> team1 = new ArrayList<>();
        ArrayList<Player> team2 = new ArrayList<>();
        for (int i=0; i<11; i++){
            Player player = new Player(i, playerNames[i]);
            team1.add(player);
        }
        Team team_1 = new Team("Team 1", team1);
        for(int i=11; i<22;i++){
            Player player = new Player(i, playerNames[i]);
            team2.add(player);
        }
        Team team_2 = new Team("Team 2", team2);
        new Match(team_1,team_2);
    }
}

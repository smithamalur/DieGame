import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    private final List<Die>  dice;
    private final List<Player>  players;
    private final GamePlay gamePlay;
    private final int totalNoOfPlayers;
    private final int totalNoOfRounds;
    private final int totalNoOfDie;

    public Game() {
        totalNoOfPlayers = 4;
        totalNoOfRounds = 4;
        totalNoOfDie = 5;
        dice = initDice(totalNoOfDie);
        players = initPlayers(totalNoOfPlayers);
        gamePlay = new GamePlay(players, totalNoOfRounds, dice);
    }

    public void play() {
        gamePlay.playGame();
        List<Player> winners = getWinners();
        displayPlayers();
        displayWinners(winners);
    }

    private void displayPlayers() {
        System.out.println("The Players are: ");
        for(Player player : players) {
            System.out.println(player);
        }
    }

    private List<Player> getWinners() {
        List<Player> winners = new ArrayList<>();
        Optional<Player> maxScoredPlayer = players.stream()
                .min(Comparator.comparing(player -> player.getScore()));
        if (!maxScoredPlayer.isPresent()) {
            return winners;
        }
        int maxScore = maxScoredPlayer.get().getScore();
        for (Player player : players) {
            if (player.getScore() == maxScore) {
                winners.add(player);
            }
        }
        return winners;
    }

    private List<Die> initDice(int totalNoOfDie) {
        List<Die> dieList = new ArrayList<>();
        for(int i=0; i<totalNoOfDie; i++) {
            Die die = new Die(i);
            dieList.add(die);
        }
        return dieList;
    }

    private List<Player> initPlayers(int totalNoOfPlayers) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        System.out.println("Enter your name ");
        String name = scanner.next();
        System.out.println("");
        Player player = new Player(name, false);
        players.add(player);

        for(int i=1; i< totalNoOfPlayers; i++) {
            player = new Player("Robo"+i, true);
            players.add(player);
        }
        System.out.println("Game begins");
        System.out.println("The players are");
        for(Player p : players) {
            System.out.println(p.getName());
        }
        try {
            Thread.sleep(500);
        } catch(Exception e) {

        }
        return players;
    }

    private void displayWinners(List<Player> winners) {
        if (winners.size() > 1) {
            System.out.println("It's a tie!!!");
        }
        System.out.println("The winners are: ");
        for (Player player: winners) {
            System.out.println("Player: " + player.getName() +", Score: " + player.getScore());
        }
    }
}

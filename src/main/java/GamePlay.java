import java.util.ArrayList;
import java.util.List;

public class GamePlay {
    private List<Player> players;
    private List<Die> dice;
    private int totalRounds;

    public GamePlay(List<Player> players, int totalRounds, List<Die> die) {
        this.players = players;
        this.totalRounds = totalRounds;
        this.dice = die;
    }

    public void playGame() {
        for (int i=0; i< totalRounds; i++) {
            System.out.println("*********************************************************************");
            System.out.println("Playing round " +i);
            System.out.println("*********************************************************************");
            System.out.println("");

            playRound();
        }
    }

    public void playRound() {
        int playerIndex = getRandomPlayer();
        for(int i=0; i< players.size(); i++) {
            int index = (playerIndex+i)% players.size();
            Player player = players.get(index);
            System.out.println("-------------------------------------------");
            System.out.println("It is Player " +player.getName()+"'s turn");
            System.out.println("-------------------------------------------");
            if(player.isRobo()) {
                autoPlayRound(index);
            } else {
                playYOurPlay(index);
            }
            resetDies();
        }
    }

    private void resetDies() {
        for(Die die : dice) {
            die.reset();
        }
    }

    private void playYOurPlay( int index) {
        Player player = players.get(index);
        player.receiveDies(new ArrayList<>(dice), new ArrayList<>());
        while(!player.getDieAvailable().isEmpty()) {
            player.rollDie();

        }
        player.resetDies();
    }

    private void autoPlayRound(int index) {
        Player player = players.get(index);
        player.receiveDies(new ArrayList<>(dice), new ArrayList<>());
        while(!player.getDieAvailable().isEmpty()) {
            player.rollDie();
        }
        player.resetDies();
    }

    private int getRandomPlayer() {
        return (int)(Math.random() * players.size()) + 1;
    }

}

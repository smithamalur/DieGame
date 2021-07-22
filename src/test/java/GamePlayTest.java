import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GamePlayTest {
    private GamePlay gameplay;
    private List<Die> dice = new ArrayList<Die>();
    private List<Player> players = new ArrayList<Player>();
    @Before
    public void init() {
        dice.add(new Die(1));
        dice.add(new Die(2));
        dice.add(new Die(3));
        dice.add(new Die(4));
        dice.add(new Die(5));

        players.add(new Player("roboPlayer1", true));
        players.add(new Player("roboPlayer2", true));
        players.add(new Player("roboPlayer3", true));
        players.add(new Player("roboPlayer4", true));
        gameplay = new GamePlay(players, 4, dice);
    }

    @Test
    public void testPlay() {
        gameplay.playRound();
        for(Player player : players) {
            assert(player.getScore() > 0);
        }
    }

}

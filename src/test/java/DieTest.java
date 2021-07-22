import org.junit.Before;
import org.junit.Test;

public class DieTest {
    public Die die;
    public DieFace dieface;
    @Before
    public void init() {
        die = new Die(1);
    }
    @Test
    public void testRollisNotAboveFive() {
        dieface = die.roll();
        assert(die.getStatus().equals(DieStatus.ROLLED));
        assert(die.getScore() <= 6);
    }
}
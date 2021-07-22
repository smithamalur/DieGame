import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    private final List<DieFace> dieFace;
    private final int id;
    private final Random random;
    private DieStatus status;
    private DieFace face;

    public Die(int id) {
        dieFace = new ArrayList<>();
        for(int i=0; i<6; i++) {
            if(i==3) {
                dieFace.add(new DieFace(4, 0));
            } else {
                dieFace.add(new DieFace(i+1, i+1));
            }
        }
        this.id=id;
        status = DieStatus.ROLL;
        random = new Random();
        face = dieFace.get(0);
    }

    public DieFace roll() {
        if(status == DieStatus.ROLL) {
            int index = random.nextInt(6);
            face = dieFace.get(index);
            status = DieStatus.ROLLED;
            return dieFace.get(index);
        } else {
            throw new IllegalStateException("Die Unavailable to roll");
        }
    }

    public DieStatus getStatus() {
        return status;
    }


    public DieFace getFace() {
        return face;
    }


    public int getId() {
        return id;
    }

    public int getScore() {
        return face.getScore();
    }

    public void keep() {
        if (status != DieStatus.ROLLED) {
            throw new IllegalStateException("Die Not Rolled. ROll the die");
        }
        status = DieStatus.KEPT;
    }

    public void reset() {
        status = DieStatus.ROLL;
    }

    @Override
    public String toString() {
        return "Die{" +
                "id=" + id +
                " value=" + face.getValue() +
                ", score=" + face.getScore() +
                '}';
    }
}

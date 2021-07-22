import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Player {
    private String name;
    private int score;
    private boolean robo;
    private List<Die> dieKept;
    private List<Die> dieAvailable;

    public Player(String name, boolean robo) {
        this.name = name;
        this.robo = robo;
        this.score = 0;
        this.dieKept = new ArrayList<>();
        this.dieAvailable = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isRobo() {
        return robo;
    }

    public List<Die> getDieAvailable() {
        return dieAvailable;
    }

    public void rollDie() {
        for (Die die : dieAvailable) {
            die.roll();
            try {
                Thread.sleep(500);
            } catch(Exception e) {
                continue;
            }
        }
        Set<Integer> diePicked = pickDice();
        keepAndResetDie(diePicked);
    }

    private Set<Integer> pickDice() {
        if (robo) {
           return roboPickDice();
        } else {
            return manualPickDice();
        }
    }

    private Set<Integer> roboPickDice() {
        Collections.sort(dieAvailable, (a, b) -> a.getScore() - b.getScore());

        for(Die die: dieAvailable) {
            System.out.println(die);
        }

        Set<Integer> diePicked = new HashSet<>();
        diePicked.add(dieAvailable.get(0).getId());
        System.out.println("Player picked die "+dieAvailable.get(0)+ " With score "+dieAvailable.get(0).getScore());

        if (dieAvailable.size() < 2) {
            return diePicked;
        }
        for(int i=1; i<dieAvailable.size(); i++) {
            Die die = dieAvailable.get(i);
            switch(die.getScore()) {
                case 0:
                    diePicked.add(die.getId());
                    System.out.println("Player picked die "+die.getId()+ " With score "+die.getScore());
                    break;
                case 1:
                    diePicked.add(die.getId());
                    System.out.println("Player picked die "+die.getId()+ " With score "+die.getScore());
                    break;
                case 2:
                    if(dieAvailable.size() <= 3) {
                        diePicked.add(die.getId());
                        System.out.println("Player picked die "+die.getId()+ " With score "+die.getScore());
                    }
                    break;
                default:
                    continue;
            }
        }
        return diePicked;
    }

    private Set<Integer> manualPickDice() {
        System.out.println("Enter the id of one or more die you'd like to pick separated by space ");
        for(Die die : dieAvailable) {
            System.out.println(die);
        }
        Scanner scanner = new Scanner(System.in);
        Set<Integer> dicePicked = new HashSet<>();
        boolean picked = false;
        while (!picked) {
            String selection = scanner.nextLine().trim();
            if (Objects.isNull(selection) || selection.isEmpty()) {
                System.out.println("Enter at least one value:");
                continue;
            }
            picked = true;
            String[] ids = selection.split(" ");
            for (String id : ids) {
                dicePicked.add(Integer.valueOf(id));
            }
        }
        return dicePicked;
    }

    public void resetDies() {
        dieKept = new ArrayList<>();
        dieAvailable = new ArrayList<>();
    }

    public void receiveDies(List<Die> dieAvailable, List<Die> dieKept) {
        this.dieAvailable = dieAvailable;
        this.dieKept = dieKept;
    }

    private void keepAndResetDie(Set<Integer> dieIds) {
        List<Die> temp = new ArrayList<>(dieAvailable);
        dieAvailable = new ArrayList<>();
        for (Die die : temp) {
            if (dieIds.contains(die.getId())) {
                score += die.getScore();
                die.keep();
                dieKept.add(die);
            } else {
                die.reset();
                dieAvailable.add(die);
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

import java.util.Comparator;

public class Player implements Comparator<Player> {
    private String name;
    private int score;
    private int gamesWon;
    private int resultRoll;


    public Player() {
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.gamesWon = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getResultRoll() {
        return resultRoll;
    }

    public void setResultRoll(int resultRoll) {
        this.resultRoll = resultRoll;
    }

    public void setNumBrains(int nBrains) {
        this.score = nBrains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return "The player " + getName() + "'s reaches the score: " + getScore();
    }

    @Override
    public int compare(Player o1, Player o2) {
        return o2.resultRoll - o1.resultRoll;
    }
}

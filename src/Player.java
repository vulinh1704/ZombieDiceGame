public class Player {
    private String name;
    private int score;
    private int gamesWon;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.gamesWon = 0;
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
}

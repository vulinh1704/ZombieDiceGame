import java.util.Random;

class Die {
    private int nSides;

    public Die() {
        this.nSides = 6;
    }

    public Die(int nSides) {
        this.nSides = nSides;
    }

    public int roll() {
        Random rand = new Random();
        return rand.nextInt(nSides) + 1;
    }

    public String toString() {
        return "A " + nSides + "-sided die";
    }
}

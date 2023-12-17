import java.util.Objects;
import java.util.Random;

class ZomebieDice extends Die {
    private String color;
    private int nBrains;
    private int nShotguns;
    private int nFeet;

    public ZomebieDice(String color) {
        super(6);
        this.color = color;
        this.nFeet = 2;
        switch (color) {
            case "Green" -> {
                this.nBrains = 3;
                this.nShotguns = 1;
            }
            case "Red" -> {
                this.nBrains = 1;
                this.nShotguns = 3;
            }
            case "Yellow" -> {
                this.nBrains = 2;
                this.nShotguns = 2;
            }
        }
    }

    @Override
    public int roll() {
        Random rand = new Random();
        int result = rand.nextInt(6) + 1;
        if (result == 1 || result == 6) {
            return 0;
        } else if (result == 4 || (result == 2 && Objects.equals(this.color, "Green")) || (result == 5 && Objects.equals(this.color, "Green")) || (result == 2 && Objects.equals(this.color, "Yellow"))) {
            return -1;
        } else if (result == 3 || (result == 2 && Objects.equals(this.color, "Red")) || (result == 5 && Objects.equals(this.color, "Red")) || (result == 5 && Objects.equals(this.color, "Yellow"))) {
            return -2;
        }
        return 1;
    }

    public String convertRollResult(int result) {
        if (result == 0) {
            return "Footsteps";
        } else if (result == -1) {
            return "Brain";
        } else {
            return "Shotgun";
        }
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return "ZomebieDice: Number of sides: " + super.toString() + ", Color: " + color + ", Feet: " + nFeet +
                ", Brains: " + nBrains + ", Shotguns: " + nShotguns;
    }
}
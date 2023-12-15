import java.util.Random;

class ZombieDie extends Die {
    private String color;
    private int nBrains;
    private int nShotguns;
    private int nFeet;

    public ZombieDie(String color) {
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
        } else if (result == 4) {
            return -1;
        } else if (result == 3) {
            return -2;
        } else {
            return 1;
        }
    }

    public String convertRollResult(int result) {
        if (result == 0) {
            return "Footsteps";
        } else if (result == -1) {
            return "Brain";
        } else if (result == -2) {
            return "Shotgun";
        } else {
            return "Unknown";
        }
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return "ZombieDie: Number of sides: " + super.toString() + ", Color: " + color + ", Feet: " + nFeet +
                ", Brains: " + nBrains + ", Shotguns: " + nShotguns;
    }
}
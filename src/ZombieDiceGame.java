import java.util.*;

public class ZombieDiceGame {
    public static void main(String[] args) {
        Die die = new Die();
        System.out.println(die);

        ArrayList<ZombieDie> zombieDice = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            zombieDice.add(new ZombieDie("Green"));
        }
        for (int i = 0; i < 4; i++) {
            zombieDice.add(new ZombieDie("Yellow"));
        }
        for (int i = 0; i < 3; i++) {
            zombieDice.add(new ZombieDie("Red"));
        }

        Scanner scanner = new Scanner(System.in);
        int numPlayers;
        do {
            System.out.print("Enter number of players (3-6): ");
            numPlayers = scanner.nextInt();
        } while (numPlayers < 3 || numPlayers > 6);

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        int[] playerOrder = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerOrder[i] = die.roll();
        }
        Arrays.sort(playerOrder);

        System.out.println("Player order:");
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + playerOrder[i]);
        }

        Random rand = new Random();
        int totalBrains = 0;
        int currentPlayer = 0;

        while (true) {

            int shotguns = 0;
            int brains = 0;
            int rerolls = 0;

            System.out.println("\nPlayer " + players[currentPlayer].getName() + "'s turn:");
            ArrayList<ZombieDie> currentDice = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                int index = rand.nextInt(zombieDice.size());
                ZombieDie dieZombie = zombieDice.get(index);
                currentDice.add(dieZombie);

                System.out.println("ZombieDie Color: " + dieZombie.getColor());
            }

            for (ZombieDie dieZombie : currentDice) {
                int result = dieZombie.roll();
                System.out.print(dieZombie.convertRollResult(result) + " | ");
                if (result == -2) {
                    shotguns++;
                } else if (result == -1) {
                    brains++;
                } else if (result == 0) {
                    rerolls++;
                }
            }

            if (shotguns >= 3) {
                players[currentPlayer].setNumBrains(0);
                System.out.println("\nTURN OVER - 3 shotguns! All points lost.");
            } else {
                totalBrains += brains;
                players[currentPlayer].setNumBrains(totalBrains);
                System.out.println("\nPlayer " + players[currentPlayer].getName() + " accumulated " + brains + " brains.");

                if (totalBrains >= 13) {
                    System.out.println("PLAYER " + players[currentPlayer].getName() + " WINS!");
                    break;
                }
                if (rerolls > 0) {
                    System.out.println("Player " + players[currentPlayer].getName() + " has " + rerolls +
                            " dice with footsteps. Rerolling...");
                }
            }
            currentPlayer = (currentPlayer + 1) % numPlayers;
        }
    }
}

import java.util.*;

public class ZombieDiceGame {
    public static void main(String[] args) {
        Die die = new Die();
        System.out.println(die);


        Scanner scanner = new Scanner(System.in);
        int numPlayers;
        do {
            System.out.print("Enter number of players (3-6): ");
            numPlayers = scanner.nextInt();
        } while (numPlayers < 3 || numPlayers > 6);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1)));
            players.get(i).setResultRoll(die.roll());
        }
        players.sort(new Player());

        System.out.println("Player order:");
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + players.get(i).getResultRoll());
        }

        int currentPlayer = 0;
        int reRoll = 0;
        Random rand = new Random();
        ArrayList<ZombieDie> zombieDice = getZombieDice();
        int shotguns = 0;
        int brains = 0;
        while (true) {
            if (reRoll != currentPlayer) {
                zombieDice = getZombieDice();
            }

            Player player = players.get(currentPlayer);
            System.out.println("Player " + player.getName() + "'s turn!!");
            ArrayList<ZombieDie> currentDice = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (zombieDice.size() == 0) break;
                int index = rand.nextInt(zombieDice.size());
                ZombieDie zombieDie = zombieDice.remove(index);
                currentDice.add(zombieDie);
                System.out.println("ZombieDie Color: " + zombieDie.getColor());
            }

            for (ZombieDie z : currentDice) {
                int result = z.roll();
                System.out.print(z.convertRollResult(result) + " | ");
                if (result == -2) {
                    shotguns++;
                } else if (result == -1) {
                    brains++;
                } else if (result == 0) {
                    zombieDice.add(z);
                }
            }

            boolean checkIncrementCurrentPlayer = false;
            if (player.getShotguns() >= 3) {
                player.setScore(0);
                System.out.println("\nTURN OVER Player " + player.getName() + "'s - 3 shotguns! All points lost.");
                shotguns = 0;
                brains = 0;
                checkIncrementCurrentPlayer = true;
            } else if (player.getScore() >= 13) {
                brains = 0;
                shotguns = 0;
                System.out.println("\nPlayer " + player.getName() + "'s reaches the score: " + player.getScore() + " WINN!!");
                checkIncrementCurrentPlayer = true;
            } else {
                if (zombieDice.size() == 0) {
                    System.out.println("\nThe player " + player.getName() + "'s reaches the score: " + player.getScore());
                    System.out.println("\nPlayer " + player.getName() + "'s turn has ended!");
                    brains = 0;
                    shotguns = 0;
                    checkIncrementCurrentPlayer = true;
                } else {
                    reRoll = currentPlayer;
                    player.setScore(player.getScore() + brains);
                    player.setShotguns(player.getShotguns() + shotguns);
                    System.out.println("\nThe player " + player.getName() + "'s has Brains: " + player.getScore() + " and Shotguns: " + shotguns);
                }
            }

            if (players.size() == currentPlayer + 1) {
                checkShotGuns(players);
                boolean flag = false;
                for (Player p : players) {
                    if (p.getScore() >= 13) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
                else {
                    currentPlayer = 0;
                    reRoll = -1;
                }
            }

            if(checkIncrementCurrentPlayer) {
                currentPlayer++;
            }
        }


        System.out.println("\n--------------- GAME END ------------------");
        for (Player player : players) {
            System.out.println(player);
        }

        for (Player player : players) {
            if (player.getScore() >= 13) {
                System.out.println(player.getName() + " WIN !!!!");
            }
        }

    }

    public static void checkShotGuns(List<Player> players) {
        int checkShotgunsPlayers = 0;
        for (Player p : players) {
            if (p.getShotguns() >= 3) {
                checkShotgunsPlayers++;
            }
        }
        if (checkShotgunsPlayers == players.size()) {
            for (Player p : players) {
                p.setShotguns(0);
            }
        }
    }


    public static ArrayList<ZombieDie> getZombieDice() {
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
        return zombieDice;
    }

}

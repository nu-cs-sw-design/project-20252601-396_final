package view;

import model.Card;
import model.CardType;
import model.GameObserver;
import model.Player;
import model.exceptions.GameException;

import java.util.Scanner;

public class GameUI implements GameObserver {
    private final Scanner scanner;

    public GameUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void onGameMessage(String message) {
        System.out.println("[GAME]: " + message);
    }

    @Override
    public void onTurnChanged(int playerIdx) {
        System.out.println("\n[TURN]: Now Player " + playerIdx + "'s turn.");
    }

    @Override
    public void onCardPlayed(int playerIdx, Card card) {
        System.out.println("[ACTION]: Player " + playerIdx + " played " + card);
    }

    @Override
    public void onRequestNope(int actionPlayerIdx, CardType cardType) {
        System.out.println("[NOPE-CHECK]: Player " + actionPlayerIdx + " played " + cardType + ". Checking for Nopes...");
    }

    @Override
    public void onExplosionRisk(int playerIdx) {
        System.out.println("[DANGER]: Player " + playerIdx + " drew an Exploding Kitten!");
    }

    @Override
    public void onRequestExplosionInsertIndex() {
        System.out.println("[INPUT]: You must insert the Kitten back into the deck.");
    }

    @Override
    public void onDefuseUsed(int playerIdx) {
        System.out.println("[DEFUSE]: Player " + playerIdx + " survived using a Defuse!");
    }

    @Override
    public void onPlayerEliminated(int playerIdx) {
        System.out.println("[DEATH]: Player " + playerIdx + " is OUT!");
    }

    @Override
    public void onActionCancelled(String message) {
        System.out.println("[CANCEL]: " + message);
    }

    @Override
    public void onException(GameException e) {
        System.out.println("[ERROR]: " + e.getMessage());
    }

    public void displayHand(Player player) {
        System.out.println("[Your Hand]: ");
        for (int i = 0; i < player.getHandSize(); i++) {
            System.out.println("   [" + i + "] " + player.getCard(i));
        }
    }

    public int promptPlayerCount() {
        System.out.println(">> How many players do you want? (3-5):");
        System.out.print("> ");

        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("> ");
                scanner.next();
            }

            int count = scanner.nextInt();
            scanner.nextLine();
            if (count >= 3 && count <= 5) {
                return count;
            } else {
                System.out.println("Invalid number. Please enter between 3 and 5.");
                System.out.print("> ");
            }
        }
    }

    public String promptCommand() {
        System.out.println("Options: [D]raw, [P <index>] Play Card");
        System.out.print("> ");
        return scanner.nextLine().trim().toUpperCase();
    }

    public int promptInsertionIndex() {
        System.out.println(">> Enter index to insert Kitten (0 = Top):");
        System.out.print("> ");
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public boolean promptPlayNope(int playerId, String message) {
        System.out.println(">> Player " + playerId + ", you have a NOPE card.");
        System.out.println("   " + message + " [Y]es / [N]o");
        System.out.print("> ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }
}
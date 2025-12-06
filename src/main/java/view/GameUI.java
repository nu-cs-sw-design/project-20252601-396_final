package view;
import model.Game;
import model.Player;
import model.GameObserver;
import model.Card;
import model.CardType;
import model.exceptions.GameException;

public class GameUI implements GameObserver {
    @Override
    public void onGameMessage(String message) {
        System.out.println("[GAME]: " + message);
    }

    @Override
    public void onTurnChanged(int playerIdx) {
        System.out.println("[TURN]: Now Player " + playerIdx + "'s turn.");
    }

    @Override
    public void onCardPlayed(int playerIdx, Card card) {
        System.out.println("[ACTION]: Player " + playerIdx + " played " + card);
    }

    @Override
    public void onRequestNope(int actionPlayerIdx, CardType cardType) {
        System.out.println("[NOPE-CHECK]: Player " + actionPlayerIdx + " played " + cardType + ". Does anyone want to NOPE?");
    }

    @Override
    public void onExplosionRisk(int playerIdx) {
        System.out.println("[DANGER]: Player " + playerIdx + " drew an Exploding Kitten!");
    }

    @Override
    public void onRequestExplosionInsertIndex() {
        System.out.println("[INPUT]: Where do you want to insert the Kitten? (Enter Index)");
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

    public void promptPlayerCount() {
        System.out.println(">> How many players do you want? (2-5):");
        System.out.print("> ");
    }

    public void displayHand(Player player) {
        System.out.println("[Your Hand]: ");
        for (int i = 0; i < player.getHandSize(); i++) {
            System.out.println("   [" + i + "] " + player.getCard(i));
        }
        System.out.print("> ");
    }

    public void displayState(Game game) {
        System.out.println("Current Phase: " + game.getPhase());
        System.out.println("Deck Size: " + game.getDeck().getDeckSize());
    }
}
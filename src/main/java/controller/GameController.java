package controller;

import model.CardType;
import model.Game;
import model.GamePhase;
import model.Player;
import model.exceptions.GameException;
import view.GameUI;

public class GameController {
    private final Game game;
    private final GameUI ui;

    public GameController(Game game, GameUI ui) {
        this.game = game;
        this.ui = ui;
    }

    public void startGame() {
        game.start();
        gameLoop();
    }

    private void gameLoop() {
        while (game.getPhase() != GamePhase.GAME_OVER) {
            try {
                switch (game.getPhase()) {
                    case EXPLOSION_PHASE:
                        handleExplosionInput();
                        break;

                    case NOPE_PHASE:
                        handleNopePhase();
                        break;

                    case NORMAL:
                    default:
                        handleNormalTurn();
                        break;
                }

            } catch (GameException e) {
                ui.onException(e);
            } catch (Exception e) {
                ui.onGameMessage("CRITICAL ERROR: " + e.getMessage());
            }
        }

        ui.onGameMessage("Game Loop Ended. Thanks for playing!");
    }

    private void handleNormalTurn() {
        while (true) {
            ui.displayHand(game.getCurrentPlayer());
            String input = ui.promptCommand();

            if (input.equals("D")) {
                game.drawCard();
                return;
            }
            else if (input.startsWith("P")) {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    ui.onGameMessage("Invalid format. Usage: P <cardIndex>");
                } else {
                    int cardIdx = Integer.parseInt(parts[1]);
                    game.playCard(game.getCurrentPlayer().getId(), cardIdx);
                    return;
                }
            }
            else {
                ui.onGameMessage("Unknown command. Please enter 'D' or 'P <index>'.");
            }
        }
    }

    private void handleExplosionInput() {
        int index = ui.promptInsertionIndex(game.getDeck().getDeckSize());
        game.handleExplosionInsert(index);
    }

    private void handleNopePhase() {
        for (Player p : game.getPlayers()) {
            if (p.getDead() || !p.hasCard(CardType.NOPE)) {
                continue;
            }

            int currentNopes = game.getPendingAction().getNopeCount();
            boolean isCancelled = (currentNopes % 2 != 0);

            String statusMsg = isCancelled
                    ? "The action is currently BLOCKED. Do you want to NOPE the NOPE? (Revive the action)"
                    : "Do you want to CANCEL the action?";

            boolean wantsToNope = ui.promptPlayNope(p.getId(), statusMsg);

            if (wantsToNope) {
                int cardIdx = p.getNopeIndex();
                if (cardIdx != -1) {
                    game.playCard(p.getId(), cardIdx);
                    ui.onGameMessage("   >> NOPE played! Moving to next player...");
                }
            }
        }

        ui.onGameMessage("Nope phase finished. Resolving action...");
        game.resolvePendingAction();
    }
}
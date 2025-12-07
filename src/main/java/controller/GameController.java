package controller;

import model.Game;
import model.GamePhase;
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
                        boolean keepPlaying = handleNormalTurn();
                        if (!keepPlaying) return;
                        break;
                }

            } catch (GameException e) {
                ui.onException(e);
            } catch (Exception e) {
                ui.onGameMessage("CRITICAL ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }

        ui.onGameMessage("Game Loop Ended. Thanks for playing!");
    }

    private boolean handleNormalTurn() {
        ui.displayHand(game.getCurrentPlayer());
        String input = ui.promptCommand();

        if (input.equals("D")) {
            game.drawCard();
        }
        else if (input.startsWith("P")) {
            try {
                String[] parts = input.split(" ");
                if (parts.length < 2) {
                    ui.onGameMessage("Invalid format. Usage: P <cardIndex>");
                } else {
                    int cardIdx = Integer.parseInt(parts[1]);
                    game.playCard(game.getCurrentPlayer().getId(), cardIdx);
                }
            } catch (Exception e) {
                ui.onGameMessage("Invalid input.");
            }
        }
        else {
            ui.onGameMessage("Unknown command.");
        }

        return true;
    }

    private void handleExplosionInput() {
        int index = ui.promptInsertionIndex();
        game.handleExplosionInsert(index);
    }

    private void handleNopePhase() {
        boolean nopePlayed = false;

        for (model.Player p : game.getPlayers()) {
            if (p.getDead() || !p.hasCard(model.CardType.NOPE)) {
                continue;
            }

            boolean wantsToNope = ui.promptPlayNope(p.getId());
            if (wantsToNope) {
                int cardIdx = p.getNopeIndex();
                if (cardIdx != -1) {
                    game.playCard(p.getId(), cardIdx);
                    return;
                }
            }
        }

        if (!nopePlayed) {
            ui.onGameMessage("No Nopes played. Resolving action...");
            game.handleNopeInput(false);
        }
    }
}
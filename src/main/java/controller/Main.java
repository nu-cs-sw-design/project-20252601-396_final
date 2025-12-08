package controller;

import model.Player;
import model.Deck;
import model.Game;
import view.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        GameUI ui = new view.GameUI();
        Random rand = new Random();

        int numPlayers = ui.promptPlayerCount();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i, new ArrayList<>()));
        }

        Deck deck = new Deck(rand, new ArrayList<>());
        Game game = new Game(players, deck);

        game.registerObservers(ui);

        controller.GameController controller = new controller.GameController(game, ui);
        controller.startGame();
    }
}
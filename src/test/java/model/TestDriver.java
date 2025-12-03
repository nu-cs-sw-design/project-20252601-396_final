//package model;
//import view.GameUI;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class TestDriver {
//    public static void main(String[] args) {
//        System.out.println("=== STARTING TEST ===");
//
//        // Setup Data
//        Deck deck = new Deck(new Random());
//        List<Player> players = new ArrayList<>();
//        players.add(new Player(0, new ArrayList<>()));
//        players.add(new Player(1, new ArrayList<>()));
//
//        // Initialize Game
//        System.out.println("\n--- TEST: INIT GAME ---");
//        Game game = new Game(players, deck);
//
//        // Register the Logger
//        System.out.println("\n--- TEST: Register the Logger ---");
//        game.registerObservers(new GameUI());
//
//        // Start the Game
//        System.out.println("\n--- TEST: START GAME ---");
//        game.start();
//
//        // 5. Test Drawing a Card
//        System.out.println("\n--- TEST: DRAW CARD ---");
//        try {
//            game.drawCard();
//        } catch (Exception e) {
//            System.out.println("Exception: " + e.getMessage());
//        }
//
//        // Test Playing a Shuffle
//        System.out.println("\n--- TEST: MANUAL SHUFFLE PLAY ---");
//
//        Player p1 = players.get(1);
//        Card shuffleCard = CardFactory.createCard(CardType.SHUFFLE);
//        p1.addCard(shuffleCard);
//        int cardIndex = p1.getHandSize() - 1; // last card we just added
//
//        try {
//            game.playCard(1, cardIndex);
//        } catch (Exception e) {
//            System.out.println("Expected Error (Turn Check): " + e.getMessage());
//        }
//
//        // Test Explosion
//        System.out.println("\n--- TEST: FORCED EXPLOSION ---");
//        Card kitten = CardFactory.createCard(CardType.EXPLODING_KITTEN);
//        deck.insertAt(kitten, deck.getDeckSize());
//
//        try {
//            // Whoever's turn it is draws the card
//            int currentPlayer = game.getCurrentPlayer().getId();
//            System.out.println("Player " + currentPlayer + " is about to draw a rigged kitten...");
//            game.drawCard();
//
//            // If they have Defuse -> "Used Defuse" -> "Where to insert?"
//            // If no Defuse -> "BOOM"
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}
package model;

import model.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

public class Game implements GameContext {
    private List<Player> players;
    private Deck deck;
    private List<GameObserver> observers;
    private int currentTurn;
    private GamePhase phase;
    private PendingAction pendingAction;

    public Game(List<Player> players, Deck deck) {
        this.players = players;
        this.deck = deck;
        this.observers = new ArrayList<>();
        this.currentTurn = 0;
        this.phase = GamePhase.NORMAL;
    }
    @Override
    public Deck getDeck(){
        return deck;
    }
    @Override
    public Player getCurrentPlayer(){
        return players.get(currentTurn);
    }
    @Override
    public GamePhase getPhase(){
        return phase;
    }
    @Override
    public PendingAction getPendingAction() {
        return pendingAction;
    }
    @Override
    public void registerObservers(GameObserver observer){
        observers.add(observer);
    }
    @Override
    public void notifyObservers(String msg){
        for (GameObserver obs : observers) {
            obs.onGameMessage(msg);
        }
    }

    public void start(){
        // Give Defuse to players
        for (Player p : players) {
            p.addCard(CardFactory.createCard(CardType.DEFUSE));
        }

        // Add 4 NopeCard
        final int cardAddedFourTimes = 4;
        for (int i = 0; i < cardAddedFourTimes; i++) {
            deck.insertAt(0, CardFactory.createCard(CardType.NOPE));
            deck.insertAt(0, CardFactory.createCard(CardType.SHUFFLE));
            deck.insertAt(0, CardFactory.createCard(CardType.SWAP_TOP_AND_BOTTOM));
        }

        // Shuffle Deck
        deck.shuffle();

        // Deal 4 cards each
        for (int i = 0; i < 4; i++) {
            for (Player p : players) {
                if (deck.getDeckSize() > 0) {
                    p.addCard(deck.draw());
                }
            }
        }

        // Insert Exploding Kittens (Players - 1)
        int kittensToInsert = players.size() - 1;
        for (int i = 0; i < kittensToInsert; i++) {
            deck.insertAt(0, CardFactory.createCard(CardType.EXPLODING_KITTEN));
        }

        deck.shuffle();

        notifyObservers("Game Started! Deck shuffled and hands dealt.");
        notifyObservers("Player " + getCurrentPlayer().getId() + "'s turn.");
        for (GameObserver obs : observers) {
            obs.onTurnChanged(getCurrentPlayer().getId());
        }
    }

    private void nextTurn() {
        int start = currentTurn;

        do {
            currentTurn = (currentTurn + 1) % players.size();
        } while (players.get(currentTurn).getDead() && currentTurn != start);

        notifyObservers("It is now Player " + currentTurn + "'s turn.");
        for (GameObserver obs : observers) {
            obs.onTurnChanged(currentTurn);
        }
    }

    private void checkGameOver() {
        long aliveCount = players.stream().filter(p -> !p.getDead()).count();
        if (aliveCount <= 1) {
            phase = GamePhase.GAME_OVER;

            Player winner = players.stream()
                    .filter(p -> !p.getDead())
                    .findFirst()
                    .orElse(null);

            String winnerId = (winner != null) ? String.valueOf(winner.getId()) : "Unknown";
            notifyObservers("GAME OVER! We have a winner: Player " + winnerId);
        }
    }

    private void handleExplosionLogic(Player player) {
        if (player.hasCard(CardType.DEFUSE)) {
            player.removeDefuse();
            notifyObservers("Player " + player.getId() + " used a Defuse!");
            for (GameObserver obs : observers) {
                obs.onDefuseUsed(player.getId());
                obs.onRequestExplosionInsertIndex();
            }
        } else {
            notifyObservers("BOOM! Player " + player.getId() + " exploded!");
            player.setDead(true);

            for (GameObserver obs : observers) {
                obs.onPlayerEliminated(player.getId());
            }

            checkGameOver();

            if (phase != GamePhase.GAME_OVER) {
                phase = GamePhase.NORMAL;
                nextTurn();
            }
        }
    }

    public void drawCard(){
        int playerIdx = getCurrentPlayer().getId();
        Player currentPlayer = getCurrentPlayer();

        if (phase != GamePhase.NORMAL) {
            throw new InvalidMoveException("Cannot draw cards right now.");
        }

        Card drawnCard = deck.draw();
        notifyObservers("Player " + playerIdx + " drew " + drawnCard.getType());

        if (drawnCard.getType() == CardType.EXPLODING_KITTEN) {
            phase = GamePhase.EXPLOSION_PHASE;
            handleExplosionLogic(currentPlayer);
        } else {
            currentPlayer.addCard(drawnCard);
            nextTurn();
        }
    }

    public void playCard(int playerIdx, int cardIdx){
        Player player = players.get(playerIdx);
        Card card = player.getCard(cardIdx);
        CardAction action = card.getAction();

        // NOPE
        if (card.getType() == CardType.NOPE) {
            if (phase != GamePhase.NOPE_PHASE) {
                throw new InvalidMoveException("You can only play Nope when an action is pending.");
            }

            card.performAction(this);

            player.removeCard(cardIdx);
            return;
        }

        // Standard Cards. Must be your turn, Game Phase must be NORMAL
        if (playerIdx != currentTurn) {
            throw new InvalidMoveException("It is not your turn!");
        }
        if (phase != GamePhase.NORMAL) {
            throw new InvalidMoveException("Cannot play cards right now. Wait for the current action to resolve.");
        }

        // Passive Cards (Defuse, Exploding Kitten)
        if (action instanceof ActionPassive) {
            card.performAction(this);
            return;
        }

        // Active Cards (Shuffle, Swap Top/Bottom)
        // Change game state, must go through "Pending/Nope"
        player.removeCard(cardIdx);
        this.pendingAction = new PendingAction(action, playerIdx);
        this.phase = GamePhase.NOPE_PHASE;

        // Notify Observers to start the Nope Loop
        notifyObservers("Player " + playerIdx + " played " + card.getType() + ". Checking for Nopes...");

        for (GameObserver obs : observers) {
            obs.onCardPlayed(playerIdx, card);
            // This tells the UI/Controller to ask everyone if they want to Nope
            obs.onRequestNope(playerIdx, card.getType());
        }
    }

    private void resolvePendingAction() {
        if (pendingAction == null) return;

        pendingAction.resolve(this);

        // Reset
        pendingAction = null;
        phase = GamePhase.NORMAL;
    }

    public void handleNopeInput(boolean wantsToNope){
        if (!wantsToNope) {
            resolvePendingAction();
        }
    }

    public void handleExplosionInsert(int index){
        if (phase != GamePhase.EXPLOSION_PHASE) {
            throw new InvalidMoveException("Not in explosion phase.");
        }

        // Insert exploding kitten back into deck
        deck.insertAt(index, CardFactory.createCard(CardType.EXPLODING_KITTEN));
        notifyObservers("Exploding Kitten re-inserted into the deck.");

        // Resume Game
        phase = GamePhase.NORMAL;
        nextTurn();
    }

    // strictly used only for test
    public void setPhase(GamePhase phase){
        this.phase = phase;
    }
    // strictly used only for test
    public void setPendingAction(PendingAction pendingAction){
        this.pendingAction = pendingAction;
    }
}

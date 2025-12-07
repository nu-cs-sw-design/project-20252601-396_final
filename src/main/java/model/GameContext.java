package model;


import java.util.List;

public interface GameContext {
    public Deck getDeck();
    public Player getCurrentPlayer();
    public List<Player> getPlayers();
    public GamePhase getPhase();
    public PendingAction getPendingAction();

    public void registerObservers(GameObserver observer);
    public void notifyObservers(String msg);
}

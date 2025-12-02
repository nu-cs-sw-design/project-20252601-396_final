package model;


public interface GameContext {
    public Deck getDeck();
    public Player getCurrentPlayer();
    public GamePhase getPhase();
    public PendingAction getPendingAction();

    public void registerObservers(GameObserver observer);
    public void notifyObservers(String msg);
}

package model;


public interface GameContext {
    public Deck getDeck();
    public Player getCurrentPlayer();
    public GamePhase getPhase();
    public void notifyObservers(String msg);
}

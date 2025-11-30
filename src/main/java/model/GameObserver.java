package model;


import model.exceptions.GameException;

public interface GameObserver {
    public void onGameMessage(String message);
    public void onTurnChanged(int playerIdx);
    public void onCardPlayed(int playerIdx, Card card);
    public void onRequestNope(int actionPlayerIdx, CardType cardType);
    public void onExplosionRisk(int playerIdx);
    public void onRequestExplosionInsertIndex();
    public void onDefuseUsed(int playerIdx);
    public void onPlayerEliminated(int playerIdx);
    public void onActionCancelled(String message);
    public void onException(GameException e);
}

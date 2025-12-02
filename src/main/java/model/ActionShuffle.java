package model;

public class ActionShuffle implements CardAction{
    public void execute(GameContext ctx) {
        ctx.getDeck().shuffle();
        ctx.notifyObservers("Deck shuffled");
    }
}

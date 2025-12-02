package model;

import model.exceptions.InvalidMoveException;

public class Card {
    private final CardType type;
    private final CardAction action;

    public Card(CardType type, CardAction action) {
        this.type = type;
        this.action = action;
    }
    public void performAction(GameContext ctx) {
        action.execute(ctx);
    }
    public CardType getType() {
        return type;
    }
    public CardAction getAction() {
        return action;
    }
    // for debugging and display
    @Override
    public String toString() {
        return type.toString();
    }
}

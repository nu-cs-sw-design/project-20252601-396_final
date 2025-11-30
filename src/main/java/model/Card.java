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
        if (action != null) {
            action.execute(ctx);
        } else {
            throw new InvalidMoveException("Card: " + type.toString() + ", cannot perform action.");
        }
    }
    public CardType getType() {
        return type;
    }

    // for debugging and display
    @Override
    public String toString() {
        return type.toString();
    }
}

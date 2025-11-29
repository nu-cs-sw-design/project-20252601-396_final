package model.exceptions;

public class NotEnoughCardsException extends GameException {
    public NotEnoughCardsException(int actual) {
        super("Not enough cards to perform action. Needed at least 2, but deck has " + actual + ".");
    }
}

package model.exceptions;

public class EmptyDeckException extends GameException {
    public EmptyDeckException() {
        super("Cannot draw: The deck is empty.");
    }
}

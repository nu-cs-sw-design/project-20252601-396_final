package model.exceptions;

public class InvalidMoveException extends GameException {
    public InvalidMoveException(String message){
        super("Invalid move detected: " + message);
    }
}

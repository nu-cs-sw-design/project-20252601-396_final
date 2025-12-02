package model;

public class ActionPassive implements CardAction{
    private final String message;

    public ActionPassive(String message) {
        this.message = message;
    }

    @Override
    public void execute(GameContext context) {
        context.notifyObservers(message);
    }
}

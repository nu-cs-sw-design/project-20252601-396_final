package model;

public class PendingAction {
    private final CardAction action;
    private final int initiatingPlayerIndex;
    private int nopeCount;

    public PendingAction(CardAction action, int initiatingPlayerIndex) {
        this.action = action;
        this.initiatingPlayerIndex = initiatingPlayerIndex;
        this.nopeCount = 0;
    }

    public void addNope() {
        this.nopeCount++;
    }

    public int getNopeCount() {
        return nopeCount;
    }

    // Called by the Game when the Nope Phase ends. Decides whether to execute the original action or not.
    public void resolve(GameContext context) {
        // Even number of Nopes -> Action succeeds
        if (nopeCount % 2 == 0) {
            context.notifyObservers("Action confirmed! Executing...");
            action.execute(context);
        } else {
            // Odd number of Nopes -> Action cancelled
            context.notifyObservers("Action was Noped! Nothing happens.");
        }
    }
}

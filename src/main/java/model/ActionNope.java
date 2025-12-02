package model;

import model.exceptions.InvalidMoveException;

public class ActionNope implements CardAction {

    @Override
    public void execute(GameContext context) {
        // 1. Can only play Nope if there is a Pending Action in the NOPE_PHASE.
        if (context.getPhase() != GamePhase.NOPE_PHASE || context.getPendingAction() == null) {
            throw new InvalidMoveException("You can only play a Nope card when another action is pending.");
        }

        // 2. Add Nope to the pile
        PendingAction pending = context.getPendingAction();
        pending.addNope();

        // 3. Calculate the current state (Odd/Even) to give clear feedback
        boolean isCancelled = (pending.getNopeCount() % 2 != 0);
        String status = isCancelled ? "PAUSED/CANCELLED" : "ACTIVE";

        context.notifyObservers("A Nope was played! Total Nopes: " + pending.getNopeCount() + ". Action is now " + status + ".");
    }
}
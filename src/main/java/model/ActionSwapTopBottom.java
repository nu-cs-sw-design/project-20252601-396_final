package model;

import model.exceptions.NotEnoughCardsException;

public class ActionSwapTopBottom implements CardAction{
    @Override
    public void execute(GameContext ctx) {
        if (ctx.getDeck().getDeckSize() < 2) {
            throw new NotEnoughCardsException(ctx.getDeck().getDeckSize());
        }
        ctx.getDeck().swapTopAndBottom();
    }
}

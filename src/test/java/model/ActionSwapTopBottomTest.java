package model;

import model.exceptions.NotEnoughCardsException;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionSwapTopBottomTest {
    @Test
    public void testExecuteSwapsWhenDeckHasEnoughCards() {
        GameContext context = EasyMock.createMock(GameContext.class);
        Deck deck = EasyMock.createMock(Deck.class);
        ActionSwapTopBottom action = new ActionSwapTopBottom();

        EasyMock.expect(context.getDeck()).andReturn(deck).anyTimes();
        EasyMock.expect(deck.getDeckSize()).andReturn(2);
        deck.swapTopAndBottom();
        EasyMock.expectLastCall();
        EasyMock.replay(context, deck);

        action.execute(context);

        EasyMock.verify(context, deck);
    }

    @Test
    public void testExecuteThrowsExceptionWhenNotEnoughCards() {
        GameContext context = EasyMock.createMock(GameContext.class);
        Deck deck = EasyMock.createMock(Deck.class);
        ActionSwapTopBottom action = new ActionSwapTopBottom();

        EasyMock.expect(context.getDeck()).andStubReturn(deck);
        EasyMock.expect(deck.getDeckSize()).andStubReturn(1);
        EasyMock.replay(context, deck);

        assertThrows(NotEnoughCardsException.class, () -> {
            action.execute(context);
        });

        EasyMock.verify(context, deck);
    }
}

package model;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class ActionShuffleTest {
    @Test
    public void testExecuteShufflesDeckAndNotifies() {
        GameContext context = EasyMock.createMock(GameContext.class);
        Deck deck = EasyMock.createMock(Deck.class);

        ActionShuffle action = new ActionShuffle();

        EasyMock.expect(context.getDeck()).andReturn(deck);
        deck.shuffle();
        EasyMock.expectLastCall();
        context.notifyObservers("Deck shuffled");
        EasyMock.expectLastCall();
        EasyMock.replay(context, deck);

        action.execute(context);

        EasyMock.verify(context, deck);
    }
}

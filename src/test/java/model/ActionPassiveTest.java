package model;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class ActionPassiveTest {
    @Test
    public void testExecuteNotifiesObserversWithMessage() {
        GameContext context = EasyMock.createMock(GameContext.class);
        String expectedMessage = "Exploding Kitten Incoming!";


        ActionPassive action = new ActionPassive(expectedMessage);

        context.notifyObservers(expectedMessage);
        EasyMock.expectLastCall();
        EasyMock.replay(context);

        action.execute(context);

        EasyMock.verify(context);
    }

    @Test
    public void testExecuteWithEmptyMessage() {
        GameContext context = EasyMock.createMock(GameContext.class);
        String emptyMessage = "";

        ActionPassive action = new ActionPassive(emptyMessage);

        context.notifyObservers(emptyMessage);
        EasyMock.replay(context);

        action.execute(context);

        EasyMock.verify(context);
    }
}

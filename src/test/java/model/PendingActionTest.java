package model;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingActionTest {
    @Test
    public void testAddNopeIncrementsCount() {
        CardAction mockAction = EasyMock.createMock(CardAction.class);
        EasyMock.replay(mockAction);

        PendingAction pending = new PendingAction(mockAction, 1);
        assertEquals(0, pending.getNopeCount());

        pending.addNope();

        assertEquals(1, pending.getNopeCount());
    }

    @Test
    public void testAddNopeMultipleTimes() {
        CardAction mockAction = EasyMock.createMock(CardAction.class);
        EasyMock.replay(mockAction);

        PendingAction pending = new PendingAction(mockAction, 1);

        pending.addNope();
        pending.addNope();
        pending.addNope();

        assertEquals(3, pending.getNopeCount());
    }

    @Test
    public void testResolveExecutesActionWhenNoNopes() {
        CardAction action = EasyMock.createMock(CardAction.class);
        GameContext context = EasyMock.createMock(GameContext.class);

        PendingAction pending = new PendingAction(action, 1);

        context.notifyObservers(EasyMock.contains("Action confirmed"));
        EasyMock.expectLastCall();
        action.execute(context);
        EasyMock.expectLastCall();
        EasyMock.replay(action, context);

        pending.resolve(context);

        EasyMock.verify(action, context);
    }

    @Test
    public void testResolveCancelsActionWhenOneNope() {
        CardAction action = EasyMock.createMock(CardAction.class);
        GameContext context = EasyMock.createMock(GameContext.class);

        PendingAction pending = new PendingAction(action, 1);
        pending.addNope();

        context.notifyObservers(EasyMock.contains("Action was Noped"));
        EasyMock.expectLastCall();
        EasyMock.replay(action, context);

        pending.resolve(context);

        EasyMock.verify(action, context);
    }

    @Test
    public void testResolveExecutesActionWhenTwoNopes() {
        CardAction action = EasyMock.createMock(CardAction.class);
        GameContext context = EasyMock.createMock(GameContext.class);

        PendingAction pending = new PendingAction(action, 1);
        pending.addNope();
        pending.addNope();

        context.notifyObservers(EasyMock.contains("Action confirmed"));
        action.execute(context);
        EasyMock.replay(action, context);

        pending.resolve(context);

        EasyMock.verify(action, context);
    }
}

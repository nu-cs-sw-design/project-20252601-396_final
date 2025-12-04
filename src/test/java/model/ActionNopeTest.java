package model;

import model.exceptions.InvalidMoveException;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActionNopeTest {
    @Test
    public void testExecuteAddsNopeAndCancelsAction() {
        GameContext context = EasyMock.createMock(GameContext.class);
        PendingAction pending = EasyMock.createMock(PendingAction.class);
        ActionNope action = new ActionNope();

        EasyMock.expect(context.getPhase()).andStubReturn(GamePhase.NOPE_PHASE);
        EasyMock.expect(context.getPendingAction()).andStubReturn(pending);
        pending.addNope();
        EasyMock.expectLastCall();
        EasyMock.expect(pending.getNopeCount()).andStubReturn(1);
        context.notifyObservers(EasyMock.contains("PAUSED/CANCELLED"));
        EasyMock.expectLastCall();
        EasyMock.replay(context, pending);

        action.execute(context);

        EasyMock.verify(context, pending);
    }

    @Test
    public void testExecuteAddsNopeAndResumesAction() {
        GameContext context = EasyMock.createMock(GameContext.class);
        PendingAction pending = EasyMock.createMock(PendingAction.class);
        ActionNope action = new ActionNope();

        EasyMock.expect(context.getPhase()).andStubReturn(GamePhase.NOPE_PHASE);
        EasyMock.expect(context.getPendingAction()).andStubReturn(pending);
        pending.addNope();
        EasyMock.expectLastCall();
        EasyMock.expect(pending.getNopeCount()).andStubReturn(2);
        context.notifyObservers(EasyMock.contains("ACTIVE"));
        EasyMock.expectLastCall();
        EasyMock.replay(context, pending);

        action.execute(context);

        EasyMock.verify(context, pending);
    }

    @Test
    public void testExecuteThrowsExceptionIfWrongPhase() {
        GameContext context = EasyMock.createMock(GameContext.class);
        ActionNope action = new ActionNope();

        EasyMock.expect(context.getPhase()).andStubReturn(GamePhase.NORMAL);
        EasyMock.replay(context);

        Exception e = assertThrows(InvalidMoveException.class, () -> {
            action.execute(context);
        });

        assertEquals("Invalid move detected: You can only play a Nope card when another action is pending.", e.getMessage());
        EasyMock.verify(context);
    }

    @Test
    public void testExecuteThrowsExceptionIfNoPendingAction() {
        GameContext context = EasyMock.createMock(GameContext.class);
        ActionNope action = new ActionNope();

        EasyMock.expect(context.getPhase()).andStubReturn(GamePhase.NOPE_PHASE);
        EasyMock.expect(context.getPendingAction()).andStubReturn(null);

        EasyMock.replay(context);

        Exception e = assertThrows(InvalidMoveException.class, () -> {
            action.execute(context);
        });

        assertEquals("Invalid move detected: You can only play a Nope card when another action is pending.", e.getMessage());
        EasyMock.verify(context);
    }
}

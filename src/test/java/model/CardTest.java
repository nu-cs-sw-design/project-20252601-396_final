package model;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
	@ParameterizedTest
	@EnumSource(names = {"NOPE", "DEFUSE", "SHUFFLE",
			"EXPLODING_KITTEN", "SWAP_TOP_AND_BOTTOM"
	})
	public void testPerformAction(CardType cardType) {
		GameContext ctx = EasyMock.createMock(GameContext.class);
		CardAction action = EasyMock.createMock(CardAction.class);
		Card card = new Card(cardType, action);

		action.execute(ctx);
		EasyMock.expectLastCall();
		EasyMock.replay(ctx, action);

		card.performAction(ctx);
		EasyMock.verify(ctx, action);
	}

	@ParameterizedTest
	@EnumSource(names = {"NOPE", "DEFUSE", "SHUFFLE",
			"EXPLODING_KITTEN", "SWAP_TOP_AND_BOTTOM"
	})
	public void testGetType(CardType cardType) {
		CardAction action = EasyMock.createMock(CardAction.class);
		Card card = new Card(cardType, action);
		EasyMock.replay(action);

		assertEquals(card.getType(), cardType);
		EasyMock.verify(action);
	}

	@ParameterizedTest
	@EnumSource(names = {"NOPE", "DEFUSE", "SHUFFLE",
			"EXPLODING_KITTEN", "SWAP_TOP_AND_BOTTOM"
	})
	public void testGetAction(CardType cardType) {
		CardAction action = EasyMock.createMock(CardAction.class);
		Card card = new Card(cardType, action);
		EasyMock.replay(action);

		assertEquals(card.getAction(), action);
		EasyMock.verify(action);
	}

}

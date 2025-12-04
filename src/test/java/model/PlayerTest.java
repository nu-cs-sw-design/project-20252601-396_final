package model;

import model.exceptions.InvalidMoveException;
import model.exceptions.NotEnoughCardsException;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
	@ParameterizedTest
	@EnumSource(names = {"NOPE", "DEFUSE", "SHUFFLE",
			"EXPLODING_KITTEN", "SWAP_TOP_AND_BOTTOM"
	})
	public void testHasCardTrue(CardType cardType) {
		CardAction action = EasyMock.createMock(CardAction.class);
		Card card = EasyMock.createMock(Card.class);
		ArrayList<Card> hands = new ArrayList<>(Arrays.asList(card));
		Player player = new Player(0, hands);

		EasyMock.expect(card.getType()).andReturn(cardType);
		EasyMock.replay(action, card);

		assertTrue(player.hasCard(cardType));
		EasyMock.verify(action, card);
	}

	@ParameterizedTest
	@EnumSource(names = {"NOPE", "DEFUSE", "SHUFFLE",
			"EXPLODING_KITTEN", "SWAP_TOP_AND_BOTTOM"
	})
	public void testHasEmptyCard(CardType cardType) {
		CardAction action = EasyMock.createMock(CardAction.class);
		ArrayList<Card> hands = new ArrayList<>();
		Player player = new Player(0, hands);

		EasyMock.replay(action);

		assertFalse(player.hasCard(cardType));
		EasyMock.verify(action);
	}

	@Test
	public void testHasCardFalse() {
		CardAction action = EasyMock.createMock(CardAction.class);
		Card card = EasyMock.createMock(Card.class);
		ArrayList<Card> hands = new ArrayList<>(Arrays.asList(card));
		Player player = new Player(0, hands);

		EasyMock.expect(card.getType()).andReturn(CardType.NOPE);
		EasyMock.replay(action, card);

		assertFalse(player.hasCard(CardType.DEFUSE));
		EasyMock.verify(action, card);
	}
	@Test
	public void testRemoveDefuseSuccess() {
		Card nopeCard = EasyMock.createMock(Card.class);
		Card defuseCard = EasyMock.createMock(Card.class);

		EasyMock.expect(nopeCard.getType()).andStubReturn(CardType.NOPE);
		EasyMock.expect(defuseCard.getType()).andStubReturn(CardType.DEFUSE);
		EasyMock.replay(nopeCard, defuseCard);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(nopeCard, defuseCard));
		Player player = new Player(0, hand);

		player.removeDefuse();

		assertEquals(1, player.getHandSize());
		assertTrue(player.hasCard(CardType.NOPE));
		assertFalse(player.hasCard(CardType.DEFUSE));

		EasyMock.verify(nopeCard, defuseCard);
	}

	@Test
	public void testRemoveDefuseRemovesOnlyFirstInstance() {
		Card defuse1 = EasyMock.createMock(Card.class);
		Card defuse2 = EasyMock.createMock(Card.class);

		EasyMock.expect(defuse1.getType()).andStubReturn(CardType.DEFUSE);
		EasyMock.expect(defuse2.getType()).andStubReturn(CardType.DEFUSE);
		EasyMock.replay(defuse1, defuse2);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(defuse1, defuse2));
		Player player = new Player(0, hand);

		player.removeDefuse();

		assertEquals(1, player.getHandSize());
		assertEquals(defuse2.getType(), player.getCard(0).getType());

		EasyMock.verify(defuse1, defuse2);
	}

	@Test
	public void testRemoveDefuseThrowsExceptionOnEmptyHand() {
		ArrayList<Card> emptyHand = new ArrayList<>();
		Player player = new Player(0, emptyHand);

		Exception exception = assertThrows(NotEnoughCardsException.class, () -> {
			player.removeDefuse();
		});

		String expectedMessage = "Not enough cards to perform action. Needed at least 2, but deck has 0.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	public void testRemoveDefuseDoingNothingIfNoDefusePresent() {
		Card c1 = EasyMock.createMock(Card.class);
		Card c2 = EasyMock.createMock(Card.class);

		EasyMock.expect(c1.getType()).andStubReturn(CardType.SHUFFLE);
		EasyMock.expect(c2.getType()).andStubReturn(CardType.NOPE);

		EasyMock.replay(c1, c2);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1, c2));
		Player player = new Player(0, hand);

		player.removeDefuse();

		assertEquals(2, hand.size());
		EasyMock.verify(c1, c2);
	}
	@Test
	public void testRemoveCardValidIndex() {
		Card c1 = EasyMock.createMock(Card.class);
		Card c2 = EasyMock.createMock(Card.class);
		Card c3 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1, c2, c3);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1, c2, c3));
		Player player = new Player(0, hand);

		Card removedCard = player.removeCard(1);

		assertEquals(c2, removedCard);
		assertEquals(2, player.getHandSize());
		assertEquals(c1, player.getCard(0));
		assertEquals(c3, player.getCard(1));

		EasyMock.verify(c1, c2, c3);
	}

	@Test
	public void testRemoveCardFirstIndex() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1));
		Player player = new Player(0, hand);

		Card removedCard = player.removeCard(0);

		assertEquals(c1, removedCard);
		assertEquals(0, player.getHandSize());
		EasyMock.verify(c1);
	}

	@Test
	public void testRemoveCardThrowsExceptionIndexTooLarge() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1));
		Player player = new Player(0, hand);

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			player.removeCard(1);
		});

		 String expectedMessage = "Invalid move detected: Index out of bounds.";
		 assertEquals(expectedMessage, exception.getMessage());

		EasyMock.verify(c1);
	}

	@Test
	public void testRemoveCardThrowsExceptionNegativeIndex() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1));
		Player player = new Player(0, hand);

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			player.removeCard(-1);
		});

		String expectedMessage = "Invalid move detected: Index out of bounds.";
		assertEquals(expectedMessage, exception.getMessage());

		EasyMock.verify(c1);
	}

	@Test
	public void testGetCardValidIndex() {
		Card c1 = EasyMock.createMock(Card.class);
		Card c2 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1, c2);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1, c2));
		Player player = new Player(0, hand);

		Card retrievedCard = player.getCard(1);

		assertEquals(c2, retrievedCard);
		assertEquals(2, player.getHandSize());

		EasyMock.verify(c1, c2);
	}

	@Test
	public void testGetCardThrowsExceptionIndexTooLarge() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1));
		Player player = new Player(0, hand);

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			player.getCard(1);
		});

		String expectedMessage = "Invalid move detected: Index out of bounds.";
		assertEquals(expectedMessage, exception.getMessage());

		EasyMock.verify(c1);
	}

	@Test
	public void testGetCardThrowsExceptionNegativeIndex() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1));
		Player player = new Player(0, hand);

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			player.getCard(-1);
		});

		String expectedMessage = "Invalid move detected: Index out of bounds.";
		assertEquals(expectedMessage, exception.getMessage());

		EasyMock.verify(c1);
	}

	@Test
	public void testGetCardThrowsExceptionEmptyHand() {
		ArrayList<Card> hand = new ArrayList<>();
		Player player = new Player(0, hand);

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			player.getCard(0);
		});

		String expectedMessage = "Invalid move detected: Index out of bounds.";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	public void testAddCardToEmptyHand() {
		Card c1 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1);

		ArrayList<Card> hand = new ArrayList<>();
		Player player = new Player(0, hand);

		player.addCard(c1);

		assertEquals(1, player.getHandSize());
		assertEquals(c1, player.getCard(0));
		EasyMock.verify(c1);
	}

	@Test
	public void testAddCardAppendsToEnd() {
		Card existingCard = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		EasyMock.replay(existingCard, newCard);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(existingCard));
		Player player = new Player(0, hand);

		player.addCard(newCard);

		assertEquals(2, player.getHandSize());
		assertEquals(existingCard, player.getCard(0));
		assertEquals(newCard, player.getCard(1));

		EasyMock.verify(existingCard, newCard);
	}

	@Test
	public void testGetHandSizeEmpty() {
		ArrayList<Card> hand = new ArrayList<>();
		Player player = new Player(0, hand);

		assertEquals(0, player.getHandSize());
	}

	@Test
	public void testGetHandSizePopulated() {
		Card c1 = EasyMock.createMock(Card.class);
		Card c2 = EasyMock.createMock(Card.class);
		Card c3 = EasyMock.createMock(Card.class);
		EasyMock.replay(c1, c2, c3);

		ArrayList<Card> hand = new ArrayList<>(Arrays.asList(c1, c2, c3));
		Player player = new Player(0, hand);

		assertEquals(3, player.getHandSize());

		EasyMock.verify(c1, c2, c3);
	}

	@Test
	public void testGetHandSizeAfterConstructorHandlesNull() {
		Player player = new Player(0, null);

		assertEquals(0, player.getHandSize());
	}

	@Test
	public void testSetDeadToTrue() {
		ArrayList<Card> hand = new ArrayList<>();
		Player player = new Player(0, hand);

		assertFalse(player.getDead());

		player.setDead(true);

		assertTrue(player.getDead());
	}

	@Test
	public void testSetDeadToFalse() {
		ArrayList<Card> hand = new ArrayList<>();
		Player player = new Player(0, hand);

		player.setDead(true);
		assertTrue(player.getDead());

		player.setDead(false);

		assertFalse(player.getDead());
	}
}

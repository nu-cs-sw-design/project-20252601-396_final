package model;

import model.exceptions.InvalidMoveException;
import model.exceptions.NotEnoughCardsException;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTest
{
	@Test
	public void shuffleDeckTwoCards() {
		Random rand = EasyMock.createMock(Random.class);
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckOfSize2 =
				new ArrayList<>(Arrays.asList(secondCard, firstCard));
		EasyMock.expect(rand.nextInt(2)).andReturn(0);
		EasyMock.replay(rand, firstCard, secondCard);
		Deck deck = new Deck(rand, deckOfSize2);

		deck.shuffle();

		ArrayList<Card> expectedDeck =
				new ArrayList<>(Arrays.asList(firstCard, secondCard));
		assertEquals(expectedDeck, deck.getCards());
		EasyMock.verify(rand, firstCard, secondCard);
	}

	@Test
	public void shuffleDeckZeroCards() {
		Random rand = EasyMock.createMock(Random.class);
		ArrayList<Card> deckOfSize2 = new ArrayList<>();
		EasyMock.replay(rand);
		Deck deck = new Deck(rand, deckOfSize2);

		deck.shuffle();

		assertEquals(0, deck.getDeckSize());
		EasyMock.verify(rand);
	}

	@Test
	public void shuffleDeckThreeCardsDupe() {
		Random rand = EasyMock.createMock(Random.class);
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);
		final int firstRandNumber = 3;
		final int secondRandNumber = 2;

		ArrayList<Card> deckOfSize3 =
				new ArrayList<>(Arrays.asList(firstCard, firstCard, secondCard));
		EasyMock.expect(rand.nextInt(firstRandNumber)).andReturn(0);
		EasyMock.expect(rand.nextInt(secondRandNumber)).andReturn(0);
		EasyMock.replay(rand, firstCard, secondCard);
		Deck deck = new Deck(rand, deckOfSize3);

		deck.shuffle();

		ArrayList<Card> expectedDeck =
				new ArrayList<>(Arrays.asList(firstCard, secondCard, firstCard));
		assertEquals(expectedDeck, deck.getCards());
		EasyMock.verify(rand, firstCard, secondCard);
	}

	@Test
	public void drawCardEmptyDeckFromBottomThrowsException() {
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.replay(rand);

		ArrayList<Card> emptyDeck = new ArrayList<>();
		Deck deck = new Deck(rand, emptyDeck);

		String expectedMessage  = "Invalid move detected: Cannot draw card from empty cards.";

		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			deck.draw();
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);

		EasyMock.verify(rand);
	}

	@Test
	public void drawCardOneCardDeckReturnsOnlyCard() {
		Card card1 = EasyMock.createMock(Card.class);
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.replay(card1, rand);

		ArrayList<Card> oneCardDeck = new ArrayList<>();
		oneCardDeck.add(card1);

		Deck deck = new Deck(rand, oneCardDeck);
		Card cardDrawn = deck.draw();
		assertEquals(cardDrawn, card1);
		assertEquals(0, deck.getDeckSize());

		EasyMock.verify(card1, rand);
	}

	@Test
	public void drawCardTwoCardsDeckReturnsSecondCard() {
		final int expectedDeckSize = 1;
		Card card1 = EasyMock.createMock(Card.class);
		Card card2 = EasyMock.createMock(Card.class);
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.replay(card1, card2, rand);

		ArrayList<Card> oneCardDeck = new ArrayList<>();
		oneCardDeck.add(card1);
		oneCardDeck.add(card2);

		Deck deck = new Deck(rand, oneCardDeck);
		Card cardDrawn = deck.draw();
		assertEquals(cardDrawn, card2);
		assertEquals(expectedDeckSize, deck.getDeckSize());

		EasyMock.verify(card1, card2, rand);
	}

	@Test
	public void drawCard42CardsDeckFromBottomReturnsFirstCard() {
		final int expectedDeckSize = 41;
		Random rand = EasyMock.createMock(Random.class);
		Card cardStub = EasyMock.createMock(Card.class);
		Card firstCard = EasyMock.createMock(Card.class);
		EasyMock.replay(rand, cardStub);

		ArrayList<Card> maxSizeDeck = new ArrayList<>
				(Collections.nCopies(expectedDeckSize, cardStub));
		maxSizeDeck.add(firstCard);

		Deck deck = new Deck(rand, maxSizeDeck);
		Card cardDrawn = deck.draw();
		assertEquals(cardDrawn, firstCard);
		assertEquals(expectedDeckSize, deck.getDeckSize());

		EasyMock.verify(cardStub, rand);
	}

	@Test
	public void insertAtStart(){
		Random rand = EasyMock.createMock(Random.class);
		Card existingCard = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(existingCard));
		EasyMock.replay(rand, existingCard, newCard);
		Deck deck = new Deck(rand, deckList);

		deck.insertAt(0, newCard);

		ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(newCard, existingCard));
		assertEquals(expectedDeck, deck.getCards());
		EasyMock.verify(rand, existingCard, newCard);
	}

	@Test
	public void insertAtMiddle(){
		Random rand = EasyMock.createMock(Random.class);
		Card c1 = EasyMock.createMock(Card.class);
		Card c2 = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(c1, c2));
		EasyMock.replay(rand, c1, c2, newCard);
		Deck deck = new Deck(rand, deckList);

		deck.insertAt(1, newCard);

		ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(c1, newCard, c2));
		assertEquals(expectedDeck, deck.getCards());
		EasyMock.verify(rand, c1, c2, newCard);
	}

	@Test
	public void insertAtEnd(){
		Random rand = EasyMock.createMock(Random.class);
		Card c1 = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(c1));
		EasyMock.replay(rand, c1, newCard);
		Deck deck = new Deck(rand, deckList);

		deck.insertAt(1, newCard);

		ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(c1, newCard));
		assertEquals(expectedDeck, deck.getCards());
		EasyMock.verify(rand, c1, newCard);
	}

	@Test
	public void insertAtInvalidNegativeIndex() {
		Random rand = EasyMock.createMock(Random.class);
		Card c1 = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(c1));
		EasyMock.replay(rand, c1, newCard);
		Deck deck = new Deck(rand, deckList);

		String expectedMessage  = "Invalid move detected: Cannot insert card: Index -1 is out of bounds.";
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			deck.insertAt(-1, newCard);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);

		EasyMock.verify(rand, c1, newCard);
	}

	@Test
	public void insertAtInvalidIndexOutOfBounds() {
		Random rand = EasyMock.createMock(Random.class);
		Card c1 = EasyMock.createMock(Card.class);
		Card newCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(c1));
		EasyMock.replay(rand, c1, newCard);
		Deck deck = new Deck(rand, deckList);

		String expectedMessage  = "Invalid move detected: Cannot insert card: Index 2 is out of bounds.";
		Exception exception = assertThrows(InvalidMoveException.class, () -> {
			deck.insertAt(2, newCard);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
		EasyMock.verify(rand, c1, newCard);
	}

	@Test
	public void swapTopAndBottomTwoCards() {
		Random rand = EasyMock.createMock(Random.class);
		Card cardAtBottom = EasyMock.createMock(Card.class);
		Card cardAtTop = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(cardAtBottom, cardAtTop));
		EasyMock.replay(rand, cardAtBottom, cardAtTop);
		Deck deck = new Deck(rand, deckList);

		deck.swapTopAndBottom();

		ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(cardAtTop, cardAtBottom));
		assertEquals(expectedDeck, deck.getCards());

		EasyMock.verify(rand, cardAtBottom, cardAtTop);
	}

	@Test
	public void swapTopAndBottomThreeCards() {
		Random rand = EasyMock.createMock(Random.class);
		Card cardAtBottom = EasyMock.createMock(Card.class);
		Card middleCard = EasyMock.createMock(Card.class);
		Card cardAtTop = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(cardAtBottom, middleCard, cardAtTop));
		EasyMock.replay(rand, cardAtBottom, middleCard, cardAtTop);
		Deck deck = new Deck(rand, deckList);

		deck.swapTopAndBottom();

		ArrayList<Card> expectedDeck = new ArrayList<>(Arrays.asList(cardAtTop, middleCard, cardAtBottom));
		assertEquals(expectedDeck, deck.getCards());

		EasyMock.verify(rand, cardAtBottom, middleCard, cardAtTop);
	}

	@Test
	public void swapTopAndBottomNotEnoughCards() {
		Random rand = EasyMock.createMock(Random.class);
		Card oneCard = EasyMock.createMock(Card.class);
		ArrayList<Card> deckList = new ArrayList<>(Arrays.asList(oneCard));
		EasyMock.replay(rand, oneCard);
		Deck deck = new Deck(rand, deckList);

		Exception exception = assertThrows(NotEnoughCardsException.class, () -> {
			deck.swapTopAndBottom();
		});

		 String expectedMessage = "Not enough cards to perform action. Needed at least 2, but deck has 1.";
		 assertEquals(expectedMessage, exception.getMessage());

		EasyMock.verify(rand, oneCard);
	}

	@Test
	public void initializeDeckSizeInitializeSizeof2() {
		Random rand = EasyMock.createMock(Random.class);
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);

		ArrayList<Card> deckOfSize2 =
				new ArrayList<>(Arrays.asList(firstCard, secondCard));
		EasyMock.replay(rand, firstCard, secondCard);
		Deck deck = new Deck(rand, deckOfSize2);

		assertEquals(2, deck.getDeckSize());
		EasyMock.verify(rand, firstCard, secondCard);
	}

	@Test
	public void initializeDeckSizeInitializeSizeof3() {
		final int maxDeckSize = 3;
		Random rand = EasyMock.createMock(Random.class);
		Card firstCard = EasyMock.createMock(Card.class);
		Card secondCard = EasyMock.createMock(Card.class);
		Card thirdCard = EasyMock.createMock(Card.class);

		ArrayList<Card> deckOfSize3 =
				new ArrayList<>(Arrays.asList(firstCard, secondCard, thirdCard));
		EasyMock.replay(rand, firstCard, secondCard, thirdCard);
		Deck deck = new Deck(rand, deckOfSize3);

		assertEquals(maxDeckSize, deck.getDeckSize());
		EasyMock.verify(rand, firstCard, secondCard, thirdCard);
	}

	@Test
	public void initializeDeckSizeInitializeSizeof0() {
		ArrayList<Card> deckOfSize0 = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);

		EasyMock.replay(rand);

		Deck deck = new Deck(rand, deckOfSize0);
		assertEquals(0, deck.getDeckSize());
		EasyMock.verify(rand);
	}

	@Test
	public void initializeDeckSizeInitializeSizeof2Dupes() {
		Card firstCard = EasyMock.createMock(Card.class);
		Random rand = EasyMock.createMock(Random.class);
		ArrayList<Card> deckOfSize2 =
				new ArrayList<>(Arrays.asList(firstCard, firstCard));
		EasyMock.replay(rand, firstCard);
		Deck deck = new Deck(rand, deckOfSize2);

		assertEquals(2, deck.getDeckSize());
		EasyMock.verify(rand, firstCard);
	}
}

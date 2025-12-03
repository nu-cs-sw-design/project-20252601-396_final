package model;

import domain.game.Card;
import domain.game.CardType;
import domain.game.Instantiator;
import domain.game.Player;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

	private final static int PLAYER_ID_ONE = 1;
	private final static int PLAYER_ID_TWO = 2;
	private final static int PLAYER_ID_THREE = 3;
	private final static int PLAYER_ID_FOUR = 4;

	@Test
	public void addDefuseToPlayer() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		domain.game.Card defuse = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(defuse.getCardType()).andReturn(domain.game.CardType.DEFUSE);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);

		EasyMock.replay(defuse, instantiator);
		domain.game.Player newPlayer = new domain.game.Player(1, instantiator);
		newPlayer.addDefuse(defuse);

		assertEquals(newPlayer.getHandSize(), 1);
		EasyMock.verify(defuse, instantiator);
	}

	@Test
	public void addNonDefuseToPlayerWhenShouldBeDefuse() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		domain.game.Card defuse = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(defuse.getCardType()).andReturn(domain.game.CardType.CATOMIC_BOMB);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);

		EasyMock.replay(defuse, instantiator);
		domain.game.Player newPlayer = new domain.game.Player(1, instantiator);
		String expectedMessage  = "Must Add Defuse Card";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			newPlayer.addDefuse(defuse);
		});

		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
		EasyMock.verify(defuse, instantiator);
	}

	@Test
	public void addToEmptyHand() {
		domain.game.Card attack = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(attack.getCardType()).andStubReturn(domain.game.CardType.ATTACK);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();

		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(attack, instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(attack);

		assertEquals(1, player.getHandSize());
		assertEquals(attack, player.getCardAt(0));

		try {
			player.getCardAt(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}

		try {
			player.getCardAt(1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}

		EasyMock.verify(attack, instantiator);
	}

	@Test
	public void addToNonEmptyHand() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		domain.game.Card mark = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(mark.getCardType()).andStubReturn(domain.game.CardType.MARK);
		EasyMock.replay(mark);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();

		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		player.addCardToHand(mark);

		try {
			player.getCardAt(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}

		try {
			player.getCardAt(2);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}

		assertEquals(player.getHandSize(), 2);
		assertEquals(player.getCardAt(1), mark);
		EasyMock.verify(nope, mark, instantiator);
	}

	@ParameterizedTest
	@EnumSource(names = {"ATTACK", "DEFUSE", "NOPE", "SHUFFLE", "SKIP",
			"SEE_THE_FUTURE", "CAT_ONE", "CAT_TWO",
			"CAT_THREE", "CAT_FOUR", "STREAKING_KITTEN",
			"ALTER_THE_FUTURE", "CATOMIC_BOMB", "SUPER_SKIP",
			"CURSE_OF_THE_CAT_BUTT", "GARBAGE_COLLECTION",
			"MARK", "SWAP_TOP_AND_BOTTOM"})
	public void getIndexOfCard(domain.game.CardType cardType) {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);


		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		EasyMock.verify(nope, instantiator);

		assertEquals(player.getIndexOfCard(nope.getCardType()), 0);
	}

	@Test
	public void getIndexOfCardDupe() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);


		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		player.addCardToHand(nope);
		EasyMock.verify(nope, instantiator);

		assertEquals(player.getIndexOfCard(nope.getCardType()), 0);
	}

	@Test
	public void getIndexOfCardSecond() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		domain.game.Card mark = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(mark.getCardType()).andStubReturn(domain.game.CardType.MARK);
		EasyMock.replay(mark);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(mark);
		player.addCardToHand(nope);
		EasyMock.verify(nope, instantiator, mark);

		assertEquals(player.getIndexOfCard(nope.getCardType()), 1);
	}

	@Test
	public void getIndexOfCardInvalid() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		domain.game.Card mark = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(mark.getCardType()).andStubReturn(domain.game.CardType.MARK);
		EasyMock.replay(mark);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(mark);
		player.addCardToHand(nope);

		try {
			player.getIndexOfCard(domain.game.CardType.DEFUSE);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No Card Found");
		}
		EasyMock.verify(nope, instantiator, mark);
	}

	@Test
	public void removeOneCard() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		domain.game.CardType cardType = player.removeCardFromHand(0);
		assertEquals(player.getHandSize(), 0);
		assertEquals(cardType, domain.game.CardType.NOPE);
		EasyMock.verify(nope, instantiator);

	}

	@Test
	public void removeTwoCards() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		domain.game.Card mark = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(mark.getCardType()).andStubReturn(domain.game.CardType.MARK);
		EasyMock.replay(mark);

		domain.game.Card skip = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(skip.getCardType()).andStubReturn(domain.game.CardType.SKIP);
		EasyMock.replay(skip);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(mark);
		player.addCardToHand(nope);
		player.addCardToHand(skip);
		domain.game.CardType cardType = player.removeCardFromHand(2);
		assertEquals(player.getHandSize(), 2);
		assertEquals(cardType, domain.game.CardType.SKIP);
		EasyMock.verify(nope, instantiator, mark, skip);

	}

	@Test
	public void removeThreeCards() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		player.addCardToHand(nope);
		domain.game.CardType cardType = player.removeCardFromHand(1);
		assertEquals(player.getHandSize(), 1);
		assertEquals(cardType, domain.game.CardType.NOPE);
		EasyMock.verify(nope, instantiator);

	}

	@Test
	public void removeOneCardInvalid() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		try {
			player.removeCardFromHand(1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No Card Found");
		}
		EasyMock.verify(nope, instantiator);

	}

	@Test
	public void removeOneCardNegativeOne() {
		domain.game.Card nope = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(nope.getCardType()).andStubReturn(domain.game.CardType.NOPE);
		EasyMock.replay(nope);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(nope);
		try {
			player.removeCardFromHand(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No Card Found");
		}
		EasyMock.verify(nope, instantiator);

	}

	@Test
	public void removeFromEmptyHand() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		try {
			player.removeCardFromHand(0);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No Card Found");
		}
		EasyMock.verify(instantiator);

	}

	@Test
	public void hasAtLeastTwoCatsTrue() {
		domain.game.Card catOne = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card catTwo = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(catOne.getCardType()).andStubReturn(domain.game.CardType.CAT_ONE);
		EasyMock.expect(catTwo.getCardType()).andStubReturn(domain.game.CardType.CAT_ONE);
		EasyMock.replay(catOne, catTwo);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(catOne);
		player.addCardToHand(catTwo);
		assertEquals(2,
				player.checkNumberOfCardsInHand(catOne.getCardType()));
		EasyMock.verify(catOne, catTwo, instantiator);
	}

	@Test
	public void hasAtLeastTwoCatsFalse() {
		domain.game.Card catOne = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card catTwo = EasyMock.createMock(domain.game.Card.class);
		EasyMock.expect(catOne.getCardType()).andStubReturn(domain.game.CardType.CAT_ONE);
		EasyMock.expect(catTwo.getCardType()).andStubReturn(domain.game.CardType.CAT_TWO);
		EasyMock.replay(catOne, catTwo);

		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		Random rand = EasyMock.createMock(Random.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(catOne);
		player.addCardToHand(catTwo);
		assertEquals(1, player.
				checkNumberOfCardsInHand(catOne.getCardType()));
		EasyMock.verify(catOne, instantiator);
	}

	@Test
	public void shuffleHandTwoCards() {
		Random rand = EasyMock.createMock(Random.class);
		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.expect(rand.nextInt(2)).andReturn(0);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(rand, instantiator);


		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(secondCard);
		player.addCardToHand(firstCard);
		player.shuffleHand();

		assertEquals(player.getCardAt(0), firstCard);
		EasyMock.verify(rand, instantiator);
	}

	@Test
	public void shuffleHandZeroCards() {
		Random rand = EasyMock.createMock(Random.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(rand, instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.shuffleHand();
		assertEquals(player.getHandSize(), 0);
		EasyMock.verify(rand, instantiator);
	}

	@Test
	public void shuffleDeckThreeCardsDupe() {
		Random rand = EasyMock.createMock(Random.class);
		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		final int randUpperBoundOne = 3;
		final int randUpperBoundTwo = 2;
		EasyMock.expect(rand.nextInt(randUpperBoundOne)).andReturn(0);
		EasyMock.expect(rand.nextInt(randUpperBoundTwo)).andReturn(0);
		EasyMock.replay(rand, firstCard, secondCard, instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);
		player.addCardToHand(firstCard);
		player.addCardToHand(firstCard);
		player.addCardToHand(secondCard);

		player.shuffleHand();

		assertEquals(player.getCardAt(0), firstCard);
		assertEquals(player.getCardAt(1), secondCard);
		assertEquals(player.getCardAt(2), firstCard);
		EasyMock.verify(rand, firstCard, secondCard, instantiator);
	}

	@Test
	public void setCursedTrue() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		Random rand = EasyMock.createMock(Random.class);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);

		player.setCursed(true);

		assertTrue(player.getIsCursed());

		EasyMock.verify(instantiator);
	}

	@Test
	public void setCursedFalse() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		Random rand = EasyMock.createMock(Random.class);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);

		player.setCursed(false);

		assertFalse(player.getIsCursed());

		EasyMock.verify(instantiator);
	}

	@Test
	public void exploded() {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		Random rand = EasyMock.createMock(Random.class);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(0, instantiator);

		assertFalse(player.getIsDead());

		player.setIsDead();

		assertTrue(player.getIsDead());

		EasyMock.verify(instantiator);
	}

	@ParameterizedTest
	@ValueSource(ints = {PLAYER_ID_ONE, PLAYER_ID_TWO, PLAYER_ID_THREE, PLAYER_ID_FOUR})
	public void getPlayerID(int playerID) {
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		Random rand = EasyMock.createMock(Random.class);

		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		EasyMock.replay(instantiator);

		domain.game.Player player = new domain.game.Player(playerID, instantiator);

		assertEquals(player.getPlayerID(), playerID);

		EasyMock.verify(instantiator);
	}

	@Test
	public void hasCardEmptyHand() {
		Random rand = EasyMock.createMock(Random.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);
		EasyMock.replay(rand, instantiator);


		domain.game.Player player = new domain.game.Player(0, instantiator);

		assertFalse(player.hasCard(domain.game.CardType.NOPE));
		EasyMock.verify(rand, instantiator);
	}

	@Test
	public void hasOneCardNotNope() {
		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
		Random rand = EasyMock.createMock(Random.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);

		EasyMock.expect(firstCard.getCardType()).andReturn(domain.game.CardType.DEFUSE);

		EasyMock.replay(rand, instantiator, firstCard);


		domain.game.Player player = new domain.game.Player(0, instantiator);

		player.addCardToHand(firstCard);

		assertFalse(player.hasCard(domain.game.CardType.NOPE));
		EasyMock.verify(rand, instantiator, firstCard);
	}

	@Test
	public void hasOneCardNope() {
		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
		Random rand = EasyMock.createMock(Random.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		ArrayList<domain.game.Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);

		EasyMock.expect(firstCard.getCardType()).andReturn(domain.game.CardType.NOPE);

		EasyMock.replay(rand, instantiator,  firstCard);


		domain.game.Player player = new domain.game.Player(0, instantiator);

		player.addCardToHand(firstCard);

		assertTrue(player.hasCard(domain.game.CardType.NOPE));
		EasyMock.verify(rand, instantiator, firstCard);
	}

	@Test
	public void hasThreeCardsOneNope() {
		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
		domain.game.Card thirdCard = EasyMock.createMock(domain.game.Card.class);
		Random rand = EasyMock.createMock(Random.class);
		Instantiator instantiator = EasyMock.createMock(Instantiator.class);
		EasyMock.expect(instantiator.createRandom()).andReturn(rand);
		ArrayList<Card> cardList = new ArrayList<>();
		EasyMock.expect(instantiator.createCardList()).andReturn(cardList);

		EasyMock.expect(firstCard.getCardType()).andReturn(domain.game.CardType.DEFUSE);
		EasyMock.expect(secondCard.getCardType()).andReturn(domain.game.CardType.DEFUSE);
		EasyMock.expect(thirdCard.getCardType()).andReturn(domain.game.CardType.NOPE);

		EasyMock.replay(rand, instantiator, firstCard, secondCard, thirdCard);

		domain.game.Player player = new Player(0, instantiator);

		player.addCardToHand(firstCard);
		player.addCardToHand(secondCard);
		player.addCardToHand(thirdCard);

		assertTrue(player.hasCard(CardType.NOPE));
		EasyMock.verify(rand, instantiator, firstCard, secondCard, thirdCard);
	}
}

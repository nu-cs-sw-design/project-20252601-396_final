//package model;
//
//import domain.game.*;
//import domain.game.Card;
//import domain.game.CardType;
//import domain.game.Deck;
//import domain.game.Game;
//import domain.game.Player;
//import org.easymock.EasyMock;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EnumSource;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.Random;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GameTest {
//
//	private final static int SIX_PLAYERS = 6;
//	private final static int FIVE_PLAYERS = 5;
//	private final static int ONE_PLAYER = 1;
//	private final static int NEGATIVE_ONE_PLAYER = -1;
//	private final static int TWO_PLAYERS = 2;
//	private final static int FORTY_TWO_DECK_SIZE = 42;
//	private final static int FIFTY_SIX_DECK_SIZE = 56;
//	private final static int SIXTY_TWO_DECK_SIZE = 62;
//
//	@Test
//	public void selectRandomPlayerNoPlayers() {
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.expect(rand.nextInt(0)).andReturn(0);
//		EasyMock.replay(rand);
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(0, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//
//		try {
//			game.selectRandomPlayer();
//		} catch (UnsupportedOperationException e) {
//			assertEquals(e.getMessage(), "No players to select from.");
//		}
//		EasyMock.verify(rand);
//	}
//
//	@Test
//	public void selectRandomPlayerOnePlayer() {
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.expect(rand.nextInt(1)).andReturn(0);
//		EasyMock.replay(rand);
//		domain.game.Player playerFirst = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(playerFirst.getPlayerID()).andReturn(0);
//		EasyMock.replay(playerFirst);
//		domain.game.Player[] players = {playerFirst};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(1, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		domain.game.Player player = game.selectRandomPlayer();
//
//		assertEquals(player.getPlayerID(), 0);
//		EasyMock.verify(rand);
//		EasyMock.verify(playerFirst);
//	}
//
//	@Test
//	public void selectRandomPlayerTwoPlayers() {
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.expect(rand.nextInt(2)).andReturn(1);
//		EasyMock.replay(rand);
//		domain.game.Player playerFirst = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerSecond = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(playerSecond.getPlayerID()).andReturn(1);
//		EasyMock.replay(playerSecond);
//		domain.game.Player[] players = {playerFirst, playerSecond};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(2, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		domain.game.Player player = game.selectRandomPlayer();
//		assertEquals(player.getPlayerID(), 1);
//		EasyMock.verify(rand);
//		EasyMock.verify(playerSecond);
//	}
//
//	@Test
//	public void selectRandomPlayerThreePlayers() {
//
//		Random rand = EasyMock.createMock(Random.class);
//		final int randNextValue = 2;
//		final int randNextInput = 3;
//		final int numOfPlayers = 3;
//		final int expectedPlayerID = 2;
//		EasyMock.expect(rand.nextInt(randNextInput))
//				.andReturn(randNextValue);
//		EasyMock.replay(rand);
//		domain.game.Player playerFirst = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerSecond = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThird = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(playerThird.getPlayerID()).andReturn(2);
//		EasyMock.replay(playerThird);
//		domain.game.Player[] players = {playerFirst, playerSecond, playerThird};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//		domain.game.Player player = game.selectRandomPlayer();
//		assertEquals(player.getPlayerID(), expectedPlayerID);
//		EasyMock.verify(rand);
//		EasyMock.verify(playerThird);
//	}
//
//	@Test
//	public void selectRandomPlayerMaxPlayers() {
//		final int randNextValue = 3;
//		final int randNextInput = 4;
//		final int numOfPlayers = 4;
//		final int expectedPlayerID = 3;
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.expect(rand.nextInt(randNextInput))
//				.andReturn(randNextValue);
//		EasyMock.replay(rand);
//		domain.game.Player playerFirst = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerSecond = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThird = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFourth = EasyMock.createMock(domain.game.Player.class);
//		final int timesPlayerIDReturned = 3;
//		EasyMock.expect(playerFourth.getPlayerID())
//				.andReturn(timesPlayerIDReturned);
//		EasyMock.replay(playerFourth);
//		domain.game.Player[] players = {playerFirst, playerSecond, playerThird, playerFourth};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		domain.game.Player player = game.selectRandomPlayer();
//		assertEquals(player.getPlayerID(), expectedPlayerID);
//		EasyMock.verify(rand);
//		EasyMock.verify(playerFourth);
//	}
//
//	@Test
//	public void selectRandomPlayerMaxPlayersSelectFirstPlayer() {
//		final int randNextValue = 0;
//		final int randNextInput = 4;
//		final int numOfPlayers = 4;
//		final int expectedPlayerID = 0;
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.expect(rand.nextInt(randNextInput))
//				.andReturn(randNextValue);
//		EasyMock.replay(rand);
//		domain.game.Player playerFirst = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerSecond = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThird = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFourth = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(playerFirst.getPlayerID())
//				.andReturn(expectedPlayerID);
//		EasyMock.replay(playerFirst);
//		domain.game.Player[] players = {playerFirst, playerSecond, playerThird, playerFourth};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		domain.game.Player player = game.selectRandomPlayer();
//		assertEquals(player.getPlayerID(), expectedPlayerID);
//		EasyMock.verify(rand);
//		EasyMock.verify(playerFirst);
//	}
//
//	@ParameterizedTest
//	@ValueSource(ints = { ONE_PLAYER, SIX_PLAYERS })
//	public void retrieveNumberOfPlayersException(int numPlayers) {
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player[] players = {};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck
//				, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numPlayers, gameType, deck, players, new SecureRandom(),
//				attackQueue, turnTracker);
//
//		IllegalArgumentException exception =
//				assertThrows(IllegalArgumentException.class, () -> {
//			game.setNumberOfPlayers(numPlayers);
//		});
//		assertEquals("Number of players must be " +
//				"between 2 and 5 inclusive", exception.getMessage());
//		EasyMock.verify(deck
//				, attackQueue);
//	}
//
//	@ParameterizedTest
//	@ValueSource(ints = { TWO_PLAYERS, FIVE_PLAYERS })
//	public void retrieveNumberOfPlayersValid(int numPlayers) {
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player[] players = {};
//		GameType gameType = GameType.EXPLODING_KITTENS;
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.setNumberOfPlayers(numPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck
//				, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numPlayers, gameType,
//				deck, players, new SecureRandom(),
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numPlayers);
//		int numPlayersRetrieved = game.getNumberOfPlayers();
//		assertEquals(numPlayersRetrieved, numPlayers);
//		EasyMock.verify(deck
//				, attackQueue);
//	}
//
//	@ParameterizedTest
//	@EnumSource(names = {"NONE"})
//	public void retrieveGameTypeInvalid(GameType gameType) {
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, rand
//				, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(2, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		IllegalArgumentException exception =
//				assertThrows(IllegalArgumentException.class, () -> {
//			game.retrieveGameMode(gameType);
//		});
//		assertEquals("Must Provide a Valid Game Type", exception.getMessage());
//		EasyMock.verify(deck, rand
//				, attackQueue);
//	}
//
//	@ParameterizedTest
//	@EnumSource(names = {"EXPLODING_KITTENS", "STREAKING_KITTENS", "IMPLODING_KITTENS"})
//	public void retrieveGameTypeValid(GameType gameType) {
//		final int numOfPlayers = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.chooseGameType(gameType);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, rand
//				, attackQueue);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers, gameType, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.retrieveGameMode(gameType);
//		assertEquals(game.getGameTypeForTesting(), gameType);
//		EasyMock.verify(deck, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void checkNumberOfPlayersAliveIsOne() {
//		final int numOfPlayers = 5;
//		final int numOfAlivePlayers = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(player.getIsDead()).andReturn(false);
//		final int timesPlayer2DeadReturned = 4;
//		EasyMock.expect(player2.getIsDead()).andReturn(true)
//				.times(timesPlayer2DeadReturned);
//		domain.game.Player[] players = {player, player2, player2, player2, player2};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, rand, player, player2);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		assertEquals(game.checkNumberOfAlivePlayers(), numOfAlivePlayers);
//
//		EasyMock.verify(deck, rand, player, player2);
//	}
//
//	@Test
//	public void checkNumberOfPlayersAliveIsFive() {
//		final int numOfPlayers = 5;
//		final int numOfAlivePlayers = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		EasyMock.expect(player.getIsDead()).andReturn(false);
//		final int timesPlayer2DeadReturned = 4;
//		EasyMock.expect(player2.getIsDead()).andReturn(false)
//				.times(timesPlayer2DeadReturned);
//		domain.game.Player[] players = {player, player2, player2, player2, player2};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, rand, player, player2);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		assertEquals(game.checkNumberOfAlivePlayers(), numOfAlivePlayers);
//
//		EasyMock.verify(deck, rand, player, player2);
//	}
//
//	@Test
//	public void swapTopAndBottomEmptyDeck() {
//		final int numOfPlayers = 5;
//		final int expectedDeckSize = 0;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(0).times(2);
//		EasyMock.replay(deck, rand
//				, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.swapTopAndBottom();
//		assertEquals(expectedDeckSize, game.getDeckSize());
//		EasyMock.verify(deck, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void swapTopAndBottomDeckSizeOne() {
//		final int numOfPlayers = 5;
//		final int expectedDeckSize = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(card.getCardType()).andReturn(domain.game.CardType.SKIP);
//		EasyMock.expect(deck.getDeckSize()).andReturn(1).times(2);
//		EasyMock.replay(deck, rand, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.swapTopAndBottom();
//		assertEquals(expectedDeckSize, game.getDeckSize());
//		assertEquals(domain.game.CardType.SKIP, card.getCardType());
//		EasyMock.verify(deck, rand, card);
//	}
//
//	@Test
//	public void swapTopAndBottomDeckSizeTwo() {
//		final int numOfPlayers = 5;
//		final int expectedDeckSize = 2;
//		final int numOfCardsToAdd = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(2).anyTimes();
//		EasyMock.expect(deck.drawCardFromBottom()).andReturn(cardOne);
//		EasyMock.expect(deck.drawCard()).andReturn(cardTwo);
//		EasyMock.expect(cardOne.getCardType()).andReturn(domain.game.CardType.SKIP).times(2);
//		deck.insertCard(domain.game.CardType.SKIP, numOfCardsToAdd, false);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(cardTwo.getCardType()).andReturn(domain.game.CardType.NOPE);
//		deck.insertCard(domain.game.CardType.NOPE, numOfCardsToAdd, true);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardOne);
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.swapTopAndBottom();
//
//		assertEquals(expectedDeckSize, game.getDeckSize());
//
//		assertEquals(domain.game.CardType.SKIP, game.getDeckCardType(0));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo);
//	}
//
//	@Test
//	public void swapTopAndBottomDeckSizeThree() {
//		final int numOfPlayers = 5;
//		final int expectedDeckSize = 3;
//		final int numOfCardsToAdd = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(expectedDeckSize).anyTimes();
//		EasyMock.expect(deck.drawCardFromBottom()).andReturn(cardOne);
//		EasyMock.expect(deck.drawCard()).andReturn(cardTwo);
//		EasyMock.expect(cardOne.getCardType()).andReturn(domain.game.CardType.SKIP).times(2);
//		deck.insertCard(domain.game.CardType.SKIP, numOfCardsToAdd, false);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(cardTwo.getCardType()).andReturn(domain.game.CardType.NOPE);
//		deck.insertCard(domain.game.CardType.NOPE, numOfCardsToAdd, true);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardOne);
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.swapTopAndBottom();
//
//		assertEquals(expectedDeckSize, game.getDeckSize());
//
//		assertEquals(domain.game.CardType.SKIP, game.getDeckCardType(0));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo);
//	}
//
//	@Test
//	public void playCatComboPlayerToStealHaveNoCard() {
//		final int indexOfStolenPlayer = 0;
//		final int handSize = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player.getHandSize()).andReturn(handSize);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//		EasyMock.replay(deck, player, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(2,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.incrementPlayerTurn();
//		try {
//			game.stealRandomCard(indexOfStolenPlayer);
//		} catch (IllegalArgumentException e) {
//			assertEquals("Player has no cards to steal", e.getMessage());
//		}
//		EasyMock.verify(deck, player, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void stealTypeCardPlayerToStealHaveNoCard() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player.getIndexOfCard(domain.game.CardType.NOPE)).
//				andThrow(new IllegalArgumentException("No Card Found"));
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//		EasyMock.replay(deck, player, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.incrementPlayerTurn();
//		try {
//			game.stealTypeCard(domain.game.CardType.NOPE, indexOfStolenPlayer);
//		} catch (IllegalArgumentException e) {
//			assertEquals("Player does not have the card type to steal", e.getMessage());
//		}
//		EasyMock.verify(deck, player, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playCatComboPlayerToStealHaveOneCard() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 1;
//		final int indexOfStealingPlayer = 0;
//		final int handSizeStealingPlayer = 1;
//		final int handSizeStolenPlayer = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getHandSize()).andReturn(1);
//		EasyMock.expect(player2.getHandSize()).andReturn(1);
//		EasyMock.expect(rand.nextInt(1)).andReturn(0);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		EasyMock.expect(player2.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.removeCardFromHand(0)).andReturn(domain.game.CardType.DEFUSE);
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(true).once();
//		EasyMock.expect(player2.hasCard(domain.game.CardType.DEFUSE)).andReturn(false).once();
//		player.addCardToHand(card);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(player.getHandSize()).andReturn(1);
//		EasyMock.expect(player.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.getHandSize()).andReturn(0);
//		EasyMock.replay(deck, player, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		domain.game.Card stealedCard = game.stealRandomCard(indexOfStolenPlayer);
//		assertEquals(stealedCard, card);
//		assertEquals(handSizeStealingPlayer, player.getHandSize());
//		assertEquals(player.getCardAt(0), card);
//		assertEquals(handSizeStolenPlayer, player2.getHandSize());
//		assertTrue(game.checkIfPlayerHasCard(indexOfStealingPlayer, domain.game.CardType.DEFUSE));
//		assertFalse(game.checkIfPlayerHasCard(indexOfStolenPlayer, domain.game.CardType.DEFUSE));
//
//		EasyMock.verify(deck, player, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void stealTypeCardPlayerToStealHaveOneCard() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 1;
//		final int indexOfStealingPlayer = 0;
//		final int handSizeStealingPlayer = 1;
//		final int handSizeStolenPlayer = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIndexOfCard(domain.game.CardType.DEFUSE)).andReturn(0);
//		EasyMock.expect(player2.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.removeCardFromHand(0)).andReturn(domain.game.CardType.DEFUSE);
//
//		player.addCardToHand(card);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(player.getHandSize()).andReturn(1);
//		EasyMock.expect(player.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.getHandSize()).andReturn(0);
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(true).once();
//		EasyMock.expect(player2.hasCard(domain.game.CardType.DEFUSE)).andReturn(false).once();
//		EasyMock.replay(deck, player, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.stealTypeCard(domain.game.CardType.DEFUSE, indexOfStolenPlayer);
//
//		assertEquals(handSizeStealingPlayer, player.getHandSize());
//		assertEquals(player.getCardAt(0), card);
//		assertEquals(handSizeStolenPlayer, player2.getHandSize());
//		assertTrue(game.checkIfPlayerHasCard(indexOfStealingPlayer, domain.game.CardType.DEFUSE));
//		assertFalse(game.checkIfPlayerHasCard(indexOfStolenPlayer, domain.game.CardType.DEFUSE));
//
//		EasyMock.verify(deck, player, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void stealTypeCardPlayerToStealHaveTwoCards() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 1;
//		final int indexOfStealingPlayer = 0;
//		final int handSizeStealingPlayer = 1;
//		final int handSizeStolenPlayer = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIndexOfCard(domain.game.CardType.DEFUSE)).andReturn(1);
//		EasyMock.expect(player2.getCardAt(1)).andReturn(card);
//		EasyMock.expect(player2.removeCardFromHand(1)).andReturn(domain.game.CardType.DEFUSE);
//
//		player.addCardToHand(card);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(true).once();
//		EasyMock.expect(player2.hasCard(domain.game.CardType.DEFUSE)).andReturn(true).once();
//
//		EasyMock.expect(player.getHandSize()).andReturn(1);
//		EasyMock.expect(player.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.getHandSize()).andReturn(1);
//		EasyMock.replay(deck, player, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.stealTypeCard(domain.game.CardType.DEFUSE, indexOfStolenPlayer);
//		assertEquals(handSizeStealingPlayer, player.getHandSize());
//		assertEquals(player.getCardAt(0), card);
//		assertEquals(handSizeStolenPlayer, player2.getHandSize());
//		assertTrue(game.checkIfPlayerHasCard(indexOfStealingPlayer, domain.game.CardType.DEFUSE));
//		assertTrue(game.checkIfPlayerHasCard(indexOfStolenPlayer, domain.game.CardType.DEFUSE));
//		EasyMock.verify(deck, player, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playCatCombBothPlayerHaveOneCard() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 2;
//		final int indexOfStealingPlayer = 0;
//		final int handSizeStealingPlayer = 2;
//		final int handSizeStolenPlayer = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.ATTACK)).andReturn(true).once();
//		EasyMock.expect(player2.hasCard(domain.game.CardType.ATTACK)).andReturn(false).once();
//
//		EasyMock.expect(player2.getHandSize()).andReturn(1);
//		EasyMock.expect(player2.getHandSize()).andReturn(1);
//		EasyMock.expect(rand.nextInt(1)).andReturn(0);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		EasyMock.expect(player2.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.removeCardFromHand(0))
//				.andReturn(domain.game.CardType.ATTACK);
//
//		player.addCardToHand(card);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(player.getHandSize()).andReturn(2);
//		EasyMock.expect(player.getCardAt(1)).andReturn(card);
//		EasyMock.expect(player2.getHandSize()).andReturn(0);
//		EasyMock.replay(deck, player, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player1, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.stealRandomCard(indexOfStolenPlayer);
//		assertEquals(handSizeStealingPlayer, player.getHandSize());
//		assertEquals(player.getCardAt(1), card);
//		assertEquals(handSizeStolenPlayer, player2.getHandSize());
//		assertTrue(game.checkIfPlayerHasCard(indexOfStealingPlayer, domain.game.CardType.ATTACK));
//		assertFalse(game.checkIfPlayerHasCard(indexOfStolenPlayer, domain.game.CardType.ATTACK));
//
//		EasyMock.verify(deck, player, player1, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void stealTypeCardBothPlayerHaveOneCard() {
//		final int numOfPlayers = 2;
//		final int indexOfStolenPlayer = 2;
//		final int indexOfStealingPlayer = 0;
//		final int handSizeStealingPlayer = 2;
//		final int handSizeStolenPlayer = 0;
//		final int indexOfCard = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIndexOfCard(domain.game.CardType.ATTACK)).andReturn(0);
//		EasyMock.expect(player2.getCardAt(0)).andReturn(card);
//		EasyMock.expect(player2.removeCardFromHand(0))
//				.andReturn(domain.game.CardType.ATTACK);
//
//		player.addCardToHand(card);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.ATTACK)).andReturn(true).once();
//		EasyMock.expect(player2.hasCard(domain.game.CardType.ATTACK)).andReturn(false).once();
//
//		EasyMock.expect(player.getHandSize()).andReturn(2);
//		EasyMock.expect(player.getCardAt(indexOfCard)).andReturn(card);
//		EasyMock.expect(player2.getHandSize()).andReturn(0);
//		EasyMock.replay(deck, player, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player1, player2};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.stealTypeCard(domain.game.CardType.ATTACK, indexOfStolenPlayer);
//		assertEquals(handSizeStealingPlayer, player.getHandSize());
//		assertEquals(player.getCardAt(indexOfCard), card);
//		assertEquals(handSizeStolenPlayer, player2.getHandSize());
//		assertTrue(game.checkIfPlayerHasCard(indexOfStealingPlayer, domain.game.CardType.ATTACK));
//		assertFalse(game.checkIfPlayerHasCard(indexOfStolenPlayer, domain.game.CardType.ATTACK));
//
//		EasyMock.verify(deck, player, player1, player2, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playCatomicBombOneBombTwoCards() {
//		final int numOfPlayers = 5;
//		final int numOfBombs = 1;
//		final int bombOneIndex = 1;
//		final int deckSize = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo};
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(deckSize).anyTimes();
//
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false);
//		EasyMock.expect(cardOne.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN).times(1);
//
//		EasyMock.expect(cardTwo.getCardType())
//				.andReturn(domain.game.CardType.DEFUSE).anyTimes();
//
//		EasyMock.expect(deck.removeBombs()).andReturn(1);
//
//		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardOne).times(1);
//
//		deck.insertCard(domain.game.CardType.EXPLODING_KITTEN,
//				numOfBombs, false);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(deck.drawCardFromBottom()).andReturn(cardTwo).times(1);
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.setCurrentPlayerTurn(0);
//		game.playCatomicBomb();
//
//		assertEquals(game.getNumberOfTurns(), 0);
//		assertEquals(game.getPlayerTurn(), 1);
//		assertEquals(deckSize, game.getDeckSize());
//		assertEquals(domain.game.CardType.DEFUSE, game.drawFromBottom().getCardType());
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombOneIndex));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//	}
//
//	@Test
//	public void playCatomicBombTwoBombsThreeCards() {
//		final int numOfPlayers = 5;
//		final int numOfBombs = 2;
//		final int bombOneIndex = 1;
//		final int bombTwoIndex = 2;
//		final int deckSize = 3;
//		final int numOfTurnIndexStart = 4;
//		final int numOfTurnIndexEnd = 4;
//		final int numOfTurnsStart = 12;
//		final int numOfTurnsEnd = 11;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//		Random rand = EasyMock.createMock(Random.class);
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(deckSize).anyTimes();
//
//		EasyMock.expect(cardOne.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN).times(numOfBombs);
//
//		EasyMock.expect(cardTwo.getCardType())
//				.andReturn(domain.game.CardType.DEFUSE).anyTimes();
//
//		EasyMock.expect(deck.removeBombs()).andReturn(1);
//		EasyMock.expect(deck.drawCardFromBottom()).andReturn(cardTwo).times(1);
//		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardOne).times(1);
//		EasyMock.expect(deck.getCardAtIndex(2)).andReturn(cardOne).times(1);
//
//		deck.insertCard(domain.game.CardType.EXPLODING_KITTEN, 1, false);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo
//				, playerOne, playerTwo, playerThree, playerFour, playerFive);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numOfTurnsStart);
//		game.setCurrentPlayerTurn(numOfTurnIndexStart);
//		game.playCatomicBomb();
//
//		assertEquals(game.getNumberOfTurns(), numOfTurnsEnd);
//		assertEquals(game.getPlayerTurn(), numOfTurnIndexEnd);
//		assertEquals(deckSize, game.getDeckSize());
//		assertEquals(domain.game.CardType.DEFUSE, game.drawFromBottom().getCardType());
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombOneIndex));
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombTwoIndex));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo
//				, playerOne, playerTwo, playerThree, playerFour, playerFive);
//	}
//
//	@Test
//	public void playCatomicBombOneBomb() {
//		final int numOfPlayers = 5;
//		final int numOfBombs = 1;
//		final int deckSize = 1;
//		final int numOfTurnIndexStart = 3;
//		final int numOfTurnIndexEnd = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo,
//				playerThree, playerFour, playerFive};
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(1).anyTimes();
//
//		EasyMock.expect(cardOne.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN).times(1);
//
//		EasyMock.expect(deck.removeBombs()).andReturn(1);
//
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardOne).times(1);
//
//		deck.insertCard(domain.game.CardType.EXPLODING_KITTEN, numOfBombs, false);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.setCurrentPlayerTurn(numOfTurnIndexStart);
//		game.playCatomicBomb();
//
//		assertEquals(deckSize, game.getDeckSize());
//		assertEquals(game.getPlayerTurn(), numOfTurnIndexEnd);
//		assertEquals(game.getNumberOfTurns(), 0);
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(0));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//	}
//
//	@Test
//	public void playCatomicBombThreeBombsFourCards() {
//		final int numOfPlayers = 5;
//		final int numOfBombs = 3;
//		final int bombOneIndex = 1;
//		final int bombTwoIndex = 2;
//		final int bombThreeIndex = 3;
//		final int deckSize = 4;
//		final int indexOne = 1;
//		final int indexTwo = 2;
//		final int indexThree = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour};
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(deckSize).anyTimes();
//
//		EasyMock.expect(cardOne.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN).times(numOfBombs);
//
//		EasyMock.expect(cardTwo.getCardType())
//				.andReturn(domain.game.CardType.DEFUSE).anyTimes();
//
//		EasyMock.expect(deck.removeBombs()).andReturn(1);
//
//		EasyMock.expect(deck.getCardAtIndex(indexOne)).andReturn(cardOne).times(1);
//		EasyMock.expect(deck.getCardAtIndex(indexTwo)).andReturn(cardOne).times(1);
//		EasyMock.expect(deck.getCardAtIndex(indexThree)).andReturn(cardOne).times(1);
//
//
//		deck.insertCard(domain.game.CardType.EXPLODING_KITTEN, 1, false);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(deck.drawCardFromBottom()).andReturn(cardTwo).times(1);
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo, playerOne,
//				playerTwo, playerThree, playerFour);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(2);
//		game.setCurrentPlayerTurn(0);
//		game.playCatomicBomb();
//
//		assertEquals(game.getNumberOfTurns(), 1);
//		assertEquals(game.getPlayerTurn(), 0);
//		assertEquals(deckSize, game.getDeckSize());
//		assertEquals(domain.game.CardType.DEFUSE, game.drawFromBottom().getCardType());
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombOneIndex));
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombTwoIndex));
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN, game.getDeckCardType(bombThreeIndex));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo, playerOne
//				, playerTwo, playerThree, playerFour);
//	}
//
//	@ParameterizedTest
//	@ValueSource(ints = {FORTY_TWO_DECK_SIZE,
//			FIFTY_SIX_DECK_SIZE, SIXTY_TWO_DECK_SIZE})
//	public void playCatomicBombOneBombExpansionPacks(int deckSize) {
//		final int numOfPlayers = 5;
//		final int numOfBombs = 1;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Card cardOne = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo};
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(deck.getDeckSize()).andReturn(deckSize).anyTimes();
//
//		EasyMock.expect(cardOne.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN).times(1);
//
//		EasyMock.expect(deck.removeBombs()).andReturn(numOfBombs);
//
//		EasyMock.expect(deck.getCardAtIndex(deckSize - 1)).andReturn(cardOne).times(1);
//
//		deck.insertCard(domain.game.CardType.EXPLODING_KITTEN, numOfBombs, false);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(2);
//		game.setCurrentPlayerTurn(0);
//		game.playCatomicBomb();
//
//		assertEquals(deckSize, game.getDeckSize());
//		assertEquals(game.getNumberOfTurns(), 1);
//		assertEquals(game.getPlayerTurn(), 0);
//		assertEquals(domain.game.CardType.EXPLODING_KITTEN,
//				game.getDeckCardType(deckSize - 1));
//
//		EasyMock.verify(deck, rand, cardOne, cardTwo, playerOne, playerTwo);
//	}
//
//	@Test
//	public void playReverseTwoPlayers() {
//		final int numOfPlayers = 2;
//		final int zerothPlayerIndex = 0;
//		final int firstPlayerIndex = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setCurrentPlayerTurn(0);
//
//
//		game.playReverse();
//
//		assertEquals(game.getPlayerTurn(), 0);
//		assertEquals(game.getNumberOfTurns(), 0);
//		assertEquals(game.getPlayerAtIndex(zerothPlayerIndex), playerTwo);
//		assertEquals(game.getPlayerAtIndex(firstPlayerIndex), playerOne);
//		assertTrue(game.getIsReversed());
//
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo);
//	}
//
//	@Test
//	public void playReverseFivePlayers() {
//		final int numOfPlayers = 5;
//		final int initialNumOfPlayers = 2;
//		final int zerothPlayerIndex = 0;
//		final int firstPlayerIndex = 1;
//		final int secondPlayerIndex = 2;
//		final int thirdPlayerIndex = 3;
//		final int fourthPlayerIndex = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, rand, playerOne,
//				playerTwo, playerThree, playerFour, playerFive);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(initialNumOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setCurrentPlayerTurn(firstPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.playReverse();
//
//		assertEquals(game.getPlayerTurn(), fourthPlayerIndex);
//		assertEquals(game.getPlayerAtIndex(zerothPlayerIndex), playerFive);
//		assertEquals(game.getPlayerAtIndex(firstPlayerIndex), playerFour);
//		assertEquals(game.getPlayerAtIndex(secondPlayerIndex), playerThree);
//		assertEquals(game.getPlayerAtIndex(thirdPlayerIndex), playerTwo);
//		assertEquals(game.getPlayerAtIndex(fourthPlayerIndex), playerOne);
//
//		assertTrue(game.getIsReversed());
//
//		EasyMock.verify(deck, rand, playerOne,
//				playerTwo, playerThree, playerFour, playerFive);
//	}
//
//	@Test
//	public void playReverseTwoPlayersTwice() {
//		final int numOfPlayers = 2;
//		final int initialNumOfPlayers = 5;
//		final int zerothPlayerIndex = 0;
//		final int firstPlayerIndex = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false);
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.replay(deck, rand, playerOne, playerTwo);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(initialNumOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setCurrentPlayerTurn(0);
//		game.setNumberOfPlayers(2);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.playReverse();
//
//		assertEquals(game.getPlayerTurn(), 0);
//		assertEquals(game.getPlayerAtIndex(zerothPlayerIndex), playerTwo);
//		assertEquals(game.getPlayerAtIndex(firstPlayerIndex), playerOne);
//		assertTrue(game.getIsReversed());
//
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.playReverse();
//
//		assertEquals(game.getPlayerTurn(), 0);
//		assertEquals(game.getPlayerAtIndex(zerothPlayerIndex), playerOne);
//		assertEquals(game.getPlayerAtIndex(firstPlayerIndex), playerTwo);
//
//		assertFalse(game.getIsReversed());
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo);
//	}
//
//	@Test
//	public void playReverseFivePlayersMaxTurns() {
//		final int numOfPlayers = 5;
//		final int initialNumOfPlayers = 2;
//		final int zerothPlayerIndex = 0;
//		final int firstPlayerIndex = 1;
//		final int secondPlayerIndex = 2;
//		final int thirdPlayerIndex = 3;
//		final int fourthPlayerIndex = 4;
//		final int numOfTurnsStart = 12;
//		final int numOfTurnsEnd = 11;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).anyTimes();
//		EasyMock.expect(playerThree.getIsDead()).andReturn(false).anyTimes();
//
//		EasyMock.replay(deck, rand, playerOne,
//				playerTwo, playerThree, playerFour, playerFive);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(initialNumOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setCurrentPlayerTurn(fourthPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(numOfTurnsStart);
//		game.playReverse();
//
//		assertEquals(game.getPlayerTurn(), zerothPlayerIndex);
//		assertEquals(game.getNumberOfTurns(), numOfTurnsEnd);
//		assertEquals(game.getPlayerAtIndex(zerothPlayerIndex), playerFive);
//		assertEquals(game.getPlayerAtIndex(firstPlayerIndex), playerFour);
//		assertEquals(game.getPlayerAtIndex(secondPlayerIndex), playerThree);
//		assertEquals(game.getPlayerAtIndex(thirdPlayerIndex), playerTwo);
//		assertEquals(game.getPlayerAtIndex(fourthPlayerIndex), playerOne);
//
//		assertTrue(game.getIsReversed());
//
//		EasyMock.verify(deck, rand, playerOne,
//				playerTwo, playerThree, playerFour, playerFive);
//	}
//
//	@Test
//	public void playSkipOneTurnWithRegSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 0;
//		final int numberOfPlayerTurns = 1;
//		final int indexOfTurnPostSkip = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip( false));
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipOneTurnWithSuperSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 0;
//		final int numberOfPlayerTurns = 1;
//		final int indexOfTurnPostSkip = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip( true));
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipTwoTurnWithRegSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 1;
//		final int numberOfPlayerTurns = 2;
//		final int indexOfTurnPostSkip = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(false));
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipTwoTurnWithSuperSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 0;
//		final int numberOfPlayerTurns = 2;
//		final int indexOfTurnPostSkip = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(true));
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipThreeTurnWithRegSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 2;
//		final int numberOfPlayerTurns = 3;
//		final int indexOfTurnPostSkip = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(false));
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipThreeTurnWithSuperSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 0;
//		final int numberOfPlayerTurns = 3;
//		final int indexOfTurnPostSkip = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(true));
//
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipMaxTurnWithRegSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 5;
//		final int numberOfPlayerTurns = 6;
//		final int indexOfTurnPostSkip = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(false));
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipMaxTurnWithSuperSkip() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurnsPostSkip = 0;
//		final int numberOfPlayerTurns = 6;
//		final int indexOfTurnPostSkip = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		assertEquals(numberOfPlayerTurnsPostSkip, game.playSkip(true));
//
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		assertEquals(indexOfTurnPostSkip, game.getPlayerTurn());
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipMoreThanMaxTurnWithSuperSkipThrowsError() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurns = 7;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		String expectedMessage  = "Number of turns must be between 1 and 6.";
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
//			game.playSkip(true);
//		});
//		String actualMessage = exception.getMessage();
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		assertEquals(expectedMessage, actualMessage);
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playSkipLessThanMinTurnWithSuperSkipThrowsError() {
//		final int numOfPlayers = 5;
//		final int numberOfPlayerTurns = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		String expectedMessage  = "Number of turns must be between 1 and 6.";
//		game.setCurrentPlayerNumberOfTurns(numberOfPlayerTurns);
//		Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
//			game.playSkip(true);
//		});
//		String actualMessage = exception.getMessage();
//		assertEquals(expectedMessage, actualMessage);
//		assertEquals(game.getGameType(), GameType.STREAKING_KITTENS);
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue);
//	}
//
//
//	@Test
//	public void incrementPlayerTurnTwoAlivePlayers() {
//		final int numOfPlayers = 2;
//		final int playerTurn = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.incrementPlayerTurn();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementPlayerTurnThirdDeadPlayerSkipped() {
//		final int numOfPlayers = 4;
//		final int playerTurnStart = 1;
//		final int playerTurn = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player3.getIsDead()).andReturn(true);
//		EasyMock.expect(player4.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(playerTurnStart);
//
//		game.incrementPlayerTurn();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementPlayerTurnFourthDeadPlayerSkipped() {
//		final int numOfPlayers = 4;
//		final int playerTurnStart = 2;
//		final int playerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player4.getIsDead()).andReturn(true);
//		EasyMock.expect(player1.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(playerTurnStart);
//		game.incrementPlayerTurn();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementPlayerTurnFourPlayers() {
//		final int numOfPlayers = 4;
//		final int playerTurnStart = 3;
//		final int playerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player1.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(playerTurnStart);
//		game.incrementPlayerTurn();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playMarkTwoPlayersOneCard() {
//		final int numOfPlayers = 2;
//		final int markedPlayerIndex = 1;
//		final int markedCardIndex = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo};
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(playerTwo.getCardAt(0)).andReturn(card).times(2);
//		EasyMock.expect(playerTwo.getHandSize()).andReturn(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//		card.markCard();
//
//
//		EasyMock.expect(card.checkIfMarked()).andReturn(true).once();
//		deck.setNumberOfPlayers(numOfPlayers);
//
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(playerTwo.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//
//		EasyMock.expect(playerTwo.getCardAt(0)).andReturn(card);
//		EasyMock.expect(card.getCardType()).andReturn(domain.game.CardType.SKIP);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo, card);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//		game.playMark(markedPlayerIndex, markedCardIndex);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//		assertEquals(game.getCardType(markedPlayerIndex,
//				markedCardIndex), domain.game.CardType.SKIP);
//		assertTrue(game.getPlayerAtIndex(markedPlayerIndex)
//				.getCardAt(markedCardIndex).checkIfMarked());
//		EasyMock.verify(deck, rand, playerOne, playerTwo, card);
//	}
//
//	@Test
//	public void playMarkTwoPlayersOneCardFirstPlayer() {
//		final int numOfPlayers = 2;
//		final int markedPlayerIndex = 0;
//		final int markedCardIndex = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo};
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(playerOne.getCardAt(0)).andReturn(card).times(2);
//		EasyMock.expect(playerOne.getHandSize()).andReturn(1);
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false).once();
//		card.markCard();
//
//		EasyMock.expect(playerOne.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//
//		EasyMock.expect(card.checkIfMarked()).andReturn(true).once();
//		deck.setNumberOfPlayers(numOfPlayers);
//
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(playerOne.getCardAt(0)).andReturn(card);
//		EasyMock.expect(card.getCardType()).andReturn(domain.game.CardType.SKIP);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//		game.playMark(markedPlayerIndex, markedCardIndex);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//		assertEquals(game.getCardType(markedPlayerIndex,
//				markedCardIndex), domain.game.CardType.SKIP);
//		assertTrue(game.getPlayerAtIndex(markedPlayerIndex)
//				.getCardAt(markedCardIndex).checkIfMarked());
//		EasyMock.verify(deck, rand, playerOne, playerTwo, card);
//	}
//
//	@Test
//	public void playMarkTwoPlayersOneCardCardIndexOut() {
//		final int numOfPlayers = 2;
//		final int markedPlayerIndex = 1;
//		final int markedCardIndex = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo};
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(playerTwo.getHandSize()).andReturn(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerTwo.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("cardIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo, card);
//	}
//
//	@Test
//	public void playMarkTwoPlayersOneCardPlayerIndexOut() {
//		final int numOfPlayers = 2;
//		final int markedPlayerIndex = 2;
//		final int markedCardIndex = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo};
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerTwo.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//
//		assertTrue(game.checkIfPlayerHasCard(1, domain.game.CardType.SKIP));
//		try {
//
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("playerIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo, card);
//	}
//
//	@Test
//	public void playMarkFivePlayersOneCard() {
//		final int numOfPlayers = 5;
//		final int markedPlayerIndex = 4;
//		final int markedCardIndex = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		final int playerFiveHandSize = 3;
//		EasyMock.expect(playerFive.getHandSize()).andReturn(playerFiveHandSize);
//
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card cardTwo = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		EasyMock.expect(playerFive.getCardAt(2)).andReturn(card).anyTimes();
//		EasyMock.expect(card.checkIfMarked()).andReturn(true).once();
//
//		EasyMock.expect(playerFive.getCardAt(1)).andReturn(cardTwo).anyTimes();
//		EasyMock.expect(cardTwo.checkIfMarked()).andReturn(false).once();
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		card.markCard();
//		EasyMock.expectLastCall();
//
//		EasyMock.expect(card.getCardType()).andReturn(domain.game.CardType.SKIP);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo, playerThree
//				, playerFour, playerFive, card, cardTwo);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//		game.playMark(markedPlayerIndex, markedCardIndex);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//
//		assertTrue(game.getPlayerAtIndex(markedPlayerIndex)
//				.getCardAt(markedCardIndex).checkIfMarked());
//
//		assertFalse(game.getPlayerAtIndex(markedPlayerIndex).getCardAt(1).checkIfMarked());
//		assertEquals(game.getCardType(markedPlayerIndex,
//				markedCardIndex), domain.game.CardType.SKIP);
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, cardTwo);
//	}
//
//	@Test
//	public void playMarkFivePlayersOneCardCardIndexOut() {
//		final int numOfPlayers = 5;
//		final int markedPlayerIndex = 4;
//		final int markedCardIndex = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		final int playerFiveHandSize = 3;
//		EasyMock.expect(playerFive.getHandSize()).andReturn(playerFiveHandSize);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("cardIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//	}
//
//	@Test
//	public void playMarkFivePlayersOneCardPlayerIndexOut() {
//		final int numOfPlayers = 5;
//		final int correctPlayerIndex = 4;
//		final int markedPlayerIndex = 5;
//		final int markedCardIndex = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//		assertTrue(game.checkIfPlayerHasCard(correctPlayerIndex, domain.game.CardType.SKIP));
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("playerIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//	}
//
//	@Test
//	public void playMarkFivePlayersOneCardPlayerExploded() {
//		final int numOfPlayers = 5;
//		final int markedPlayerIndex = 4;
//		final int markedCardIndex = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerFive.getIsDead()).andReturn(true).once();
//		Random rand = EasyMock.createMock(Random.class);
//
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("Player is dead", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//	}
//
//
//	@Test
//	public void playMarkFivePlayersOneCardCardIndexNegative() {
//		final int numOfPlayers = 5;
//		final int markedPlayerIndex = 4;
//		final int markedCardIndex = -1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		final int playerFiveHandSize = 3;
//		EasyMock.expect(playerFive.getHandSize()).andReturn(playerFiveHandSize);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//		assertTrue(game.checkIfPlayerHasCard(markedPlayerIndex, domain.game.CardType.SKIP));
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("cardIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne,
//				playerTwo, playerThree, playerFour, playerFive, card);
//	}
//
//	@Test
//	public void playMarkFivePlayersOneCardPlayerIndexNegative() {
//		final int numOfPlayers = 5;
//		final int correctPlayerIndex = 4;
//		final int markedPlayerIndex = -1;
//		final int markedCardIndex = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerFive.hasCard(domain.game.CardType.SKIP)).andReturn(true).once();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.STREAKING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setNumberOfPlayers(numOfPlayers);
//
//		assertTrue(game.checkIfPlayerHasCard(correctPlayerIndex, domain.game.CardType.SKIP));
//		try {
//			game.playMark(markedPlayerIndex, markedCardIndex);
//
//		} catch (IllegalArgumentException e) {
//			assertEquals("playerIndex out of Bounds", e.getMessage());
//		}
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//	}
//
//	@Test
//	public void playAttackPlayer0() {
//
//		final int numOfPlayers = 5;
//		final int attackedPlayerTurn = 1;
//		final int attackedPlayerNumberOfTurns = 2;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//
//
//		game.setNumberOfAttacks(0);
//		game.setCurrentPlayerTurn(0);
//		assertFalse(game.getAttacked());
//		game.playAttack();
//		assertTrue(game.getAttacked());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//	}
//
//	@Test
//	public void playAttack10Player5() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 4;
//		final int initialNumberOfPlayerTurns = 10;
//		final int attackedPlayerTurn = 0;
//		final int attackedPlayerNumberOfTurns = 12;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false).once();
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(initialPlayerTurn);
//		assertFalse(game.getAttacked());
//		game.playAttack();
//		assertTrue(game.getAttacked());
//
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//	}
//
//	@Test
//	public void playAttack10Player4() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 3;
//		final int initialNumberOfPlayerTurns = 10;
//		final int attackedPlayerTurn = 4;
//		final int attackedPlayerNumberOfTurns = 12;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(initialPlayerTurn);
//		assertFalse(game.getAttacked());
//		game.playAttack();
//		assertTrue(game.getAttacked());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//	}
//
//	@Test
//	public void playTargetedAttackPlayer0() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 2;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(0);
//		game.setCurrentPlayerTurn(0);
//		assertFalse(game.getAttacked());
//		game.playTargetedAttack(initialPlayerTurn);
//		assertTrue(game.getAttacked());
//		assertEquals(game.getAttackCounter(), initialPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), initialNumberOfPlayerTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//	}
//
//	@Test
//	public void playTargetedAttack10Player4() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 10;
//		final int attackedPlayerTurn = 4;
//		final int attackedPlayerNumberOfTurns = 12;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.IMPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		assertFalse(game.getAttacked());
//		game.playTargetedAttack(attackedPlayerTurn);
//
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertTrue(game.getAttacked());
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card);
//
//	}
//
//	@Test
//	public void playAttackPhaseOneTargetedAttack() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 4;
//		final int initialNumberOfPlayerTurns = 0;
//		final int attackedPlayerTurn = 0;
//		final int attackedPlayerNumberOfTurns = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(0);
//		EasyMock.expectLastCall().andReturn(true).times(1);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(0).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.IMPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//
//		game.addAttackQueue(0);
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(0), attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseSixAttacks() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 1;
//		final int attackedPlayerTurn = 1;
//		final int attackedPlayerNumberOfTurns = 12;
//		final int normalAttack = 5;
//		final int numAttacks = 6;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(normalAttack);
//		EasyMock.expectLastCall().andReturn(true).times(numAttacks);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerThree.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFour.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).times(2);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.EXPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(0);
//		game.setCurrentPlayerNumberOfTurns(1);
//		for (int index = 0; index < numAttacks; index++) {
//			game.addAttackQueue(normalAttack);
//		}
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getNumberOfTurns(), 0);
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(1), attackedPlayerNumberOfTurns);
//		assertTrue(game.getAttacked());
//
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseOneAttackTenTurns() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 10;
//		final int attackedPlayerTurn = 1;
//		final int attackedPlayerNumberOfTurns = 2;
//		final int normalAttack = 5;
//		final int numAttacks = 1;
//		final int endNumberOfTurns = 9;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(normalAttack);
//		EasyMock.expectLastCall().andReturn(true).times(numAttacks);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).times(1);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(attackedPlayerNumberOfTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(0);
//		game.setCurrentPlayerNumberOfTurns(initialNumberOfPlayerTurns);
//		for (int index = 0; index < numAttacks; index++) {
//			game.addAttackQueue(normalAttack);
//		}
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getNumberOfTurns(), endNumberOfTurns);
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(1), attackedPlayerNumberOfTurns);
//		assertTrue(game.getAttacked());
//
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseFiveAttacks() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 1;
//		final int attackedPlayerTurn = 0;
//		final int attackedPlayerNumberOfTurns = 10;
//		final int numberOfTurns = 9;
//		final int normalAttack = 5;
//		final int numAttacks = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(normalAttack);
//		EasyMock.expectLastCall().andReturn(true).times(numAttacks);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerThree.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFour.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerOne.getIsDead()).andReturn(false).once();
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		for (int index = 0; index < numAttacks; index++) {
//			game.addAttackQueue(normalAttack);
//		}
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getNumberOfTurns(), numberOfTurns);
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(0), 1);
//		assertFalse(game.getAttacked());
//
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseFiveAttacksOneTargetedAttack() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		final int initialNumberOfPlayerTurns = 1;
//		final int attackedPlayerTurn = 4;
//		final int attackedPlayerNumberOfTurns = 12;
//		final int normalAttack = 5;
//		final int targetedAttackIndex = 2;
//		final int numAttacks = 6;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		final int normalAttacksAddedFirst = 3;
//		attackQueue.add(normalAttack);
//		EasyMock.expectLastCall().andReturn(true).times(normalAttacksAddedFirst);
//		final int targetedAttacksAdded = 1;
//		attackQueue.add(targetedAttackIndex);
//		EasyMock.expectLastCall().andReturn(true).times(targetedAttacksAdded);
//		final int normalAttacksAddedSecond = 2;
//		attackQueue.add(normalAttack);
//		EasyMock.expectLastCall().andReturn(true).times(normalAttacksAddedSecond);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerThree.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFour.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(targetedAttackIndex).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFour.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(normalAttack).times(1);
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false).once();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.IMPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		for (int index = 0; index < numAttacks; index++) {
//			if (index == targetedAttackIndex + 1) {
//				game.addAttackQueue(targetedAttackIndex);
//
//			} else {
//				game.addAttackQueue(normalAttack);
//			}
//		}
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(attackedPlayerTurn),
//				attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseOneTargetedAttack3() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 4;
//		final int initialNumberOfPlayerTurns = 1;
//		final int attackedPlayerTurn = 3;
//		final int attackedPlayerNumberOfTurns = 2;
//		final int targetedAttackIndex = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(targetedAttackIndex);
//		EasyMock.expectLastCall().andReturn(true).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0))
//				.andReturn(targetedAttackIndex).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.IMPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.addAttackQueue(targetedAttackIndex);
//
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(attackedPlayerTurn),
//				attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseOneTargetedAttack4() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 3;
//		final int initialNumberOfPlayerTurns = 1;
//		final int attackedPlayerTurn = 4;
//		final int attackedPlayerNumberOfTurns = 2;
//		final int targetedAttackIndex = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(targetedAttackIndex);
//		EasyMock.expectLastCall().andReturn(true).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0))
//				.andReturn(targetedAttackIndex).times(1);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//			GameType.IMPLODING_KITTENS, deck, players, rand,
//			attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.addAttackQueue(targetedAttackIndex);
//
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(attackedPlayerTurn),
//				attackedPlayerNumberOfTurns);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playAttackPhaseOneTargetedAttackTenCounter() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 4;
//		final int initialNumberOfPlayerTurns = 0;
//		final int attackedPlayerTurn = 0;
//		final int attackedPlayerNumberOfTurns = 2;
//		final int numTurnCounter = 10;
//		final int numTurnCounterFinal = 12;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//
//
//		attackQueue.add(0);
//		EasyMock.expectLastCall().andReturn(true).times(1);
//
//		deck.setNumberOfPlayers(numOfPlayers);
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(false).times(1);
//		EasyMock.expect(attackQueue.remove(0)).andReturn(0).times(1);
//
//		EasyMock.expect(attackQueue.isEmpty()).andReturn(true).times(2);
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {numTurnCounter, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setNumberOfPlayers(numOfPlayers);
//		game.setNumberOfAttacks(initialNumberOfPlayerTurns);
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//
//		game.addAttackQueue(0);
//		assertFalse(game.getAttacked());
//		game.startAttackPhase();
//
//
//		assertTrue(game.isAttackQueueEmpty());
//		assertEquals(game.getAttackCounter(), attackedPlayerTurn);
//		assertEquals(game.getNumberOfAttacks(), attackedPlayerNumberOfTurns);
//		assertEquals(game.getTurnCountOfPlayer(0), numTurnCounterFinal);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playShuffleGameDeckTwoCards() {
//		final int numOfPlayers = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(firstCard).anyTimes();
//
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.expect(rand.nextInt(2)).andReturn(0).anyTimes();
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		int timesToShuffle = 1;
//		game.playShuffle(timesToShuffle);
//		assertEquals(deck.getCardAtIndex(0), firstCard);
//		assertEquals(timesToShuffle, 1);
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//	}
//
//	@Test
//	public void playShuffleGameDeckZeroCards() {
//		final int numOfPlayers = 5;
//		final int deckSize = 0;
//		final int timesShuffled = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//		EasyMock.expect(deck.getDeckSize()).andReturn(0).anyTimes();
//
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().andVoid();
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		int timesToShuffle = 1;
//		game.playShuffle(timesToShuffle);
//		assertEquals(deck.getDeckSize(), deckSize);
//		assertEquals(timesToShuffle, timesShuffled);
//
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//	}
//
//	@Test
//	public void playShuffleGameDeckThreeCards() {
//		final int numOfPlayers = 5;
//		final int timesShuffled = 100;
//		final int randomInputOne = 3;
//		final int randomInputTwo = 2;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(firstCard).anyTimes();
//		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(firstCard).anyTimes();
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().andVoid().anyTimes();
//
//		EasyMock.expect(rand.nextInt(randomInputOne)).andReturn(0).anyTimes();
//		EasyMock.expect(rand.nextInt(randomInputTwo)).andReturn(0).anyTimes();
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		final int timesToShuffle = 100;
//		game.playShuffle(timesToShuffle);
//		assertEquals(deck.getCardAtIndex(0), firstCard);
//		assertEquals(deck.getCardAtIndex(1), firstCard);
//		assertEquals(timesToShuffle, timesShuffled);
//
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand
//				, attackQueue, firstCard, secondCard);
//	}
//
//	@ParameterizedTest
//	@EnumSource(names = {"NOPE", "DEFUSE", "ATTACK",
//			"SHUFFLE", "SKIP", "SEE_THE_FUTURE", "CAT_ONE",
//			"CAT_TWO", "CAT_THREE", "CAT_FOUR", "EXPLODING_KITTEN",
//			"STREAKING_KITTEN", "SWAP_TOP_AND_BOTTOM", "GARBAGE_COLLECTION",
//			"CURSE_OF_THE_CAT_BUTT", "ALTER_THE_FUTURE", "CATOMIC_BOMB",
//			"SUPER_SKIP", "MARK", "IMPLODING_KITTEN", "REVERSE",
//			"FERAL_CAT", "TARGETED_ATTACK", "DRAW_FROM_THE_BOTTOM"
//	})
//	public void removeOneCard(domain.game.CardType cardType) {
//		final int numOfPlayers = 2;
//		final int playerIndex = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player.getIndexOfCard(cardType)).andReturn(0);
//		EasyMock.expect(player.removeCardFromHand(0))
//				.andReturn(cardType);
//
//		EasyMock.expect(player.hasCard(cardType)).andReturn(false).once();
//
//		EasyMock.replay(deck, player, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player1, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.removeCardFromHand(playerIndex, cardType);
//		assertFalse(game.checkIfPlayerHasCard(playerIndex, cardType));
//
//		EasyMock.verify(deck, player, player1, player2, rand
//				, attackQueue);
//	}
//
//	@ParameterizedTest
//	@EnumSource(names = {"NOPE", "DEFUSE", "ATTACK",
//			"SHUFFLE", "SKIP", "SEE_THE_FUTURE", "CAT_ONE",
//			"CAT_TWO", "CAT_THREE", "CAT_FOUR", "EXPLODING_KITTEN",
//			"STREAKING_KITTEN", "SWAP_TOP_AND_BOTTOM", "GARBAGE_COLLECTION",
//			"CURSE_OF_THE_CAT_BUTT", "ALTER_THE_FUTURE", "CATOMIC_BOMB",
//			"SUPER_SKIP", "MARK", "IMPLODING_KITTEN", "REVERSE",
//			"FERAL_CAT", "TARGETED_ATTACK", "DRAW_FROM_THE_BOTTOM"
//	})
//	public void removeThreeCardDupe(domain.game.CardType cardType) {
//		final int numOfPlayers = 2;
//		final int playerIndex = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//		final int timesIndexAcquired = 3;
//
//		EasyMock.expect(player.getIndexOfCard(cardType)).andReturn(0)
//				.times(timesIndexAcquired);
//
//		final int timesCardRemoved = 3;
//
//		EasyMock.expect(player.removeCardFromHand(0))
//				.andReturn(cardType).times(timesCardRemoved);
//
//		EasyMock.expect(player.hasCard(cardType)).andReturn(false).once();
//
//		EasyMock.replay(deck, player, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player, player1, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.removeCardFromHand(playerIndex, cardType);
//		game.removeCardFromHand(playerIndex, cardType);
//		game.removeCardFromHand(playerIndex, cardType);
//		assertFalse(game.checkIfPlayerHasCard(playerIndex, cardType));
//
//		EasyMock.verify(deck, player, player1, player2, rand
//				, attackQueue);
//	}
//
//	@ParameterizedTest
//	@ValueSource(ints = {NEGATIVE_ONE_PLAYER, FIVE_PLAYERS})
//	public void playExplodingKittenInvalidPlayer(int playerIndex) {
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//		domain.game.Player[] players = {};
//
//		Random rand = EasyMock.createMock(Random.class);
//		EasyMock.replay(deck, rand);
//
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(2,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//
//		Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
//			game.playExplodingKitten(playerIndex);
//		});
//		String actualMessage = exception.getMessage();
//		String expectedMessage = "Invalid player index.";
//		assertEquals(expectedMessage, actualMessage);
//
//		EasyMock.verify(deck, rand);
//	}
//
//	@Test
//	public void playExplodingKittenWithDefuse() {
//		final int numOfPlayers = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player[] players = {player, player, player, player, player};
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(true).anyTimes();
//		EasyMock.expect(player.getIsDead()).andReturn(false).anyTimes();
//
//		EasyMock.replay(deck, player, rand
//				, attackQueue);
//
//		int playerIndex = 0;
//		boolean isPlayerExploded = game.playExplodingKitten(playerIndex);
//		assertFalse(isPlayerExploded);
//
//		int numberOfAlivePlayers = game.checkNumberOfAlivePlayers();
//		assertEquals(numberOfAlivePlayers, numOfPlayers);
//
//
//		EasyMock.verify(deck, player, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playExplodingKittenWithoutDefuse() {
//		final int numOfPlayers = 5;
//		final int numOfAlivePlayers = 4;
//		final int playerIndex = 4;
//		final int currentPlayerIndex = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player otherPlayer = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {otherPlayer, otherPlayer, otherPlayer, otherPlayer, player};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(1);
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(false).anyTimes();
//		EasyMock.expect(player.getIsDead()).andReturn(true).anyTimes();
//		player.setIsDead();
//		EasyMock.expect(otherPlayer.getIsDead()).andReturn(false).anyTimes();
//
//		EasyMock.replay(deck, player, otherPlayer, rand
//				, attackQueue);
//
//		boolean isPlayerExploded = game.playExplodingKitten(playerIndex);
//		assertTrue(isPlayerExploded);
//
//		int numberOfAlivePlayers = game.checkNumberOfAlivePlayers();
//		assertEquals(numberOfAlivePlayers, numOfAlivePlayers);
//
//		assertEquals(0, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, player, otherPlayer, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playExplodingKittenWithoutDefuseOneAlivePlayerLeft() {
//		final int numOfPlayers = 5;
//		final int numOfAlivePlayers = 1;
//		final int playerIndex = 4;
//		final int currentPlayerIndex = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player deadPlayer = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player alivePlayer = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Player[] players = {deadPlayer, deadPlayer, deadPlayer, alivePlayer, player};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(1);
//
//		EasyMock.expect(player.hasCard(domain.game.CardType.DEFUSE)).andReturn(false).anyTimes();
//		EasyMock.expect(player.getIsDead()).andReturn(true).anyTimes();
//		player.setIsDead();
//		EasyMock.expect(deadPlayer.getIsDead()).andReturn(true).anyTimes();
//		EasyMock.expect(alivePlayer.getIsDead()).andReturn(false).anyTimes();
//
//		EasyMock.replay(deck, player, deadPlayer, alivePlayer, rand,
//				attackQueue);
//
//		boolean isPlayerExploded = game.playExplodingKitten(playerIndex);
//		assertTrue(isPlayerExploded);
//
//		int numberOfAlivePlayers = game.checkNumberOfAlivePlayers();
//		assertEquals(numberOfAlivePlayers, numOfAlivePlayers);
//
//		assertEquals(1, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, player, deadPlayer, alivePlayer, rand
//				, attackQueue);
//	}
//
//	@Test
//	public void playGarbageCollectionWithEmptyDeck() {
//		final int numOfPlayers = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card skipCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//		EasyMock.expect(skipCard.getCardType()).andReturn(domain.game.CardType.SKIP).anyTimes();
//		deck.insertCard(domain.game.CardType.SKIP, 1, false);
//		EasyMock.expectLastCall().once();
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().once();
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(skipCard).once();
//		EasyMock.replay(deck, player1, player2, player3, player4, player5,
//				rand, attackQueue, skipCard);
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.playGarbageCollection(domain.game.CardType.SKIP);
//		assertEquals(skipCard, deck.getCardAtIndex(0));
//
//		assertEquals(domain.game.CardType.SKIP, skipCard.getCardType());
//		EasyMock.verify(deck, player1, player2, player3, player4, player5,
//				rand, attackQueue, skipCard);
//	}
//
//	@Test
//	public void playGarbageCollectionWithOneCardInDeck() {
//		final int numOfPlayers = 5;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(firstCard.getCardType()).andReturn(domain.game.CardType.SKIP).anyTimes();
//		EasyMock.expect(secondCard.getCardType()).andReturn(domain.game.CardType.ATTACK).anyTimes();
//
//		deck.insertCard(domain.game.CardType.ATTACK, 1, false);
//		EasyMock.expectLastCall().once();
//
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().once();
//
//		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(secondCard).once();
//		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(firstCard).once();
//
//		EasyMock.replay(deck, player1, player2, player3, player4,
//				player5, rand, attackQueue, firstCard, secondCard);
//
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.playGarbageCollection(domain.game.CardType.ATTACK);
//
//
//		assertEquals(secondCard, deck.getCardAtIndex(0));
//		assertEquals(domain.game.CardType.ATTACK, secondCard.getCardType());
//		assertEquals(firstCard, deck.getCardAtIndex(1));
//		assertEquals(domain.game.CardType.SKIP, firstCard.getCardType());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, player5,
//				rand, attackQueue, firstCard, secondCard);
//	}
//
//	@Test
//	public void playGarbageCollectionWithThreeCardsWithDupInDeck() {
//		final int numOfPlayers = 5;
//		final int firstIndex = 0;
//		final int secondIndex = 1;
//		final int thirdIndex = 2;
//		final int fourthIndex = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card firstCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card secondCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card thirdCard = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Card fourthCard = EasyMock.createMock(domain.game.Card.class);
//
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(firstCard.getCardType()).andReturn(domain.game.CardType.SKIP).anyTimes();
//		EasyMock.expect(secondCard.getCardType()).andReturn(domain.game.CardType.ATTACK).anyTimes();
//		EasyMock.expect(thirdCard.getCardType()).andReturn(domain.game.CardType.SKIP).anyTimes();
//		EasyMock.expect(fourthCard.getCardType()).andReturn(domain.game.CardType.SHUFFLE).anyTimes();
//
//		deck.insertCard(domain.game.CardType.ATTACK, 1, false);
//		EasyMock.expectLastCall().once();
//
//		deck.shuffleDeck();
//		EasyMock.expectLastCall().once();
//
//		EasyMock.expect(deck.getCardAtIndex(firstIndex)).andReturn(firstCard).once();
//		EasyMock.expect(deck.getCardAtIndex(secondIndex)).andReturn(thirdCard).once();
//		EasyMock.expect(deck.getCardAtIndex(thirdIndex)).andReturn(fourthCard).once();
//		EasyMock.expect(deck.getCardAtIndex(fourthIndex)).andReturn(secondCard).once();
//
//
//		EasyMock.replay(deck, player1, player2, player3, player4, player5, rand,
//				attackQueue, firstCard,
//				secondCard, thirdCard, fourthCard);
//
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.playGarbageCollection(domain.game.CardType.ATTACK);
//
//		assertEquals(firstCard, deck.getCardAtIndex(firstIndex));
//		assertEquals(domain.game.CardType.SKIP, firstCard.getCardType());
//		assertEquals(thirdCard, deck.getCardAtIndex(secondIndex));
//		assertEquals(domain.game.CardType.SKIP, thirdCard.getCardType());
//		assertEquals(fourthCard, deck.getCardAtIndex(thirdIndex));
//		assertEquals(domain.game.CardType.SHUFFLE, fourthCard.getCardType());
//		assertEquals(secondCard, deck.getCardAtIndex(fourthIndex));
//
//		assertEquals(domain.game.CardType.ATTACK, secondCard.getCardType());
//
//		EasyMock.verify(deck, player1, player2, player3, player4,
//				player5, rand, attackQueue, firstCard,
//				secondCard, thirdCard, fourthCard);
//
//	}
//
//	@Test
//	public void playDefuseNegIdxToInsertExplodingKitten() {
//		final int currentPlayerIndex = 0;
//		final int playerIndex = 0;
//		final int idxToInsertExplodingKitten = -1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.insertExplodingKittenAtIndex(idxToInsertExplodingKitten);
//		EasyMock.expectLastCall()
//				.andThrow(new UnsupportedOperationException(
//					"Invalid index. " +
//					"Cannot insert into negative index.")).once();
//		EasyMock.replay(deck, player, rand, attackQueue);
//
//		domain.game.Player[] players = {player, player, player, player, player};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS,
//				GameType.EXPLODING_KITTENS, deck, players,
//				rand, attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//
//		UnsupportedOperationException exception =
//				assertThrows(UnsupportedOperationException.class, () -> {
//					game.playDefuse(idxToInsertExplodingKitten, playerIndex);
//				});
//		assertEquals("Invalid index. Cannot insert into negative index.",
//				exception.getMessage());
//
//		EasyMock.verify(deck, player, rand, attackQueue);
//	}
//
//	@Test
//	public void playDefuseMaxPlusOneIdxToInsertExplodingKitten() {
//		final int currentPlayerIndex = 4;
//		final int playerIndex = 4;
//		final int currentDeckSize = 20;
//		final int idxToInsertExplodingKitten = currentDeckSize + 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.insertExplodingKittenAtIndex(idxToInsertExplodingKitten);
//		EasyMock.expectLastCall()
//			.andThrow(new UnsupportedOperationException(
//					"Invalid index. " +
//					"Cannot insert into index greater than deck size."))
//				.once();
//		EasyMock.replay(deck, player, rand, attackQueue);
//
//		domain.game.Player[] players = {player, player, player, player, player};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS,
//				GameType.EXPLODING_KITTENS, deck, players,
//				rand, attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//
//		UnsupportedOperationException exception =
//				assertThrows(UnsupportedOperationException.class, () -> {
//					game.playDefuse(idxToInsertExplodingKitten, playerIndex);
//				});
//		assertEquals("Invalid index. " +
//				"Cannot insert into index greater than deck size.",
//				exception.getMessage());
//
//		EasyMock.verify(deck, player, rand, attackQueue);
//	}
//
//	@Test
//	public void playDefuseValidIdxToInsertExplodingKittenIsCurrentPlayer() {
//		final int currentPlayerIndex = 4;
//		final int playerIndex = 4;
//		final  int fiveCardsHand = 5;
//		final int defuseIdx = 0;
//		final int explodingKittenIdx = 0;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card explodingKittenCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.insertExplodingKittenAtIndex(explodingKittenIdx);
//		EasyMock.expectLastCall().once();
//		EasyMock.expect(player.getIndexOfCard(domain.game.CardType.DEFUSE)).andReturn(defuseIdx);
//		EasyMock.expect(player.removeCardFromHand(defuseIdx)).andReturn(domain.game.CardType.DEFUSE);
//
//		EasyMock.expect(deck.getCardAtIndex(explodingKittenIdx))
//				.andReturn(explodingKittenCard);
//		EasyMock.expect(explodingKittenCard.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN);
//		EasyMock.expect(player.getHandSize()).andReturn(fiveCardsHand - 1);
//
//		EasyMock.replay(deck, explodingKittenCard, player, rand, attackQueue);
//
//		domain.game.Player[] players = {player, player, player, player, player};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS,
//				GameType.EXPLODING_KITTENS, deck, players,
//				rand, attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(1);
//
//		game.playDefuse(explodingKittenIdx, playerIndex);
//		assertEquals(game.getDeckCardType(explodingKittenIdx), domain.game.CardType.EXPLODING_KITTEN);
//		assertEquals(game.getHandSize(playerIndex), fiveCardsHand - 1);
//		assertEquals(0, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, explodingKittenCard, player, rand, attackQueue);
//	}
//
//	@Test
//	public void playDefuseValidIdxToInsertExplodingKittenNotCurrentPlayer() {
//		final int currentPlayerIndex = 0;
//		final int playerIndex = 4;
//		final  int fiveCardsHand = 5;
//		final int defuseIdx = 0;
//		final int explodingKittenIdx = 0;
//
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		domain.game.Card explodingKittenCard = EasyMock.createMock(domain.game.Card.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		deck.insertExplodingKittenAtIndex(explodingKittenIdx);
//		EasyMock.expectLastCall().once();
//		EasyMock.expect(player.getIndexOfCard(domain.game.CardType.DEFUSE)).andReturn(defuseIdx);
//		EasyMock.expect(player.removeCardFromHand(defuseIdx)).andReturn(domain.game.CardType.DEFUSE);
//
//		EasyMock.expect(deck.getCardAtIndex(explodingKittenIdx))
//				.andReturn(explodingKittenCard);
//		EasyMock.expect(explodingKittenCard.getCardType())
//				.andReturn(domain.game.CardType.EXPLODING_KITTEN);
//		EasyMock.expect(player.getHandSize()).andReturn(fiveCardsHand - 1);
//
//		EasyMock.replay(deck, explodingKittenCard, player, rand, attackQueue);
//
//		domain.game.Player[] players = {player, player, player, player, player};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS,
//				GameType.EXPLODING_KITTENS, deck, players,
//				rand, attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//		game.setCurrentPlayerNumberOfTurns(1);
//
//		game.playDefuse(explodingKittenIdx, playerIndex);
//		assertEquals(game.getDeckCardType(explodingKittenIdx), CardType.EXPLODING_KITTEN);
//		assertEquals(game.getHandSize(playerIndex), fiveCardsHand - 1);
//		assertEquals(1, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, explodingKittenCard, player, rand, attackQueue);
//	}
//
//	@ParameterizedTest
//	@ValueSource(ints = { NEGATIVE_ONE_PLAYER, FIVE_PLAYERS})
//	public void playDefuseInvalidPlayerIndex(int playerIndex) {
//		final int currentPlayerIndex = 0;
//		final int currentDeckSize = 20;
//		int idxToInsertExplodingKitten = currentDeckSize + 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.replay(deck, player, rand, attackQueue);
//
//		domain.game.Player[] players = {player, player, player, player, player};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS,
//				GameType.EXPLODING_KITTENS, deck, players,
//				rand, attackQueue, turnTracker);
//		game.setCurrentPlayerTurn(currentPlayerIndex);
//
//		UnsupportedOperationException exception =
//				assertThrows(UnsupportedOperationException.class, () -> {
//					game.playDefuse(idxToInsertExplodingKitten, playerIndex);
//				});
//		assertEquals("Invalid player index.",
//				exception.getMessage());
//
//		EasyMock.verify(deck, player, rand, attackQueue);
//	}
//
//
//
//	@Test
//	public void setPlayerNumberOfTurnsAllOnes() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setCurrentPlayerNumberOfTurns(0);
//
//		game.setPlayerNumberOfTurns();
//
//		assertEquals(game.getNumberOfTurns(), 1);
//
//		assertEquals(game.getTurnCountOfPlayer(initialPlayerTurn), 1);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void setPlayerNumberOfTurnsTwelveTurns() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 3;
//		final int numOfTurns = 12;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//		final int fourthPlayerNumOfTurns = 12;
//		int[] turnTracker = {1, 1, 1, fourthPlayerNumOfTurns, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setCurrentPlayerNumberOfTurns(0);
//
//		game.setPlayerNumberOfTurns();
//
//		assertEquals(game.getNumberOfTurns(), numOfTurns);
//
//		assertEquals(game.getTurnCountOfPlayer(initialPlayerTurn), 1);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void setPlayerNumberOfTurnsAllDifferent() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 2;
//		final int numOfTurns = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//		final int secondPlayerNumOfTurns = 2;
//		final int thirdPlayerNumOfTurns = 4;
//		final int fourthPlayerNumOfTurns = 6;
//		final int fifthPlayerNumOfTurns = 8;
//		int[] turnTracker = {1, secondPlayerNumOfTurns, thirdPlayerNumOfTurns,
//				fourthPlayerNumOfTurns, fifthPlayerNumOfTurns};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setCurrentPlayerNumberOfTurns(0);
//
//		game.setPlayerNumberOfTurns();
//
//		assertEquals(game.getNumberOfTurns(), numOfTurns);
//
//		assertEquals(game.getTurnCountOfPlayer(initialPlayerTurn), 1);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void setTurnToTargetedIndexIfAttackedNotAttacked() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		final int attackCounter = 4;
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(attackCounter);
//		game.setAttacked(false);
//		game.setTurnToTargetedIndexIfAttackOccurred();
//
//		assertEquals(game.getPlayerTurn(), initialPlayerTurn);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void setTurnToTargetedIndexIfAttackedNotDead() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(domain.game.Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//		EasyMock.expect(playerFive.getIsDead()).andReturn(false);
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		final int attackCounter = 4;
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(attackCounter);
//		game.setAttacked(true);
//		game.setTurnToTargetedIndexIfAttackOccurred();
//
//		assertEquals(game.getPlayerTurn(), attackCounter);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void setTurnToTargetedIndexIfAttackedAttackedAndDead() {
//		final int numOfPlayers = 5;
//		final int initialPlayerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player playerOne = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerTwo = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerThree = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFour = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player playerFive = EasyMock.createMock(domain.game.Player.class);
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		domain.game.Card card = EasyMock.createMock(Card.class);
//		domain.game.Player[] players = {playerOne, playerTwo, playerThree, playerFour, playerFive};
//
//		Random rand = EasyMock.createMock(Random.class);
//		;
//		EasyMock.expect(playerOne.getIsDead()).andReturn(true);
//		EasyMock.expect(playerTwo.getIsDead()).andReturn(false);
//		EasyMock.replay(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.IMPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		final int attackCounter = 0;
//		game.setCurrentPlayerTurn(initialPlayerTurn);
//		game.setAttackCounter(attackCounter);
//		game.setAttacked(true);
//		game.setTurnToTargetedIndexIfAttackOccurred();
//
//		assertEquals(game.getPlayerTurn(), 1);
//
//		EasyMock.verify(deck, rand, playerOne, playerTwo,
//				playerThree, playerFour, playerFive, card, attackQueue);
//
//	}
//
//	@Test
//	public void playImplodingKittenCurrentPlayerTurnIsZero() {
//		final int ZeroCurrentPlayerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player otherPlayer = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		domain.game.Player[] players = {player, otherPlayer, otherPlayer,
//				otherPlayer, otherPlayer};
//
//		int[] turnTracker = new int[players.length];
//		domain.game.Game game = new domain.game.Game(FIVE_PLAYERS, GameType.IMPLODING_KITTENS,
//				deck, players, rand, new ArrayList<>(), turnTracker);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.setCurrentPlayerTurn(ZeroCurrentPlayerTurn);
//
//		player.setIsDead();
//		EasyMock.expectLastCall().once();
//
//		EasyMock.replay(deck, player, otherPlayer, rand);
//
//		game.playImplodingKitten();
//
//		assertEquals(ZeroCurrentPlayerTurn, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, player, otherPlayer, rand);
//	}
//
//
//	@Test
//	public void playImplodingKittenCurrentPlayerTurnIsFour() {
//		final int ZeroCurrentPlayerTurn = 0;
//		final int FourCurrentPlayerTurn = 4;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player5 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		domain.game.Player[] players = {player1, player2, player3, player4, player5};
//		int[] turnTracker = new int[players.length];
//		domain.game.Game game = new domain.game.Game( FIVE_PLAYERS, GameType.IMPLODING_KITTENS,
//				deck, players, rand, new ArrayList<>(), turnTracker);
//		game.setCurrentPlayerNumberOfTurns(1);
//		game.setCurrentPlayerTurn(FourCurrentPlayerTurn);
//
//		player5.setIsDead();
//		EasyMock.expectLastCall().once();
//
//		EasyMock.replay(deck, player1, player2, player3, player4, player5, rand);
//
//		game.playImplodingKitten();
//
//		assertEquals(ZeroCurrentPlayerTurn, game.getNumberOfTurns());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, player5, rand);
//	}
//
//	@Test
//	public void incrementAttackTurnTwoAlivePlayers() {
//		final int numOfPlayers = 2;
//		final int playerTurn = 1;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player2.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setAttackCounter(0);
//		game.incrementAttackCounter();
//
//		assertEquals(playerTurn, game.getAttackCounter());
//
//		EasyMock.verify(deck, player1, player2, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementAttackTurnThirdDeadPlayerSkipped() {
//		final int numOfPlayers = 4;
//		final int attackCounterStart = 1;
//		final int playerTurn = 3;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player3.getIsDead()).andReturn(true);
//		EasyMock.expect(player4.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4};
//
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setAttackCounter(attackCounterStart);
//
//		game.incrementAttackCounter();
//
//		assertEquals(playerTurn, game.getAttackCounter());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementAttackTurnFourthDeadPlayerSkipped() {
//		final int numOfPlayers = 4;
//		final int attackCounterStart = 2;
//		final int playerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(domain.game.Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player4.getIsDead()).andReturn(true);
//		EasyMock.expect(player1.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		domain.game.Player[] players = {player1, player2, player3, player4};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new domain.game.Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setAttackCounter(attackCounterStart);
//
//		game.incrementAttackCounter();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//
//	}
//
//	@Test
//	public void incrementAttackTurnFourPlayers() {
//		final int numOfPlayers = 4;
//		final int attackCounterStart = 3;
//		final int playerTurn = 0;
//		domain.game.Deck deck = EasyMock.createMock(Deck.class);
//		domain.game.Player player1 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player2 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player3 = EasyMock.createMock(domain.game.Player.class);
//		domain.game.Player player4 = EasyMock.createMock(domain.game.Player.class);
//		Random rand = EasyMock.createMock(Random.class);
//
//		ArrayList<Integer> attackQueue = EasyMock.createMock(ArrayList.class);
//
//		EasyMock.expect(player1.getIsDead()).andReturn(false);
//
//		EasyMock.replay(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//		Player[] players = {player1, player2, player3, player4};
//		int[] turnTracker = {1, 1, 1, 1, 1};
//		domain.game.Game game = new Game(numOfPlayers,
//				GameType.EXPLODING_KITTENS, deck, players, rand,
//				attackQueue, turnTracker);
//		game.setAttackCounter(attackCounterStart);
//
//		game.incrementAttackCounter();
//
//		assertEquals(playerTurn, game.getPlayerTurn());
//
//		EasyMock.verify(deck, player1, player2, player3, player4, rand
//				, attackQueue);
//	}
//
//}

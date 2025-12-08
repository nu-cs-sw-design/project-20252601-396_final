package model;

import model.exceptions.InvalidMoveException;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void testRegisterAndNotifySingleObserver() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Game game = new Game(new ArrayList<>(), mockDeck);
        String msg = "Test Message";

        GameObserver observer = EasyMock.createMock(GameObserver.class);

        observer.onGameMessage(msg);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDeck, observer);

        game.registerObservers(observer);
        game.notifyObservers(msg);

        EasyMock.verify(observer);
    }

    @Test
    public void testNotifyMultipleObservers() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Game game = new Game(new ArrayList<>(), mockDeck);
        String msg = "Test Message";

        GameObserver obs1 = EasyMock.createMock(GameObserver.class);
        GameObserver obs2 = EasyMock.createMock(GameObserver.class);

        obs1.onGameMessage(msg);
        EasyMock.expectLastCall();

        obs2.onGameMessage(msg);
        EasyMock.expectLastCall();

        EasyMock.replay(obs1, obs2, mockDeck);

        game.registerObservers(obs1);
        game.registerObservers(obs2);

        game.notifyObservers(msg);

        EasyMock.verify(obs1, obs2);
    }

    @Test
    public void testNotifyWithNoObserversDoesNotCrash() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        EasyMock.replay(mockDeck);
        String msg = "Test Message";

        Game game = new Game(new ArrayList<>(), mockDeck);

        game.notifyObservers(msg);

        EasyMock.verify(mockDeck);
    }

    @Test
    public void testStartSetsUpGame() {
        Deck deck = EasyMock.createMock(Deck.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p1.getId()).andStubReturn(0);
        EasyMock.expect(p2.getId()).andStubReturn(1);

        ArrayList<Player> players = new ArrayList<>(Arrays.asList(p1, p2));
        Game game = new Game(players, deck);
        game.registerObservers(observer);

        // add defuse
        p1.addCard(EasyMock.anyObject(Card.class));
        p2.addCard(EasyMock.anyObject(Card.class));

        // add 6 Nopes, 6 Shuffles, 6 Swaps
        deck.insertAt(EasyMock.eq(0), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(18);

        // first Shuffle
        deck.shuffle();
        EasyMock.expectLastCall();

        // deal 4 cards each
        // stub deck size to be > 0
        EasyMock.expect(deck.getDeckSize()).andStubReturn(18);

        // mock drawCard
        Card dummyCard = EasyMock.createMock(Card.class);
        EasyMock.expect(deck.draw()).andReturn(dummyCard).anyTimes();

        // players to receive 4 cards each
        p1.addCard(dummyCard);
        EasyMock.expectLastCall().times(4);
        p2.addCard(dummyCard);
        EasyMock.expectLastCall().times(4);

        // insert Exploding Kittens * (players.size() - 1)
        deck.insertAt(EasyMock.eq(0), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(1);

        // second Shuffle
        deck.shuffle();
        EasyMock.expectLastCall();

        // expect 2 general notifications
        observer.onGameMessage(EasyMock.anyString());
        EasyMock.expectLastCall().times(2);

        // expect turn change notification for Player 0
        observer.onTurnChanged(0);
        EasyMock.expectLastCall();
        EasyMock.replay(deck, p1, p2, observer, dummyCard);

        game.start();

        EasyMock.verify(deck, p1, p2, observer);
    }

    @Test
    public void testDrawCardSafeAdvancesTurnAndSkipsDead() {
        // Scenario: P0 (Current) -> P1 (Dead) -> P2 (Alive)
        // Drawing a safe card should add to P0's hand, skip P1, and pass turn to P2.

        Player p0 = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p0.getId()).andStubReturn(0);
        EasyMock.expect(p1.getId()).andStubReturn(1);
        EasyMock.expect(p2.getId()).andStubReturn(2);

        Card safeCard = EasyMock.createMock(Card.class);
        EasyMock.expect(safeCard.getType()).andStubReturn(CardType.SHUFFLE);
        EasyMock.expect(deck.draw()).andReturn(safeCard);

        p0.addCard(safeCard);
        EasyMock.expectLastCall();

        // nextTurn logic
        EasyMock.expect(p1.getDead()).andReturn(true);
        EasyMock.expect(p2.getDead()).andReturn(false);

        observer.onGameMessage(EasyMock.contains("drew SHUFFLE"));
        EasyMock.expectLastCall();

        observer.onGameMessage(EasyMock.contains("Player 2's turn"));
        EasyMock.expectLastCall();

        observer.onTurnChanged(2);
        EasyMock.expectLastCall();

        EasyMock.replay(p0, p1, p2, deck, observer, safeCard);

        ArrayList<Player> players = new ArrayList<>(Arrays.asList(p0, p1, p2));
        Game game = new Game(players, deck);
        game.registerObservers(observer);

        game.drawCard();

        assertEquals(p2, game.getCurrentPlayer());
        EasyMock.verify(p0, p1, p2, deck, observer);
    }

    @Test
    public void testDrawExplodingKittenWithDefuse() {
        // Scenario: P0 (Current, Dead) -> P1 (Alive) -> P2 (Alive)
        Player p0 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p0.getId()).andStubReturn(0);

        Card kitten = EasyMock.createMock(Card.class);
        EasyMock.expect(kitten.getType()).andStubReturn(CardType.EXPLODING_KITTEN);
        EasyMock.expect(deck.draw()).andReturn(kitten);

        // has Defuse -> remove it
        EasyMock.expect(p0.hasCard(CardType.DEFUSE)).andReturn(true);
        p0.removeDefuse();
        EasyMock.expectLastCall();

        // verify onRequestExplosionInsertIndex is called
        observer.onGameMessage(EasyMock.contains("drew EXPLODING_KITTEN"));
        observer.onGameMessage(EasyMock.contains("used a Defuse"));
        observer.onDefuseUsed(0);
        observer.onRequestExplosionInsertIndex();
        EasyMock.expectLastCall();

        EasyMock.replay(p0, deck, observer, kitten);

        Game game = new Game(Arrays.asList(p0), deck);
        game.registerObservers(observer);

        game.drawCard();

        assertEquals(GamePhase.EXPLOSION_PHASE, game.getPhase());
        // current player's turn not ended yet since they would be prompted for
        // index to insert exploding kitten
        assertEquals(p0, game.getCurrentPlayer());

        EasyMock.verify(p0, deck, observer);
    }

    @Test
    public void testDrawExplodingKittenNoDefuseGameContinues() {
        // Scenario: P0 dies, P1 survives. Game continues to P1.
        Player p0 = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p0.getId()).andStubReturn(0);
        EasyMock.expect(p1.getId()).andStubReturn(1);

        // deck returns Kitten
        Card kitten = EasyMock.createMock(Card.class);
        EasyMock.expect(kitten.getType()).andStubReturn(CardType.EXPLODING_KITTEN);
        EasyMock.expect(deck.draw()).andReturn(kitten);

        // p0 has no defuse -> Dies
        EasyMock.expect(p0.hasCard(CardType.DEFUSE)).andReturn(false);
        p0.setDead(true);
        EasyMock.expectLastCall();

        // checkGameOver: P0 dies, P1 and P2 are left (Count=2). Game continues.
        Player p2 = EasyMock.createMock(Player.class);
        EasyMock.expect(p2.getId()).andStubReturn(2);

        // checkGameOver check:
        EasyMock.expect(p0.getDead()).andReturn(true).anyTimes();
        EasyMock.expect(p1.getDead()).andReturn(false).anyTimes();
        EasyMock.expect(p2.getDead()).andReturn(false).anyTimes();

        observer.onGameMessage(EasyMock.contains("drew EXPLODING_KITTEN"));
        observer.onGameMessage(EasyMock.contains("BOOM"));
        observer.onPlayerEliminated(0);
        observer.onGameMessage(EasyMock.contains("Player 1's turn"));
        observer.onTurnChanged(1);

        EasyMock.replay(p0, p1, p2, deck, observer, kitten);

        Game game = new Game(Arrays.asList(p0, p1, p2), deck);
        game.registerObservers(observer);

        game.drawCard();

        assertEquals(GamePhase.NORMAL, game.getPhase());
        assertEquals(p1, game.getCurrentPlayer());

        EasyMock.verify(observer);
    }

    @Test
    public void testDrawExplodingKittenTriggersGameOver() {
        // Scenario: P0 dies, only P1 left. P1 Wins.
        Player p0 = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p0.getId()).andStubReturn(0);
        EasyMock.expect(p1.getId()).andStubReturn(1);

        Card kitten = EasyMock.createMock(Card.class);
        EasyMock.expect(kitten.getType()).andStubReturn(CardType.EXPLODING_KITTEN);
        EasyMock.expect(deck.draw()).andReturn(kitten);

        // P0 Dies
        EasyMock.expect(p0.hasCard(CardType.DEFUSE)).andReturn(false);
        p0.setDead(true);
        EasyMock.expect(p0.getDead()).andReturn(true).anyTimes();
        EasyMock.expect(p1.getDead()).andReturn(false).anyTimes();

        observer.onGameMessage(EasyMock.contains("drew EXPLODING_KITTEN"));
        observer.onGameMessage(EasyMock.contains("BOOM"));
        observer.onPlayerEliminated(0);


        observer.onGameMessage(EasyMock.contains("GAME OVER! We have a winner: Player 1"));
        EasyMock.expectLastCall();

        EasyMock.replay(p0, p1, deck, observer, kitten);

        Game game = new Game(Arrays.asList(p0, p1), deck);
        game.registerObservers(observer);

        game.drawCard();

        assertEquals(GamePhase.GAME_OVER, game.getPhase());
        EasyMock.verify(observer);
    }

    @Test
    public void testPlayActiveCardStartsPendingAction() {
        // Setup Game
        Player p0 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);
        Game game = new Game(Arrays.asList(p0), deck);
        game.registerObservers(observer);

        EasyMock.expect(p0.getId()).andStubReturn(0);

        Card activeCard = EasyMock.createMock(Card.class);
        CardAction action = EasyMock.createMock(CardAction.class);

        EasyMock.expect(p0.getCard(0)).andReturn(activeCard);
        EasyMock.expect(activeCard.getAction()).andReturn(action);
        EasyMock.expect(activeCard.getType()).andReturn(CardType.SHUFFLE).anyTimes();

        p0.removeCard(0);
        EasyMock.expectLastCall().andReturn(activeCard);

        observer.onCardPlayed(0, activeCard);
        observer.onRequestNope(0, CardType.SHUFFLE);
        EasyMock.expectLastCall();
        observer.onGameMessage(EasyMock.anyString());
        EasyMock.expectLastCall();

        EasyMock.replay(p0, deck, observer, activeCard, action);

        game.playCard(0, 0); // Player 0, Card Index 0

        assertEquals(GamePhase.NOPE_PHASE, game.getPhase());
        assertNotNull(game.getPendingAction());

        EasyMock.verify(p0, observer);
    }

    @Test
    public void testPlayPassiveCardExecutesImmediately() {
        Player p0 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Game game = new Game(Arrays.asList(p0), deck);

        Card passiveCard = EasyMock.createMock(Card.class);
        ActionPassive passiveAction = new ActionPassive("Exploding Kitten Incoming!");

        EasyMock.expect(p0.getCard(0)).andReturn(passiveCard);
        EasyMock.expect(passiveCard.getAction()).andReturn(passiveAction);
        EasyMock.expect(passiveCard.getType()).andReturn(CardType.EXPLODING_KITTEN).anyTimes();

        passiveCard.performAction(game);
        EasyMock.expectLastCall();

        EasyMock.replay(p0, deck, passiveCard);

        game.playCard(0, 0);

        assertEquals(GamePhase.NORMAL, game.getPhase());
        assertNull(game.getPendingAction());

        EasyMock.verify(p0);
    }

    @Test
    public void testPlayCardThrowsExceptionIfNotTurn() {
        Player p0 = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);

        Game game = new Game(Arrays.asList(p0, p1), deck);

        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(p1.getCard(0)).andReturn(card);
        EasyMock.expect(card.getAction()).andReturn(EasyMock.createMock(CardAction.class));
        EasyMock.expect(card.getType()).andReturn(CardType.SHUFFLE);

        EasyMock.replay(p0, p1, card);

        String expectedMessage  = "Invalid move detected: It is not your turn!";

        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            game.playCard(1, 0);
        });

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testPlayNopeSuccessDuringNopePhase() {
        Player p0 = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Game game = new Game(Arrays.asList(p0), deck);

        // switch into NOPE phase
        Card nopeCard = EasyMock.createMock(Card.class);
        EasyMock.expect(p0.getCard(0)).andReturn(nopeCard);
        EasyMock.expect(nopeCard.getAction()).andReturn(EasyMock.createMock(CardAction.class));
        EasyMock.expect(nopeCard.getType()).andReturn(CardType.NOPE).anyTimes();

        // Play an active card to switch phase to Nope
        Card activeCard = EasyMock.createMock(Card.class);
        EasyMock.expect(p0.getCard(1)).andReturn(activeCard);
        EasyMock.expect(activeCard.getAction()).andReturn(EasyMock.createMock(CardAction.class));
        EasyMock.expect(activeCard.getType()).andReturn(CardType.SHUFFLE).anyTimes();
        EasyMock.expect(p0.removeCard(1)).andReturn(activeCard);

        // NOPE PHASE
        nopeCard.performAction(game);
        EasyMock.expectLastCall();
        EasyMock.expect(p0.removeCard(0)).andReturn(nopeCard); // Nope is removed
        EasyMock.replay(p0, deck, activeCard, nopeCard);

        // start Pending Action
        game.playCard(0, 1);
        assertEquals(GamePhase.NOPE_PHASE, game.getPhase());

        // play Nope
        game.playCard(0, 0);

        EasyMock.verify(p0, nopeCard);
    }

    @Test
    public void testPlayNopeThrowsExceptionInNormalPhase() {
        Player p0 = EasyMock.createMock(Player.class);
        Game game = new Game(Arrays.asList(p0), EasyMock.createMock(Deck.class));

        Card nopeCard = EasyMock.createMock(Card.class);
        EasyMock.expect(p0.getCard(0)).andReturn(nopeCard);
        EasyMock.expect(nopeCard.getAction()).andReturn(EasyMock.createMock(CardAction.class));
        EasyMock.expect(nopeCard.getType()).andReturn(CardType.NOPE).anyTimes();

        EasyMock.replay(p0, nopeCard);

        String expectedMessage  = "Invalid move detected: You can only play Nope when an action is pending.";

        // Game is in NORMAL phase by default
        Exception exception = assertThrows(InvalidMoveException.class, () -> {
            game.playCard(0, 0);
        });

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testHandleNopeInputFalseResolvesActionAndResetsPhase() {
        // Scenario: Player not want to Nope
        // PendingAction.resolve() is called, phase becomes NORMAL, pending becomes null.

        Deck deck = EasyMock.createMock(Deck.class);
        Game game = new Game(new ArrayList<>(), deck);

        PendingAction pending = EasyMock.createMock(PendingAction.class);

        // setters strictly only used for testing
        game.setPhase(GamePhase.NOPE_PHASE);
        game.setPendingAction(pending);

        pending.resolve(game);
        EasyMock.expectLastCall();

        EasyMock.replay(pending, deck);

        game.resolvePendingAction();

        assertEquals(GamePhase.NORMAL, game.getPhase());
        assertNull(game.getPendingAction());

        EasyMock.verify(pending);
    }

    @Test
    public void testHandleNopeInputTrueDoesNothing() {
        // Scenario: Player want to Nope
        // should NOT resolve the action. State should remain in NOPE_PHASE

        Deck deck = EasyMock.createMock(Deck.class);
        Game game = new Game(new ArrayList<>(), deck);

        // setters strictly only used for testing
        game.setPhase(GamePhase.NOPE_PHASE);
        game.setPendingAction(null);
        EasyMock.replay(deck);

        game.resolvePendingAction();

        assertEquals(GamePhase.NOPE_PHASE, game.getPhase());
        assertNull(game.getPendingAction());

        EasyMock.verify(deck);
    }

    @Test
    public void testHandleExplosionInsertSuccess() {
        // Scenario: Player 0 just defused. Inserts card at index 3.
        // Game should resume and pass turn to Player 1.

        Deck deck = EasyMock.createMock(Deck.class);
        Player p0 = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        GameObserver observer = EasyMock.createMock(GameObserver.class);

        EasyMock.expect(p0.getId()).andStubReturn(0);
        EasyMock.expect(p1.getId()).andStubReturn(1);

        deck.insertAt(EasyMock.eq(3), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall();
        EasyMock.expect(p1.getDead()).andReturn(false);
        observer.onGameMessage(EasyMock.contains("re-inserted"));
        EasyMock.expectLastCall();
        observer.onGameMessage(EasyMock.contains("Player 1's turn"));
        EasyMock.expectLastCall();
        observer.onTurnChanged(1);
        EasyMock.expectLastCall();
        EasyMock.replay(deck, p0, p1, observer);

        List<Player> players = Arrays.asList(p0, p1);
        Game game = new Game(players, deck);
        game.registerObservers(observer);

        game.setPhase(GamePhase.EXPLOSION_PHASE);

        game.handleExplosionInsert(3);

        // after explosion, gamePhase should get back to NORMAL
        assertEquals(GamePhase.NORMAL, game.getPhase());
        assertEquals(p1, game.getCurrentPlayer());

        EasyMock.verify(deck, p0, p1, observer);
    }

    @Test
    public void testHandleExplosionInsertThrowsExceptionIfWrongPhase() {
        Deck deck = EasyMock.createMock(Deck.class);
        Game game = new Game(new ArrayList<>(), deck);

        Exception e = assertThrows(InvalidMoveException.class, () -> {
            game.handleExplosionInsert(0);
        });

        assertEquals("Invalid move detected: Not in explosion phase.", e.getMessage());
    }

    @Test
    public void testHandleExplosionInsertPropagatesDeckExceptions() {
        Deck deck = EasyMock.createMock(Deck.class);
        Player p0 = EasyMock.createMock(Player.class);

        deck.insertAt(EasyMock.anyInt(), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().andThrow(new InvalidMoveException("Index out of bounds"));
        EasyMock.replay(deck, p0);

        Game game = new Game(Arrays.asList(p0), deck);
        game.setPhase(GamePhase.EXPLOSION_PHASE);

        Exception e = assertThrows(InvalidMoveException.class, () -> {
            game.handleExplosionInsert(500);
        });

        // Verify phase did NOT change to NORMAL because exception happened before that line
        assertEquals(GamePhase.EXPLOSION_PHASE, game.getPhase());
        assertEquals("Invalid move detected: Index out of bounds", e.getMessage());

        EasyMock.verify(deck);
    }
}
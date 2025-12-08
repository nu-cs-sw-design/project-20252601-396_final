package model;
import controller.GameController;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import view.GameUI;

import java.util.ArrayList;
import java.util.List;

public class GameControllerTest {
    @Test
    public void testHandleNormalTurn_DrawCard() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player player = EasyMock.createMock(Player.class);

        game.start();
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NORMAL).times(2);

        EasyMock.expect(game.getCurrentPlayer()).andReturn(player);
        ui.displayHand(player);
        EasyMock.expectLastCall().once();
        EasyMock.expect(ui.promptCommand()).andReturn("D");
        game.drawCard();
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString()); // End message

        EasyMock.replay(game, ui, player);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, player);
    }

    @Test
    public void testHandleNormalTurn_PlayCard_Valid() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player player = EasyMock.createMock(Player.class);

        final int playerId = 1;
        EasyMock.expect(player.getId()).andReturn(playerId).anyTimes();

        game.start();
        EasyMock.expectLastCall();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NORMAL).times(2);

        EasyMock.expect(game.getCurrentPlayer()).andReturn(player).anyTimes();
        ui.displayHand(player);
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptCommand()).andReturn("P 2");
        game.playCard(playerId, 2);
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.replay(game, ui, player);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, player);
    }

    @Test
    public void testHandleNormalTurn_PlayCard_Invalid() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player player = EasyMock.createMock(Player.class);

        final int playerId = 1;
        EasyMock.expect(player.getId()).andReturn(playerId).anyTimes();

        game.start();
        EasyMock.expectLastCall();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NORMAL).times(2);
        EasyMock.expect(game.getCurrentPlayer()).andReturn(player).anyTimes();

        ui.displayHand(player);
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptCommand()).andReturn("P 2 2");
        ui.onGameMessage("Invalid format. Usage: P <cardIndex>");
        EasyMock.expectLastCall().once();

        ui.displayHand(player);
        EasyMock.expectLastCall();
        EasyMock.expect(ui.promptCommand()).andReturn("P 2");
        game.playCard(playerId, 2);
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.replay(game, ui, player);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, player);
    }

    @Test
    public void testHandleExplosionInput() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Deck deck = EasyMock.createMock(model.Deck.class);

        game.start();
        EasyMock.expectLastCall();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.EXPLOSION_PHASE).times(2);
        EasyMock.expect(game.getDeck()).andStubReturn(deck);
        EasyMock.expect(deck.getDeckSize()).andStubReturn(0);
        EasyMock.expect(ui.promptInsertionIndex(0)).andReturn(0);
        game.handleExplosionInsert(0);
        EasyMock.expectLastCall().once();

        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.replay(game, ui);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui);
    }

    @Test
    public void testHandleNopePhase_ZeroNopes_WithSkips() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);
        model.PendingAction pendingAction = EasyMock.createMock(model.PendingAction.class);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        game.start();
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NOPE_PHASE).times(2);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getPendingAction()).andReturn(pendingAction).anyTimes();
        EasyMock.expect(pendingAction.getNopeCount()).andReturn(0).anyTimes();

        EasyMock.expect(p1.getDead()).andReturn(true);
        EasyMock.expect(p2.getDead()).andReturn(false);
        EasyMock.expect(p2.hasCard(CardType.NOPE)).andReturn(false);

        EasyMock.expect(p3.getDead()).andReturn(false);
        EasyMock.expect(p3.hasCard(CardType.NOPE)).andReturn(true);
        EasyMock.expect(p3.getId()).andReturn(2);

        EasyMock.expect(ui.promptPlayNope(2, "Do you want to CANCEL the action?")).andReturn(false);
        ui.onGameMessage(EasyMock.contains("Nope phase finished"));
        EasyMock.expectLastCall().once();
        game.resolvePendingAction();
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, p1, p2, p3, pendingAction);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, p1, p2, p3, pendingAction);
    }

    @Test
    public void testHandleNopePhase_OneNope_ActionCancelled() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);
        model.PendingAction pendingAction = EasyMock.createMock(model.PendingAction.class);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        game.start();
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NOPE_PHASE).times(2);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getPendingAction()).andReturn(pendingAction).anyTimes();

        EasyMock.expect(p1.getDead()).andReturn(true);

        EasyMock.expect(pendingAction.getNopeCount()).andReturn(0);
        EasyMock.expect(p2.getDead()).andReturn(false);
        EasyMock.expect(p2.hasCard(CardType.NOPE)).andReturn(true);
        EasyMock.expect(p2.getId()).andStubReturn(1);

        EasyMock.expect(ui.promptPlayNope(1, "Do you want to CANCEL the action?")).andReturn(true);
        EasyMock.expect(p2.getNopeIndex()).andReturn(0);

        game.playCard(1, 0);
        EasyMock.expectLastCall().once();
        ui.onGameMessage(EasyMock.contains("NOPE played"));
        EasyMock.expectLastCall().once();

        EasyMock.expect(pendingAction.getNopeCount()).andReturn(1);

        EasyMock.expect(p3.getDead()).andReturn(false);
        EasyMock.expect(p3.hasCard(CardType.NOPE)).andReturn(true);
        EasyMock.expect(p3.getId()).andStubReturn(2);
        EasyMock.expect(ui.promptPlayNope(2, "The action is currently BLOCKED. Do you want to NOPE the NOPE? (Revive the action)")).andReturn(false);

        ui.onGameMessage(EasyMock.contains("Nope phase finished"));
        EasyMock.expectLastCall().once();
        game.resolvePendingAction();
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.expectLastCall().once();

        EasyMock.replay(game, ui, p1, p2, p3, pendingAction);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, p1, p2, p3, pendingAction);
    }

    @Test
    public void testHandleNopePhase_TwoNopes_ActionRevived() {
        Game game = EasyMock.createMock(Game.class);
        GameUI ui = EasyMock.createMock(GameUI.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);
        model.PendingAction pendingAction = EasyMock.createMock(model.PendingAction.class);

        List<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        game.start();
        EasyMock.expectLastCall();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.NOPE_PHASE).times(2);
        EasyMock.expect(game.getPlayers()).andReturn(players);
        EasyMock.expect(game.getPendingAction()).andReturn(pendingAction).anyTimes();

        EasyMock.expect(pendingAction.getNopeCount()).andReturn(0);
        EasyMock.expect(p1.getDead()).andReturn(false);
        EasyMock.expect(p1.hasCard(CardType.NOPE)).andReturn(true);
        EasyMock.expect(p1.getId()).andStubReturn(0);

        EasyMock.expect(ui.promptPlayNope(0, "Do you want to CANCEL the action?")).andReturn(true);
        EasyMock.expect(p1.getNopeIndex()).andReturn(0);
        game.playCard(0, 0);
        EasyMock.expectLastCall().once();
        ui.onGameMessage(EasyMock.contains("NOPE played"));
        EasyMock.expectLastCall().once();

        EasyMock.expect(p2.getDead()).andReturn(false);
        EasyMock.expect(p2.hasCard(CardType.NOPE)).andReturn(false);

        EasyMock.expect(pendingAction.getNopeCount()).andReturn(1);

        EasyMock.expect(p3.getDead()).andReturn(false);
        EasyMock.expect(p3.hasCard(CardType.NOPE)).andReturn(true);
        EasyMock.expect(p3.getId()).andStubReturn(2);

        EasyMock.expect(ui.promptPlayNope(2, "The action is currently BLOCKED. Do you want to NOPE the NOPE? (Revive the action)")).andReturn(true);
        EasyMock.expect(p3.getNopeIndex()).andReturn(2);
        game.playCard(2, 2);
        EasyMock.expectLastCall().once();
        ui.onGameMessage(EasyMock.contains("NOPE played"));
        EasyMock.expectLastCall().once();

        ui.onGameMessage(EasyMock.contains("Nope phase finished"));
        EasyMock.expectLastCall().once();
        game.resolvePendingAction();
        EasyMock.expectLastCall().once();
        EasyMock.expect(game.getPhase()).andReturn(GamePhase.GAME_OVER);
        ui.onGameMessage(EasyMock.anyString());
        EasyMock.expectLastCall().once();
        EasyMock.replay(game, ui, p1, p2, p3, pendingAction);

        GameController controller = new GameController(game, ui);
        controller.startGame();

        EasyMock.verify(game, ui, p1, p2, p3, pendingAction);
    }
}

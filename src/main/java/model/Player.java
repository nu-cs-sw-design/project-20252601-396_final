package model;

import model.exceptions.NotEnoughCardsException;

import java.util.List;

public class Player {
    private final int id;
    private List<Card> hand;
    private boolean isDead = false;

    public Player(int id) {
        this.id = id;
    }

    public boolean hasCard(CardType cardType) {
        for (Card card : hand) {
            if (card.getType() == cardType) {
                return true;
            }
        }
        return false;
    }
    public CardType removeCard(int index){
        if (index < 0 || index >= getHandSize()) {
            throw new NotEnoughCardsException(getHandSize());
        }
        CardType cardType = hand.get(index).getType();
        hand.remove(index);
        return cardType;
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public int getHandSize() {
        return hand.size();
    }
}

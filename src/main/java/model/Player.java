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
    public void removeDefuse(){
        for (Card card : hand) {
            if (card.getType() == CardType.DEFUSE) {
                hand.remove(card);
            }
        }
    }
    public Card removeCard(int index){
        if (index < 0 || index >= getHandSize()) {
            throw new NotEnoughCardsException(getHandSize());
        }
        Card card = hand.get(index);
        hand.remove(index);
        return card;
    }
    public Card getCard(int index){
        if (index < 0 || index >= getHandSize()) {
            throw new NotEnoughCardsException(getHandSize());
        }
        Card card = hand.get(index);
        return card;
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public int getHandSize() {
        return hand.size();
    }
    public int getId() {
        return id;
    }
    public boolean getDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }
}

package model;

import model.exceptions.InvalidMoveException;
import model.exceptions.NotEnoughCardsException;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int id;
    private ArrayList<Card> hand;
    private boolean isDead;

    public Player(int id, ArrayList<Card> hand) {
        this.id = id;
        this.hand = (hand != null) ? hand : new ArrayList<>();
        this.isDead = false;
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
        if (hand.size() == 0) {
            throw new NotEnoughCardsException(hand.size());
        }
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getType() == CardType.DEFUSE) {
                hand.remove(i);
                return;
            }
        }
    }
    public Card removeCard(int index){
        if (index < 0 || index >= getHandSize()) {
            throw new InvalidMoveException("Index out of bounds.");
        }
        Card card = hand.get(index);
        hand.remove(index);
        return card;
    }
    public Card getCard(int index){
        if (index < 0 || index >= getHandSize()) {
            throw new InvalidMoveException("Index out of bounds.");
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

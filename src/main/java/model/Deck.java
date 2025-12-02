package model;

import model.exceptions.InvalidMoveException;

import java.util.List;

public class Deck {
    private List<Card> cards;
    private static final String DRAW_FROM_EMPTY_DECK_EXCEPTION =
            "Cannot draw card from empty deck.";

    public Deck(){}
    public void shuffle(){

    }
    public Card draw(){
        if (cards.isEmpty()) {
            throw new InvalidMoveException(DRAW_FROM_EMPTY_DECK_EXCEPTION);
        }
        else {
            return cards.remove(cards.size() - 1);
        }
    }
    public void insertAt(Card card, int index){
        cards.add(index, card);
    }
    public void swapTopAndBottom(){

    }
    public int getDeckSize(){
        return cards.size();
    }
}

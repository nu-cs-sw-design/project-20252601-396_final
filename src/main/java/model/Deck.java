package model;

import model.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;
    private Random rand;
    private static final String DRAW_FROM_EMPTY_DECK_EXCEPTION =
            "Cannot draw card from empty cards.";

    public Deck(Random rand, ArrayList<Card> cards) {
        this.rand = rand;
        this.cards = cards;
    }
    public void shuffle(){
        //Fischer Yates Algorithm
        for (int deckIndex = cards.size() - 1; deckIndex > 0; deckIndex--) {
            int indexToSwap = rand.nextInt(deckIndex + 1);
            Card temporaryCard = cards.get(indexToSwap);
            cards.set(indexToSwap, cards.get(deckIndex));
            cards.set(deckIndex, temporaryCard);
        }
    }
    public Card draw(){
        if (cards.isEmpty()) {
            throw new InvalidMoveException(DRAW_FROM_EMPTY_DECK_EXCEPTION);
        }
        else {
            return cards.remove(cards.size() - 1);
        }
    }
    public void insertAt(int index, Card card){
        if (index < 0 || index > cards.size()) {
            throw new InvalidMoveException("Cannot insert card: Index " + index + " is out of bounds.");
        }
        else{
            cards.add(index, card);
        }
    }
    public void swapTopAndBottom() {
        if (cards.size() < 2) {
            throw new model.exceptions.NotEnoughCardsException(cards.size());
        }

        int bottomIndex = 0;
        int topIndex = cards.size() - 1;

        Card cardAtBottom = cards.get(bottomIndex);
        Card cardAtTop = cards.get(topIndex);

        cards.set(bottomIndex, cardAtTop);
        cards.set(topIndex, cardAtBottom);
    }
    public int getDeckSize(){
        return cards.size();
    }

    // strictly used only for test
    public ArrayList<Card> getCards(){
        return cards;
    }
}

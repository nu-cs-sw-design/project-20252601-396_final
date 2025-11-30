package model;

import java.util.List;

public class Player {
    private final int id;
    private List<Card> hand;
    private boolean isDead = false;

    public Player(int id) {
        this.id = id;
    }

    public boolean hasDefuse(){
        return false;
    }
    public void removeDefuse(){}
    // did not handle when hand has no card etc.
    public Card removeCard(int index){
        return hand.remove(index);
    }
    public void addCard(Card card){}
}

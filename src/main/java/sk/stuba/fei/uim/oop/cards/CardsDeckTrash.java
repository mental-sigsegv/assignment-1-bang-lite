package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;

public class CardsDeckTrash {
    private ArrayList<Card> cards;
    public CardsDeckTrash() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void clearCards() {
        this.cards.clear();
    }
}

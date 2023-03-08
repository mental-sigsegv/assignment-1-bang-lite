package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Collection;

public class CardsDeck {
    private ArrayList<Card> cards;

    public CardsDeck() {
        cards = new ArrayList<Card>();


        cards.add(new Beer());
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}

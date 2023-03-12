package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardDeck {
    private final ArrayList<Card> cards;

    // TODO remove this, instead use 1 constructor and decide based on argument
    public CardDeck(int i) {
        cards = new ArrayList<>();

        cards.add(new Dynamite());
        cards.add(new Barrel());
        cards.add(new Indians());
        cards.add(new Missed());
        cards.add(new Missed());
        cards.add(new Prison());
        cards.add(new Beer());
        cards.add(new Beer());
        cards.add(new Beer());
        cards.add(new Beer());
        cards.add(new CatBalou());
        cards.add(new StageCoach());

    }
    public CardDeck(String string) {
        cards = new ArrayList<>();
    }
    public CardDeck() {
        cards = new ArrayList<>();

        cards.add(new Dynamite());

        for (int i=0; i<2; i++) {
            cards.add(new Barrel());
            cards.add(new Indians());
        }
        for (int i=0; i<3; i++) {
            cards.add(new Prison());
        }
        for (int i=0; i<4; i++) {
            cards.add(new StageCoach());
        }
        for (int i=0; i<6; i++) {
            cards.add(new CatBalou());
        }
        for (int i=0; i<8; i++) {
            cards.add(new Beer());
        }
        for (int i=0; i<15; i++) {
            cards.add(new Missed());
        }
        for (int i=0; i<30; i++) {
            cards.add(new Bang());
        }

        Collections.shuffle(cards);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public Card getCard(int index) {
        Card cardToReturn = this.getCards().get(index);
        this.removeCard(index);
        return cardToReturn;
    }

    public void addCard(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(int index) {
        this.cards.remove(index);
    }

    public void removeCards() {
        this.cards.clear();
    }

    public int getSize() {
        return cards.size();
    }

    public void printCards(String name) {
        System.out.println(name + " deck");
        for (Card card : cards) {
            System.out.println(card.getName());
        }
    }
}

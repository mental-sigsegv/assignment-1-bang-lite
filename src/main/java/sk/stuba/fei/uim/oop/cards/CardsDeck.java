package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CardsDeck {
    private ArrayList<Card> cards;

    public CardsDeck() {
        cards = new ArrayList<Card>();

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
}

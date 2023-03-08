package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.Card;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_COLOR = "Brown";
    public Beer() {
        super(CARD_NAME, CARD_COLOR);
    }
//    @Override
//    public boolean canPlay() {
//        return false;
//    }
//
//    @Override
//    public boolean canPlay(int index) {
//        return false;
//    }

}

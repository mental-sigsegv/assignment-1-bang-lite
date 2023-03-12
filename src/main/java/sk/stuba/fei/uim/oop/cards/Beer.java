package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_COLOR = "Brown";
    private ArrayList<Card> cardsToThrowAway;
    public Beer() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {
        cardsToThrowAway = new ArrayList<Card>();
        cardsToThrowAway.add(this);

        System.out.println(CARD_NAME + " card was played and 1hp has been added to " + player.getName());
        player.addHealth();

        return cardsToThrowAway;
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

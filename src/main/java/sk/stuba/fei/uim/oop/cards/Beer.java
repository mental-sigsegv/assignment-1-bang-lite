package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_COLOR = "Brown";
    public Beer() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public void playCard(Player player) {
        System.out.println(CARD_NAME + " card was played and added 1hp to " + player.getName());
        player.addHealth();
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

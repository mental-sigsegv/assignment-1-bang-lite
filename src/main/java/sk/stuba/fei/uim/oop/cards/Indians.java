package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";
    private static final String CARD_COLOR = "Brown";

    public Indians() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public boolean canPlay() {
        return false;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {

        return null;
    }
}

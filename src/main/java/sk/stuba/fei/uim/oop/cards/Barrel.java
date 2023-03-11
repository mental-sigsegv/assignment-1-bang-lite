package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";
    private static final String CARD_COLOR = "Blue";

    public Barrel() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public void playCard(Player player) {

    }
}

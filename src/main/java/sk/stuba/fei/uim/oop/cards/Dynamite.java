package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    private static final String CARD_COLOR = "Blue";

    public Dynamite() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public void playCard(Player player) {

    }
}

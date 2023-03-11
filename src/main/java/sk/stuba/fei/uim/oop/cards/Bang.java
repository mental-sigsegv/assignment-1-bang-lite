package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    private static final String CARD_COLOR = "Brown";

    public Bang() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public void playCard(Player player) {
        Card missed = new Missed();
        if (player.hasCard(missed)) {
            int indexOfCard = player.getCardIndex(missed);
            player.removeCard(indexOfCard);
        } else {
            player.removeHealth();
        }
    }
}

package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    private static final String CARD_COLOR = "Brown";

    public Bang() {
        super(CARD_NAME, CARD_COLOR, false);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();
        Card missed = new Missed();
        if (player.hasCard(missed)) {
            int indexOfCard = player.getCardIndex(missed);
            Card removedCard = player.removeCard(indexOfCard);
            cardsToThrowAway.add(removedCard);
        } else {
            player.removeHealth();
        }
        cardsToThrowAway.add(this);
        return cardsToThrowAway;
    }
}

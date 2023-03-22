package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";
    public Missed(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return false;
    }
    @Override
    public void playCard(Player player) {
        super.playCard(player);

        player.removeCard(this);
        cardDeck.trash.add(this);
    }
}

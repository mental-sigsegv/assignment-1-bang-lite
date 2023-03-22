package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";
    public CatBalou(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return false;
    }

    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);
    }
}

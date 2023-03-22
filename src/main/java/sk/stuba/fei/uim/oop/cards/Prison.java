package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    public Prison(CardDeck cardDeck) {
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

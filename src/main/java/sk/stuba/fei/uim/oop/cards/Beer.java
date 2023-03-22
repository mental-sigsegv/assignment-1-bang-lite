package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    public Beer(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }
    @Override
    public boolean canPlay() {
        return true;
    }

    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);
        player.addHealth();
        cardDeck.trash.add(this);
    }
}

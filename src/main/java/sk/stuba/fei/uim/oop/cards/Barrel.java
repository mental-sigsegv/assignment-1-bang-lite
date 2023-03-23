package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;

import java.util.ArrayList;
import java.util.Objects;

public class Barrel extends Card {
    private static final String CARD_NAME = "Barrel";
    public Barrel(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay(Player player) {
        for (Card card : player.getActiveCards()) {
            if (card instanceof Barrel) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player);

        player.getCards().remove(this);
        player.getActiveCards().add(this);
    }

    public boolean checkChance() {
        return Math.random() < 1 / 4.0;
    }
}

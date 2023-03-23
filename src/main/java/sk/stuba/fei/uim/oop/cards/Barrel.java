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
    public boolean canPlay() {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player);

        for (Card card : player.getActiveCards()) {
            if (Objects.equals(card.getName(), "Barrel")) {
                System.out.println(ConsoleColors.RED + "--- You already have barrel in front of you! ---" + ConsoleColors.RESET);
                return;
            }
        }

        player.getCards().remove(this);
        player.getActiveCards().add(this);
    }

    public boolean checkChance(Player player) {
        if (Math.random() < 1/4.0) {
//            Barrel is not removed
//            player.getActiveCards().remove(this);
//            cardDeck.trash.add(this);
            return true;
        }
        return false;
    }
}

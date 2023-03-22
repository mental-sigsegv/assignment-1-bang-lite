package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;

import java.util.ArrayList;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    public Prison(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        Player target = player.chooseTarget(players);
        target.getActiveCards().add(this);
        player.removeCard(this);
    }
    @Override
    public void playCard(Player player) {
        System.out.println(ConsoleColors.YELLOW + "--- Prison outbreak has happened. ---" + ConsoleColors.RESET);

        player.removeActiveCard(this);
        cardDeck.trash.add(this);
    }
}

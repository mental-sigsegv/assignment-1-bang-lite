package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import java.util.ArrayList;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;

public abstract class Card {
    private final String name;
    protected final CardDeck cardDeck;

    public Card(String name, CardDeck cardDeck) {
        this.name = name;
        this.cardDeck = cardDeck;
    }
    public String getName() {
        return this.name;
    }
    public abstract boolean canPlay();
    public void playCard(Player player, ArrayList<Player> players) {
        System.out.println(ConsoleColors.YELLOW + "--- " + player.getName() + " has chosen card " + this.name + " to play. ---" + ConsoleColors.RESET);
    }

    public void playCard(Player player) {
        System.out.println(ConsoleColors.YELLOW + "--- " + player.getName() + " has chosen card " + this.name + " to play. ---" + ConsoleColors.RESET);
    }
}

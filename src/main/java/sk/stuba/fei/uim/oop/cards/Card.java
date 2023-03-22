package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.CardDeck;
import java.util.ArrayList;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;

public abstract class Card {
    private final String name;
    protected final CardDeck cardDeck;
    private final ConsoleColors consoleColors;

    public Card(String name, CardDeck cardDeck) {
        this.name = name;
        this.cardDeck = cardDeck;
        consoleColors = new ConsoleColors();
    }
    public String getName() {
        return this.name;
    }
    public abstract boolean canPlay();
    public void playCard(Player player, ArrayList<Player> players) {
        System.out.println(consoleColors.YELLOW + "--- " + player.getName() + " has chosen card " + this.name + " to play. ---" + consoleColors.RESET);
    }
}

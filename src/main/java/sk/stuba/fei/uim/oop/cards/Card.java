package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.CardDeck;
import java.util.ArrayList;

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
        System.out.println("--- " + player.getName() + " choose " + this.name + " card to play. ---");
    }
}

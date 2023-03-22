package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";
    public Indians(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);
        System.out.println("Indians are coming...");
        for (Player p : players) {
            if (p == player) {
                continue;
            }
            if (player.hasCardBang()) {
                System.out.println("->" + player.getName() + " shot indians with bang and didn't lose health.");
                // TODO remove bang card
            } else {
                System.out.println("->" + player.getName() + " was hit by a indian spear and lost 1 hp.");
                player.removeHealth();
            }
        }
        // TODO remove indians card
    }
}

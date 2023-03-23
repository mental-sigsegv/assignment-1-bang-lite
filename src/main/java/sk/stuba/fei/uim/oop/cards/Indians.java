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
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);
        System.out.println("Indians are coming...");
        for (Player player2 : players) {
            if (player2 == player || !player2.isAlive()) {
                continue;
            }

            if (player2.hasCardBang()) {
                System.out.println("->" + player2.getName() + " shot indians with card bang and didn't lose health.");
                for (Card card : player2.getCards()) {
                    if (card instanceof Bang) {
                        player2.getCards().remove(card);
                        cardDeck.trash.add(card);
                        break;
                    }
                }
            } else {
                System.out.println("->" + player2.getName() + " was hit by an indian spear and lost 1 hp.");
                player2.removeHealth();
            }
        }
        player.getCards().remove(this);
        cardDeck.trash.add(this);
    }
}

package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.Objects;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    public Bang(CardDeck cardDeck) {
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

        if (useBarrel(target)) {
            System.out.println("--- " + target.getName() + " dodged a bullet with card barrel. ---");
        } else if (useMissed(target)) {
            System.out.println("--- " + target.getName() + " dodged a bullet with card missed. ---");
        } else {
            System.out.println("--- " + target.getName() + " was shot and lost 1hp. ---");
            target.removeHealth();
        }

        player.removeCard(this);
        cardDeck.trash.add(this);
    }

    private boolean useMissed(Player player) {
        if (player.hasCardMissed()) {
            for (Card card : player.getCards()) {
                if (Objects.equals(card.getName(), "Missed")) {
                    card.playCard(player);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean useBarrel(Player player) {
        if (player.hasCardBarrel()) {
            for (Card card : player.getActiveCards()) {
                if (Objects.equals(card.getName(), "Barrel")) {
                    return ((Barrel) card).checkChance(player);
                }
            }
        }
        return false;
    }
}

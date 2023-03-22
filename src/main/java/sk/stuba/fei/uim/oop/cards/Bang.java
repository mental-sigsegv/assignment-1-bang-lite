package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

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

        // TODO fix this
        if (target.hasCardMissed()) {
            for (Card card : target.getCards()) {
                if (Objects.equals(card.getName(), "Missed")) {
                    card.playCard(target);
                    System.out.println(target.getName() + " dodged a bullet with card missed.");
                    break;
                }
            }
        } else {
            System.out.println(target.getName() + " was shot and lost 1hp.");
            target.removeHealth();
        }

        player.removeCard(this);
        cardDeck.trash.add(this);
    }
}

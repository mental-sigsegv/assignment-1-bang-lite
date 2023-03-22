package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";
    public CatBalou(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        ArrayList<Player> targets = new ArrayList<>();
        for (Player target : players) {
            if (target != player) {
                targets.add(target);
            }
        }

        Player target = player.chooseTarget(targets);

        int typeOfDeck = -1;

        while (typeOfDeck < 0 || typeOfDeck > 1) {
            typeOfDeck = ZKlavesnice.readInt("0 Cards on hand\n1 Cards on table");
            if (typeOfDeck < 0 || typeOfDeck > 1) {
                System.out.println(" !!! Try again! !!!");
            }
        }

        cardDeck.trash.add(this);
        player.removeCard(this);

        int cardDeckSize;
        Card cardToRemove;
        if (typeOfDeck == 0) {
            cardDeckSize = target.getCards().size();
            if (cardDeckSize == 0) {
                return;
            } else {
                cardToRemove = target.getCards().get((int) (Math.random() * cardDeckSize-1));
                target.removeCard(cardToRemove);
            }
        } else {
            cardDeckSize = target.getActiveCards().size();
            if (cardDeckSize == 0) {
                return;
            } else {
                cardToRemove = target.getActiveCards().get((int) (Math.random() * cardDeckSize-1));
                target.removeActiveCard(cardToRemove);
            }
        }
        cardDeck.trash.add(cardToRemove);

        System.out.println("--- " + target.getName() + " lost card " + cardToRemove.getName() + " ---");
    }
}

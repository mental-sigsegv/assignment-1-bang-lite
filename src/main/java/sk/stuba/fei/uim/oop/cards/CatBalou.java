package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "CatBalou";
    public CatBalou(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }
    private ArrayList<Player> canTarget;

    @Override
    public boolean canPlay(Player player) {
        canTarget = new ArrayList<>();

        for (Player target : cardDeck.getPlayers()) {
            if (target != player && target.isAlive()) {
                if ((target.getActiveCards().size() + target.getCards().size()) > 0) {
                    canTarget.add(target);
                }
            }
        }

        return canTarget.size() > 0;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        Player target = player.chooseTarget(canTarget);

        int typeOfDeck = -1;
        int cardsSize = target.getCards().size();
        int activeCardsSize = target.getActiveCards().size();
        Card selectCard;

        if (cardsSize == 0) {
            // Player doesn't have cards on hand
            System.out.println("--- " + target.getName() + " doesn't have any card on hand. Active card will be removed. ---");
            selectCard = target.getActiveCards().get((int) (Math.random() * activeCardsSize));
            target.getActiveCards().remove(selectCard);
        } else if (activeCardsSize == 0) {
            // Player doesn't have any active cards (prison, barrel, dynamite)
            System.out.println("--- " + target.getName() + " doesn't have any active card. Card on hand will be removed. ---");
            selectCard = target.getCards().get((int) (Math.random() * cardsSize));
            target.getCards().remove(selectCard);
        } else {
            // Player has active cards and cards on hand
            while (typeOfDeck < 1 || typeOfDeck > 2) {
                typeOfDeck = ZKlavesnice.readInt("1. Cards on hand\n2. Cards on table");
                if (typeOfDeck < 1 || typeOfDeck > 2) {
                    System.out.println(" !!! Try again! !!!");
                }
            }

            if (typeOfDeck == 1) {
                selectCard = target.getCards().get((int) (Math.random() * cardsSize-1));
                target.getCards().remove(selectCard);
            } else {
                selectCard = target.getActiveCards().get((int) (Math.random() * activeCardsSize-1));
                target.getActiveCards().remove(selectCard);
            }
        }

        cardDeck.getTrash().add(this);
        player.removeCard(this);
        cardDeck.getTrash().add(selectCard);

        System.out.println("--- " + target.getName() + " lost card " + selectCard.getName() + " ---");
    }
}

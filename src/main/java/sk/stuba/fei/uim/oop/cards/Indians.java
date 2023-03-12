package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";
    private static final String CARD_COLOR = "Brown";

    public Indians() {
        super(CARD_NAME, CARD_COLOR, false);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        System.out.println("Indians are coming...");
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();
        cardsToThrowAway.add(this);

        for (Player player : others) {
            Card bang = new Bang();
            if (player.hasCard(bang)) {
                System.out.println("->" + player.getName() + " shot indians with bang.");
                int indexOfCard = player.getCardIndex(bang);
                Card removedCard = player.removeCard(indexOfCard);
                cardsToThrowAway.add(removedCard);
            } else {
                System.out.println("->" + player.getName() + " was hit by a indian spear and lost 1 hp.");
                player.removeHealth();
            }
        }
        return cardsToThrowAway;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {
        return null;
    }
}

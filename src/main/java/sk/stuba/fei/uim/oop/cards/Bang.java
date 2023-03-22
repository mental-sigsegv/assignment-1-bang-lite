package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    private static final String CARD_COLOR = "Brown";

    public Bang(Indians cardDeck) {
        super(CARD_NAME, CARD_COLOR, false);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();


        Player player = caller.choosePlayer(others);
        System.out.println(caller.getName() + " is trying to shoot " + player.getName());
        Card missed = new Missed(this);
        Card barrel = new Barrel(this);
        if (player.hasActiveCard(barrel)) {
            System.out.println(player.getName() + " is trying to dodge attack with barrel");
            int indexOfCard = player.getCardIndex(barrel);
            ArrayList<Card> playedCard = player.getActiveCard(indexOfCard).playCard(player);
            if (playedCard != null) {
                cardsToThrowAway.add(playedCard.get(0));  // TODO fix imo
            }
        }

        if (player.hasCard(missed)) {
            System.out.println(player.getName() + " dodged attack with a miss card.");
            int indexOfCard = player.getCardIndex(missed);
            Card removedCard = player.removeCard(indexOfCard);
            cardsToThrowAway.add(removedCard);
        } else {
            System.out.println(player.getName() + " lost 1hp");
            player.removeHealth();
        }
        cardsToThrowAway.add(this);
        return cardsToThrowAway;
    }

    @Override
    public ArrayList<Card> playCard(Player player) { return null; }

}

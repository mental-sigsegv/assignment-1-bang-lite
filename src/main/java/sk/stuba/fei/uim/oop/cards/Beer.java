package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends Card {
    private static final String CARD_NAME = "Beer";
    private static final String CARD_COLOR = "Brown";
    private static final boolean CAN_BE_PLAYED_ON_SELF = true;

    public Beer() {
        super(CARD_NAME, CARD_COLOR, CAN_BE_PLAYED_ON_SELF);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        return null;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {
        System.out.println(player.getName() + " is getting drunk...");
        System.out.println("->" + player.getName() + " has gained 1hp.");
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();
        cardsToThrowAway.add(this);

        System.out.println(CARD_NAME + " card was played and 1hp has been added to " + player.getName());
        player.addHealth();

        return cardsToThrowAway;
    }
}

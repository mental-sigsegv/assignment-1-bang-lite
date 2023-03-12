package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    private static final String CARD_COLOR = "Blue";
    private static final boolean CAN_BE_PLAYED_ON_SELF = false;

    public Dynamite() {
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

        return null;
    }
}

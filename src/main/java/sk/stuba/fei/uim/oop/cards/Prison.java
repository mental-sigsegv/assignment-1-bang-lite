package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private static final String CARD_COLOR = "Blue";

    public Prison(CardDeck cardDeck) {
        super(CARD_NAME, CARD_COLOR, false, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();
        cardsToThrowAway.add(this);

        Card prison = new Prison(this);

        int playerIndex = 0;
        for (Player player : others) {
            System.out.println((playerIndex+1) + ". " + player.getName());
        }



        return cardsToThrowAway;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {

        return null;
    }
}

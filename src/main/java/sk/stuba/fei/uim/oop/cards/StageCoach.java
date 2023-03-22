package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class StageCoach extends Card {
    private static final String CARD_NAME = "StageCoach";
    private static final String CARD_COLOR = "Brown";

    public StageCoach(CardDeck cardDeck) {
        super(CARD_NAME, CARD_COLOR, true, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return false;
    }

    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        return null;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {
        System.out.println(player.getName() + " is going to draw 2 extra cards.");
        ArrayList<Card> cardsToThrowAway = new ArrayList<>();
//        player.drawCards();
        cardsToThrowAway.add(this);
        return cardsToThrowAway;
    }
}

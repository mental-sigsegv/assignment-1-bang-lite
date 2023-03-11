package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class StageCoach extends Card {
    private static final String CARD_NAME = "StageCoach";
    private static final String CARD_COLOR = "Brown";

    public StageCoach() {
        super(CARD_NAME, CARD_COLOR);
    }

    @Override
    public void playCard(Player player) {

    }
}

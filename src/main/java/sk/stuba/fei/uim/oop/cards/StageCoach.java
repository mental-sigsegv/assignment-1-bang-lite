package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import java.util.ArrayList;

public class StageCoach extends Card {
    private static final String CARD_NAME = "StageCoach";
    public StageCoach(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay(Player player) {
        return (cardDeck.cards.size() + cardDeck.trash.size()) >= 2;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);
        cardDeck.drawCards(player, 2);

        System.out.println("-> " + player.getName() + " has drawn 2 extra cards");

        player.removeCard(this);
        cardDeck.trash.add(this);
    }
}

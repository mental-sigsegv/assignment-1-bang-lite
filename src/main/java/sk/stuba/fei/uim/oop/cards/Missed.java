package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends Card {
    private static final String CARD_NAME = "Missed";
    public Missed(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay(Player player) {
        return false;
    }

    @Override
    public void playCard(Player player) {
        player.getCards().remove(this);
        cardDeck.getTrash().add(this);
    }
}

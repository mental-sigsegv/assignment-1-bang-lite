package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.CardsDeck;
import sk.stuba.fei.uim.oop.player.Player;

public class Bang {
    private CardsDeck cardsDeck;
    public Bang() {
        this.cardsDeck = new CardsDeck();
        System.out.println(cardsDeck.getCards().size());
    }
}
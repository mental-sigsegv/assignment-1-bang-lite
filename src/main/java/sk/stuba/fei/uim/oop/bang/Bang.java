package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.CardsDeck;
import sk.stuba.fei.uim.oop.player.Player;

public class Bang {
    private CardsDeck cardsDeck;
    public Bang() {
        this.cardsDeck = new CardsDeck();
        System.out.println(cardsDeck.getCards().size());
        printCardsDeck();

        Player p1 = new Player("l3mon");
        p1.addCard(cardsDeck.getCards().get(0));

        for (Card card : p1.getCards()) {
            System.out.println(card.getName());
        }
    }

    public void printCardsDeck() {
        for (Card card : cardsDeck.getCards()) {
//            System.out.println(card.getName());
            System.out.print(card.getName().charAt(0));
            System.out.print(card.getName().charAt(1));
            System.out.print(card.getName().charAt(2) + " ");
        }
        System.out.print("\n");
    }
}
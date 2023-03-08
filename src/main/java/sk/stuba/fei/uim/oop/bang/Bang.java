package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.CardsDeck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.CardsDeckTrash;

import java.util.ArrayList;

public class Bang {
    private CardsDeck cardsDeck;
    private CardsDeckTrash cardsDeckTrash;
    private ArrayList<Player> players;
    public Bang() {
        this.cardsDeck = new CardsDeck();
        this.cardsDeckTrash = new CardsDeckTrash();

        System.out.println(cardsDeck.getCards().size());
        printCardsDeck();

        players = new ArrayList<Player>();
        players.add(new Player("l3mon"));
        players.add(new Player("mental-sigsegv"));
        dealInitCards(players);

        for (Player player : players) {
            player.printCards();
        }

        cardsDeckTrash.addCard(players.get(0).removeCard(0));
        printCardsDeckTrash();

        for (Player player : players) {
            player.printCards();
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


    public void printCardsDeckTrash() {
        for (Card card : cardsDeckTrash.getCards()) {
//            System.out.println(card.getName());
            System.out.print(card.getName().charAt(0));
            System.out.print(card.getName().charAt(1));
            System.out.print(card.getName().charAt(2) + " ");
        }
        System.out.print("\n");
    }
    public void dealInitCards(ArrayList<Player> players) {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                player.addCard(cardsDeck.getCard(0));
            }
        }
    }
}
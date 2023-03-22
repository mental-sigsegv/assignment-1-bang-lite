package sk.stuba.fei.uim.oop.cards;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;

public class CardDeck {
    protected final ArrayList<Card> cards;
    protected final ArrayList<Card> trash;

    public CardDeck(ArrayList <Player> players) {
        cards = new ArrayList<>();
        trash = new ArrayList<>();

        setCards();
        shuffle();
    }

    private void setCards() {
        cards.add(new Dynamite(this));

        for (int i=0; i<2; i++) {
            cards.add(new Barrel(this));
            cards.add(new Indians(this));
        }
        for (int i=0; i<3; i++) {
            cards.add(new Prison(this));
        }
        for (int i=0; i<4; i++) {
            cards.add(new StageCoach(this));
        }
        for (int i=0; i<6; i++) {
            cards.add(new CatBalou(this));
        }
        for (int i=0; i<8; i++) {
            cards.add(new Beer(this));
        }
        for (int i=0; i<15; i++) {
            cards.add(new Missed(this));
        }
        for (int i=0; i<30; i++) {
            cards.add(new Bang(this));
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public ArrayList<Card> getTrash() { return trash;}

    public Card getCard(int index) {
        Card cardToReturn = this.getCards().get(index);
        this.removeCard(index);
        return cardToReturn;
    }

    public void addCard(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
    public void addCardTrash(Card card) {
        this.trash.add(card);
    }
    public void removeCardTrash(Card card) {
        this.trash.add(card);
    }
    public void removeCardsTrash() {
        trash.clear();
    }

    public void removeCard(int index) {
        this.cards.remove(index);
    }

    public void removeCards() {
        this.cards.clear();
    }

    public int getSize() {
        return cards.size();
    }

    public void printCards(String name) {
        System.out.println(name + " deck");
        for (Card card : cards) {
            System.out.println(card.getName());
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void fillCardDeckFromTrashCardDeck() {
        addCard(trash);
        removeCardsTrash();
        shuffle();
    }

    public void drawCards(Player player, int numOfCards) {
        System.out.println("-> " + player.getName() + " is drawing " + numOfCards + " cards...");
        if (numOfCards > cards.size()) {
            fillCardDeckFromTrashCardDeck();
        }

        if (numOfCards > cards.size()) {
            numOfCards = cards.size();
            System.out.println(ConsoleColors.RED + "--- There are not enough cards in deck. You will draw " + numOfCards + " cards. ---" + ConsoleColors.RESET);
        }

        System.out.print("[ " + ConsoleColors.CYAN);
        for (int i = 0; i < numOfCards; i++) {
            Card card = cards.remove(0);
            System.out.print(card.getName() + " ");
            player.addCard(card);
        }
        System.out.print(ConsoleColors.RESET + "]\n\n");
    }
}

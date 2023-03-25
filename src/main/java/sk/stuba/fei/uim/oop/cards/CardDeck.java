package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;
import java.util.Collections;

import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class CardDeck {
    private final ArrayList<Card> cards;
    private final ArrayList<Card> trash;
    private final ArrayList<Player> players;

    public CardDeck(ArrayList<Player> players) {
        cards = new ArrayList<>();
        trash = new ArrayList<>();

        this.players = players;

        setTrashReference();
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
    private void setTrashReference() {
        for (Player player : players) {
            player.setTrashReference(trash);
        }
    }
    public ArrayList<Card> getTrash() { return trash;}
    public ArrayList<Card> getCards() {
        return cards;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
    public void fillCardDeckFromTrashCardDeck() {
        System.out.println("--- Card deck has been refilled and shuffled. ---");
        cards.addAll(trash);
        trash.clear();
        shuffle();
    }
    public void drawCards(Player player, int cardsToDraw) {
        System.out.println("-> " + player.getName() + " is drawing " + cardsToDraw + " cards...");
        if (cardsToDraw > cards.size()) {
            fillCardDeckFromTrashCardDeck();
        }
        if (cardsToDraw > cards.size()) {
            cardsToDraw = cards.size();
            System.out.println(ConsoleColors.RED + "--- There are not enough cards in deck. You will draw " + cardsToDraw + " cards. ---" + ConsoleColors.RESET);
        }
        System.out.print("[ " + ConsoleColors.CYAN);
        for (int i = 0; i < cardsToDraw; i++) {
            Card card = cards.remove(0);
            System.out.print(card.getName() + " ");
            player.addCard(card);
        }
        System.out.print(ConsoleColors.RESET + "]\n\n");
    }

    public void removeExcessCards(Player currentPlayer) {
        int numberOfCards = currentPlayer.getCards().size();
        int maxNumberOfCards = currentPlayer.getHealth();
        int indexOfCard;
        Card thrownCard;

        if (numberOfCards > maxNumberOfCards) {
            System.out.println("\n" + currentPlayer.getName() + " has to throw away " + (numberOfCards-maxNumberOfCards) + " cards. \nChose:");

            while (numberOfCards > maxNumberOfCards) {
                currentPlayer.printCards();
                indexOfCard = ZKlavesnice.readInt(ConsoleColors.CYAN + "--- Enter number of card that will be removed. ---" + ConsoleColors.RESET);
                if (indexOfCard < 1 || indexOfCard > currentPlayer.getCards().size()) {
                    System.out.println(ConsoleColors.RED + "!!! Try Again! !!!" + ConsoleColors.RESET);
                    continue;
                }
                thrownCard = currentPlayer.removeCard(indexOfCard - 1);
                trash.add(thrownCard);
                numberOfCards = currentPlayer.getCards().size();
            }
        }
    }
}

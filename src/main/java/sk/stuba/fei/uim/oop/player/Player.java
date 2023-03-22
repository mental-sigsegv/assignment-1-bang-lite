package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Bang;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.CardDeck;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final String name;
    private int health;
    private final ArrayList<Card> cards;
    private final ArrayList<Card> activeCards;

    public Player(String name) {
        this.name = name;
        this.health = 4;
        this.cards = new ArrayList<>();
        this.activeCards = new ArrayList<>();
    }

    public String getName() {

        return this.name;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void removeHealth() {
        this.health--;
    }
    public void removeHealth(int damage) {
        this.health -= damage;
    }

    public void addHealth() {
        this.health++;
    }
    public int getHealth() {
        return this.health;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public void printCards() {
        int count = 1;
        System.out.println(this.name + "'s cards (" + (activeCards.size() + cards.size()) + ") are:");
        for (Card card : cards) {
            System.out.println(count + ". " + card.getName());
            count++;
        }
        System.out.println("\n");
        if (this.activeCards.size() > 0) {
            count = 1;
            System.out.println(this.name + "'s active cards are: ");
            for (Card card : activeCards) {
                System.out.println(count + ". " + card.getName());
                count++;
            }
        }
    }

    public ArrayList<Card> getActiveCards() {
        return this.cards;
    }
    public Card getActiveCard(int indexOfCard) {
        return this.activeCards.get(indexOfCard);
    }

    public void removeCards() {
        this.cards.clear();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card removeCard(int index) {
        Card cardToRemove = this.cards.get(index);
        this.cards.remove(index);
        return cardToRemove;
    }
    public Card removeActiveCard(int index) {
        Card cardToRemove = this.activeCards.get(index);
        this.activeCards.remove(index);
        return cardToRemove;
    }
    public void removeCard(Card card) {
        this.cards.remove(card);
    }
    public Card removeActiveCard(Card card) {
        for (Card c : activeCards) {
            if (Objects.equals(c.getName(), card.getName())) {
                activeCards.remove(c);
                return c;
            }
        }
        return null;
    }

    public boolean hasCard(Card card) {
        for (Card c : cards) {
            if (Objects.equals(c.getName(), card.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasActiveCard(Card card) {
        for (Card c : activeCards) {
            if (Objects.equals(c.getName(), card.getName())) {
                return true;
            }
        }
        return false;
    }

    public int getCardIndex(Card card) {
        int counter = 0;
        for (Card c : cards) {
            if (Objects.equals(c.getName(), card.getName())) {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public ArrayList<Card> getPlayableCards() {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.canPlay()) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public void printPlayableCards() {
        System.out.println("Cards that can be played by " + this.getName() + ":");
        int count = 1;
        for (Card playableCard : this.getPlayableCards()) {
            System.out.println(count + ". " + playableCard.getName());
            count++;
        }
    }

    public void checkDynamite() {

    }

    public void checkPrison() {

    }

    public Player choosePlayer(ArrayList<Player> players) {
        ArrayList<Player> otherPlayers;
        int numOfPlayers = players.size();
        int counter = 1;
        int playerIndex = -1;
        for (Player player : players) {
            System.out.println(counter + ". " + player.getName() + " " + player.getHealth() + "hp.");
            counter++;
        }

        // Chose
        while (playerIndex < 1 || playerIndex > (numOfPlayers )) {
            playerIndex = ZKlavesnice.readInt("*** Enter number");
            if (playerIndex < 1 || playerIndex > (numOfPlayers)) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        System.out.println(players.get(playerIndex-1).getName());
        return players.get(playerIndex-1);
    }

    public boolean hasCardBang() {
        for (Card card : cards) {
            if (card.getName() == "Bang") {
                return true;
            }
        }
        return false;
    }

}

package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private String name;
    private int health;
    private ArrayList<Card> cards;
    private ArrayList<Card> activeCards;

    public Player(String name) {
        this.name = name;
        this.health = 4;
        this.cards = new ArrayList<Card>();
        this.activeCards = new ArrayList<Card>();
    }

    public String getName() {

        return this.name;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public boolean hasCardsOverLimit() {
        return this.cards.size() > this.health;
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
        System.out.println(this.name + "'s cards are:");
        for (Card card : cards) {
            System.out.println(count + ". " + card.getName());
            count++;
        }
        System.out.println("\n");
    }

    public ArrayList<Card> getActiveCards() {
        return this.cards;
    }

    public void removeCards() {
        this.cards.clear();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card removeCard(int index) {
        Card cardToRemove;
        cardToRemove = this.cards.get(index);
        this.cards.remove(index);
        return cardToRemove;
    }

    public boolean hasCard(Card card) {
        for (Card c : cards) {
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
}

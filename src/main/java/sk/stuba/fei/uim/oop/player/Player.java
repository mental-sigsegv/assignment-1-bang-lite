package sk.stuba.fei.uim.oop.player;


import sk.stuba.fei.uim.oop.cards.Card;
import java.util.ArrayList;
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

    public void addHealth() {
        this.health++;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> getActiveCards() {
        return this.cards;
    }

    public void removeCards() {
        this.cards.clear();
    }
}

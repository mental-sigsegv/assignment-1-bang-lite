package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.Dynamite;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final String name;
    private int health;
    protected final ArrayList<Card> cards;
    protected final ArrayList<Card> activeCards;

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
        int count;

        if (cards.size() > 0) {
            count = 1;
            System.out.println(ConsoleColors.GREEN_UNDERLINED + name + "'s cards (" + cards.size() + ") on hand are: " + ConsoleColors.RESET);
            for (Card card : cards) {
                System.out.println(count + ". " + ConsoleColors.GREEN + card.getName() + ConsoleColors.RESET);
                count++;
            }
        }

        if (activeCards.size() > 0) {
            count = 1;
            System.out.println(ConsoleColors.BLUE_UNDERLINED + name + "'s active cards (" + activeCards.size() + ") are: " + ConsoleColors.RESET);
            for (Card card : activeCards) {
                System.out.println(count + ". " + ConsoleColors.BLUE + card.getName() + ConsoleColors.RESET);
                count++;
            }
        }
    }

    public ArrayList<Card> getActiveCards() {
        return this.activeCards;
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
        int count = 0;
        for (Card c : cards) {
            if (Objects.equals(c.getName(), card.getName())) {
                return count;
            }
            count++;
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
        System.out.println(ConsoleColors.YELLOW_UNDERLINED + getName() + "'s cards that can be played: " + ConsoleColors.RESET);
        int count = 1;
        for (Card playableCard : this.getPlayableCards()) {
            System.out.println(count + ConsoleColors.YELLOW + ". " + playableCard.getName() + ConsoleColors.RESET);
            count++;
        }
    }

    public boolean checkDynamite(ArrayList<Player> players) {
        for (Card card : activeCards) {
            if (Objects.equals(card.getName(), "Dynamite")) {
                return ((Dynamite) card).checkChance(this, players);
            }
        }
        return true;
    }

    public boolean checkPrison() {
        for (Card card : activeCards) {
            if (Objects.equals(card.getName(), "Prison")) {
                card.playCard(this);
                if (Math.random() < 1/4.0) {
                    System.out.println(ConsoleColors.GREEN + getName() + " escaped prison." + ConsoleColors.RESET);
                    return true;
                } else {
                    System.out.println(ConsoleColors.RED + getName() + " didn't escape prison. He must wait 1 round." + ConsoleColors.RESET);
                    return false;
                }
            }
        }
        return true;
    }



    public boolean hasCardBang() {
        for (Card card : cards) {
            if (Objects.equals(card.getName(), "Bang")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCardBarrel() {
        for (Card card : activeCards) {
            if (Objects.equals(card.getName(), "Barrel")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCardMissed() {
        for (Card card : cards) {
            if (card.getName() == "Missed") {
                return true;
            }
        }
        return false;
    }

    public Player chooseTarget(ArrayList<Player> players) {
        ArrayList<Player> targets = new ArrayList<>();

        int count = 1;
        int index = -1;

        for (Player player : players) {
            if (player != this && player.isAlive()) {
                targets.add(player);
                System.out.println(count + ". " + player.getName() + " " + player.getHealth() + "hp.");
                count++;
            }

        }

        while (index < 1 || index > targets.size()) {
            index = ZKlavesnice.readInt("*** Enter number");
            if (index < 1 || index > targets.size()) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        System.out.println(targets.get(index-1).getName());
        return targets.get(index-1);
    }
}

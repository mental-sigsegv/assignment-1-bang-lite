package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import java.util.ArrayList;

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
        checkDead();
    }
    public void removeHealth(int damage) {
        this.health -= damage;
        checkDead();
    }
    public void addHealth() {
        this.health++;
    }
    public int getHealth() {
        return this.health;
    }
    public void checkDead() {
        if (!isAlive()) {
            System.out.println(ConsoleColors.RED + "--- " + getName() + " IS DEAD! ---" + ConsoleColors.RESET);
        }
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
        return activeCards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card removeCard(int index) {
        Card cardToRemove = cards.get(index);
        cards.remove(index);
        return cardToRemove;
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
    public void removeActiveCard(Card card) {
        activeCards.remove(card);
    }

    public ArrayList<Card> getPlayableCards() {
        ArrayList<Card> playableCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.canPlay(this)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    public void printPlayableCards() {
        System.out.println(ConsoleColors.YELLOW_UNDERLINED + getName() + "'s cards that can be played: " + ConsoleColors.RESET);
        int count = 1;
        for (Card playableCard : getPlayableCards()) {
            System.out.println(count + ConsoleColors.YELLOW + ". " + playableCard.getName() + ConsoleColors.RESET);
            count++;
        }
    }

    public void checkDynamite(ArrayList<Player> players) {
        for (Card card : activeCards) {
            if (card instanceof Dynamite) {
                ((Dynamite) card).checkChance(this, players);
                return;
            }
        }
    }
    public boolean checkPrison() {
        for (Card card : activeCards) {
            if (card instanceof Prison) {
                card.playCard(this);
                if (Math.random() < 1/4.0) {
                    System.out.println(ConsoleColors.GREEN + "-> " + getName() + " escaped prison." + ConsoleColors.RESET);
                    return true;
                } else {
                    System.out.println(ConsoleColors.RED + "-> " + getName() + " didn't escape prison. He must wait 1 round." + ConsoleColors.RESET);
                    return false;
                }
            }
        }
        return true;
    }
    public boolean hasCardBang() {
        for (Card card : cards) {
            if (card instanceof Bang) {
                return true;
            }
        }
        return false;
    }
    public boolean hasCardBarrel() {
        for (Card card : activeCards) {
            if (card instanceof Barrel) {
                return true;
            }
        }
        return false;
    }
    public boolean hasCardMissed() {
        for (Card card : cards) {
            if (card instanceof Missed) {
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
            StringBuilder activeCards = new StringBuilder(" ");
            for (Card card : player.getActiveCards()) {
                activeCards.append(card.getName()).append(" ");
            }

            if (player != this && player.isAlive()) {
                targets.add(player);
                System.out.print(count + ". " + player.getName() + " " + player.getHealth() + " hp");
                if (player.getActiveCards().size() > 0) {
                    System.out.print(" [" + ConsoleColors.CYAN + activeCards + ConsoleColors.RESET + "]\n");
                } else {
                    System.out.print("\n");
                }
                count++;
            }

        }

        while (index < 1 || index > targets.size()) {
            index = ZKlavesnice.readInt("--- Enter number of player ---");
            if (index < 1 || index > targets.size()) {
                System.out.println(ConsoleColors.RED + "!!! Try Again! !!!" + ConsoleColors.RESET);
            }
        }
        System.out.println(targets.get(index-1).getName());
        return targets.get(index-1);
    }
}

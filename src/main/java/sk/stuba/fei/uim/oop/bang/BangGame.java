package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

import static java.lang.Math.random;

public class BangGame {
    private CardDeck cardDeck;
    private CardDeck cardTrashDeck;
    private ArrayList<Player> players;
    private int playerCurrentIndex = 0;
    public BangGame() {
        System.out.println("Welcome to FEI Bang!");

        // Get number of players
        int numOfPlayers = 0;
        while (numOfPlayers < 2 || numOfPlayers > 4) {
            numOfPlayers = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (numOfPlayers < 2 || numOfPlayers > 4) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }

        // Set players name
        this.players = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++) {
            this.players.add(new Player(ZKlavesnice.readString("*** Enter name for PLAYER " + (i+1) + " : ***")));
        }


//        this.test();
        // Start game
        this.startGame();

    }
    private void startGame() {
        // Create card decks
        this.cardDeck = new CardDeck();
        this.cardTrashDeck = new CardDeck("empty");

        System.out.println("Game started...");

        while (getAlivePlayers() > 1) {
            Player currentPlayer = this.players.get(this.playerCurrentIndex);

            if (!currentPlayer.isAlive()) {
                // TODO remove cards
                players.remove(currentPlayer);
                System.out.println(currentPlayer.getName() + " is dead!");
                this.incCurrentPlayerIndex();
                continue;
            }

            // TODO make turn
            this.makeTurn(currentPlayer);

            this.incCurrentPlayerIndex();
        }

        System.out.println("Game finished!");
        System.out.println("Thw winner is " + getWinner().getName() + "! Congratulation!");
    }

    private int getAlivePlayers() {
        int numOfAlivePlayers = 0;
        for (Player player : this.players) {
            if (player.isAlive()) {
                numOfAlivePlayers++;
            }
        }
        return numOfAlivePlayers;
    }
    private void incCurrentPlayerIndex() {
        this.playerCurrentIndex++;
        this.playerCurrentIndex %= getAlivePlayers();
    }

    private Player getWinner() {
        for (Player player : players) {
            if (player.isAlive()) {
                return player;
            }
        }
        return null;
    }

    private void makeTurn(Player currentPlayer) {
        System.out.println(currentPlayer.getName() + "'s turn...");
        System.out.println(currentPlayer.getName() + " has " + currentPlayer.getHealth() + " hp.");

        // TODO check dynamite
        checkDynamite(currentPlayer);

        // TODO check prison
        checkPrison(currentPlayer);


        drawCards(currentPlayer, 2);
        currentPlayer.printCards();

        // Fix max number of cards to player's current hp
        int numberOfCards = currentPlayer.getCards().size();
        int maxNumberOfCards = currentPlayer.getHealth();
        int indexOfCard = 0;
        if (numberOfCards > maxNumberOfCards) {
            System.out.println("\n" + currentPlayer.getName() + " has to throw away " + (numberOfCards-maxNumberOfCards) + " cards. \nChose:");

            while (numberOfCards > maxNumberOfCards) {
                currentPlayer.printCards();
                indexOfCard = ZKlavesnice.readInt("Enter number of card that will be removed.");
                if (indexOfCard < 1 || indexOfCard > currentPlayer.getCards().size()) {
                    System.out.println(" !!! You enter wrong number of card. Try Again! !!!");
                    continue;
                }
                currentPlayer.removeCard(indexOfCard-1);
                numberOfCards = currentPlayer.getCards().size();
            }
        }
    }

    private void checkDynamite(Player currentPlayer) {
        if (currentPlayer.hasCard(new Dynamite())) {
            if (!this.dynamiteChance()) {
                currentPlayer.removeHealth(3);
                // TODO remove dynamite from deck
            }
            // TODO move dynamite to previous player
        }
    }

    private void checkPrison(Player currentPlayer) {
        if (currentPlayer.hasCard(new Prison())) {
            if (!this.prisonChance()) {
                return;
            }

            // TODO remove card and add to trash deck
        }
    }

    private void drawCards(Player currentPlayer, int numOfCards) {
        System.out.println(currentPlayer.getName() + "'s drawing " + numOfCards + " cards.");
        if (numOfCards < this.cardDeck.getSize()) {
            this.cardDeck.addCard(cardTrashDeck.getCards());
            this.cardTrashDeck.removeCards();
        }

        for (int i = 0; i < numOfCards; i++) {
            currentPlayer.addCard(this.cardDeck.getCard(0));
        }
    }

    private boolean prisonChance() {
        double randomChance = random();
        return randomChance <= 0.25;
    }

    private boolean dynamiteChance() {
        double randomChance = random();
        return randomChance <= 1/8.0;
    }
    private void test() {
        Player p1 = this.players.get(0);
        Player p2 = this.players.get(1);

        p1.addCard(new Bang());
        p1.addCard(new Bang());

        p2.addCard(new Missed());


        p1.getCards().get(0).playCard(p2);
        p1.printCards();
        p2.printCards();

        p1.getCards().get(0).playCard(p2);
        p1.printCards();
        System.out.println(p2.getHealth());

    }
}
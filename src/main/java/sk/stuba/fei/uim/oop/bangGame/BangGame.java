package sk.stuba.fei.uim.oop.bangGame;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.player.Player;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.random;

public class BangGame {

    private CardDeck cardDeck;
    private CardDeck cardTrashDeck;
    private final ArrayList<Player> players;
    private int playerCurrentIndex = 0;
    public BangGame() {
        System.out.println("Welcome to FEI Bang!");

        // Get number of players
        int numOfPlayers = 0;
        int MIN_PLAYERS = 2;
        int MAX_PLAYERS = 4;
        while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
            numOfPlayers = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }

        // Set players name
        this.players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            this.players.add(new Player(ZKlavesnice.readString("*** Enter name for PLAYER " + (i+1) + " : ***")));
        }


//        this.test();
        // Start game
        this.startGame();

    }
    private void startGame() {
        // Create card decks
        this.cardDeck = new CardDeck(1);
        this.cardTrashDeck = new CardDeck("empty");

        for (Player player : players) {
            this.drawCards(player, 4);
        }

        System.out.println("--- Game started... ---");

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

        // Select card to play

        System.out.println("Game finished!");
        System.out.println("Thw winner is " + Objects.requireNonNull(getWinner()).getName() + "! Congratulation!");
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

        checkDynamite(currentPlayer);

        checkPrison(currentPlayer);

        drawCards(currentPlayer, 2);
        currentPlayer.printCards();

        playCards(currentPlayer);

        throwAwayExcessiveCards(currentPlayer);
    }

    private void playCards(Player currentPlayer) {
        int cardIndex;
        int maxCardIndex = currentPlayer.getPlayableCards().size();
        while (maxCardIndex > 0) {
            currentPlayer.printPlayableCards();
            cardIndex = ZKlavesnice.readInt("Enter number of card, that you would like to play. [0 to stop selection]");
            if (cardIndex == 0) {
                return;
            } else if (cardIndex < 0 || cardIndex > maxCardIndex) {
                System.out.println("You have entered wrong number.");
                continue;
            }
            Card cardToPlay = currentPlayer.getPlayableCards().get(cardIndex - 1);

            ArrayList<Card> cardsToRemove;
            if (!cardToPlay.getUseOnSelf()) {
                Player otherPlayer = chooseOtherPlayer(currentPlayer);
                cardsToRemove = cardToPlay.playCard(otherPlayer);
            } else {
                cardsToRemove = cardToPlay.playCard(currentPlayer);
            }

            for (Card cardToRemove : cardsToRemove) {
                currentPlayer.removeCard(cardToRemove);
                this.cardTrashDeck.addCard(cardToRemove);

            }

            maxCardIndex = currentPlayer.getPlayableCards().size();
        }
    }

    private Player chooseOtherPlayer(Player currentPlayer) {
        ArrayList<Player> otherPlayers = players;
        otherPlayers.remove(currentPlayer);
        int count = 1;
        int otherPlayerIndex = -1;
        for (Player player : otherPlayers) {
            System.out.println("Player " + count + " " + player.getName());
        }

        while (otherPlayerIndex < 0 || otherPlayerIndex > otherPlayers.size()) {
            otherPlayerIndex = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (otherPlayerIndex < 0 || otherPlayerIndex > otherPlayers.size()) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        return otherPlayers.get(otherPlayerIndex - 1);
    }

    private void throwAwayExcessiveCards(Player currentPlayer) {
        int numberOfCards = currentPlayer.getCards().size();
        int maxNumberOfCards = currentPlayer.getHealth();
        int indexOfCard;
        Card thrownCard;

        if (numberOfCards > maxNumberOfCards) {
            System.out.println("\n" + currentPlayer.getName() + " has to throw away " + (numberOfCards-maxNumberOfCards) + " cards. \nChose:");

            while (numberOfCards > maxNumberOfCards) {
                currentPlayer.printCards();
                indexOfCard = ZKlavesnice.readInt("Enter number of card that will be removed.");
                if (indexOfCard < 1 || indexOfCard > currentPlayer.getCards().size()) {
                    System.out.println(" !!! You enter wrong number of card. Try Again! !!!");
                    continue;
                }
                thrownCard = currentPlayer.removeCard(indexOfCard - 1);
                this.cardTrashDeck.addCard(thrownCard);
                numberOfCards = currentPlayer.getCards().size();
            }
        }
    }

    private void checkDynamite(Player currentPlayer) {
        if (currentPlayer.hasActiveCard(new Dynamite())) {
            if (!this.dynamiteChance()) {
                currentPlayer.removeHealth(3);
                currentPlayer.removeActiveCard(new Dynamite());
            }
            // TODO move dynamite to previous player
        }
    }

    private void checkPrison(Player currentPlayer) {
        if (currentPlayer.hasActiveCard(new Prison())) {
            if (!this.prisonChance()) {
                new Prison();
                // Do something
            }

            // TODO remove card and add to trash deck
        }
    }

    private void drawCards(Player currentPlayer, int numOfCards) {
        System.out.println(currentPlayer.getName() + "'s drawing " + numOfCards + " cards.");
        cardDeck.printCards("Card");
        cardTrashDeck.printCards("Trash");

        if (numOfCards > this.cardDeck.getSize()) {
            fillCardDeckFromTrashCardDeck();
        }

        if (numOfCards > this.cardDeck.getSize()) {
            numOfCards = this.cardDeck.getSize();
            System.out.println("There are not enough cards in deck. You will draw " + numOfCards + " cards.");
        }


        for (int i = 0; i < numOfCards; i++) {
            currentPlayer.addCard(this.cardDeck.getCard(0));
        }
    }

    private void fillCardDeckFromTrashCardDeck() {
        this.cardDeck.addCard(cardTrashDeck.getCards());
        this.cardTrashDeck.removeCards();
    }

    private boolean prisonChance() {
        double randomChance = random();
        return randomChance <= 0.25;
    }

    private boolean dynamiteChance() {
        double randomChance = random();
        return randomChance <= 1 / 8.0;
    }
}
package sk.stuba.fei.uim.oop.bangGame;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.random;

public class BangGame {

    private CardDeck cardDeck;
    private final ArrayList<Player> players;
    private int playerCurrentIndex = 0;
    public BangGame() {
        System.out.println("--- Welcome to " + ConsoleColors.BLUE + "FEI" + ConsoleColors.RESET + " Bang! ---");

        // Get number of players
        int numOfPlayers = 0;
        int MIN_PLAYERS = 2;
        int MAX_PLAYERS = 4;

        while (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
            numOfPlayers = ZKlavesnice.readInt("--- Enter number of players (2-4): ---");
            if (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
                System.out.println(ConsoleColors.RED + "!!! Try again! !!!" + ConsoleColors.RESET);
            }
        }

        // Set players name
        this.players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            this.players.add(new Player(ZKlavesnice.readString("--- Enter name for " + ConsoleColors.YELLOW + "PLAYER " + (i+1) + ConsoleColors.RESET + ": ---")));
        }

        // Start game
        this.startGame();

    }
    private void startGame() {
        cardDeck = new CardDeck(players);
        cardDeck.shuffle();

        for (Player player : players) {
            cardDeck.drawCards(player, 4);
        }

        System.out.println(ConsoleColors.BLUE + "\n--- GAME STARTED ---\n" + ConsoleColors.RESET);

        while (getAlivePlayers() > 1) {
            Player currentPlayer = players.get(playerCurrentIndex);

            if (!currentPlayer.isAlive()) {
                incCurrentPlayerIndex();
                continue;
            }

            makeTurn(currentPlayer);
            incCurrentPlayerIndex();
        }

        System.out.println(ConsoleColors.BLUE + "\n--- Game finished! ---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "\n--- The winner is " + Objects.requireNonNull(getWinner()).getName() + "! Congratulation! ---" + ConsoleColors.RESET);
    }
    private void makeTurn(Player currentPlayer) {
        System.out.println(ConsoleColors.RED + "\n--- " + currentPlayer.getName() + "'S TURN ---" + ConsoleColors.RESET);
        System.out.println("-> " + currentPlayer.getName() + " has " + ConsoleColors.CYAN + currentPlayer.getHealth() + " hp." + ConsoleColors.RESET);

        // Check dynamite
        if (!currentPlayer.checkDynamite(players)) {
            return;
        }

        // Check prison
        if (!currentPlayer.checkPrison()) {
            return;
        }

        // Draw cards
        cardDeck.drawCards(currentPlayer, 2);

        // Print player cards
        currentPlayer.printCards();

        // Play player cards
        playCards(currentPlayer);

        // Throw away cards
        throwAwayExcessiveCards(currentPlayer);  // TODO check spelling
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
            cardToPlay.playCard(currentPlayer, players);
            maxCardIndex = currentPlayer.getPlayableCards().size();
        }
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
        this.playerCurrentIndex %= players.size();
    }

    private Player getWinner() {
        for (Player player : players) {
            if (player.isAlive()) {
                return player;
            }
        }
        return null;
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
                indexOfCard = ZKlavesnice.readInt("--- Enter number of card that will be removed. ---");
                if (indexOfCard < 1 || indexOfCard > currentPlayer.getCards().size()) {
                    System.out.println(ConsoleColors.RED + "!!! Try Again! !!!" + ConsoleColors.RESET);
                    continue;
                }
                thrownCard = currentPlayer.removeCard(indexOfCard - 1);
                cardDeck.getTrash().add(thrownCard);
                numberOfCards = currentPlayer.getCards().size();
            }
        }
    }
}
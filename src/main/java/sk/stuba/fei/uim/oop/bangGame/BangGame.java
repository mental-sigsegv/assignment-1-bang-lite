package sk.stuba.fei.uim.oop.bangGame;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Objects;

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
            numOfPlayers = ZKlavesnice.readInt(ConsoleColors.CYAN + "--- Enter number of players (2-4): ---" + ConsoleColors.RESET);
            if (numOfPlayers < MIN_PLAYERS || numOfPlayers > MAX_PLAYERS) {
                System.out.println(ConsoleColors.RED + "!!! Try again! !!!" + ConsoleColors.RESET);
            }
        }

        // Set players name
        players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(ZKlavesnice.readString("--- Enter name for " + ConsoleColors.YELLOW + "PLAYER " + (i+1) + ConsoleColors.RESET + ": ---")));
        }

        // Start game
        startGame();

    }
    private void startGame() {
        cardDeck = new CardDeck(players);

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

        // Check dynamite; return if player survived dynamite
        if (!currentPlayer.checkDynamite(players)) {
            return;
        }

        // Check prison
        if (!currentPlayer.checkPrison()) {
            return;
        }

        // Draw cards
        cardDeck.drawCards(currentPlayer, 2);

        // Play player cards
        playCards(currentPlayer);

        if (getAlivePlayers() == 1) {
            return;
        }

        // Remove excess cards
        cardDeck.removeExcessCards(currentPlayer);
    }

    private void playCards(Player currentPlayer) {
        // Print player cards
        currentPlayer.printCards();

        int cardIndex;
        int maxCardIndex = currentPlayer.getPlayableCards().size();
        while (maxCardIndex > 0 && getAlivePlayers() > 1) {
            currentPlayer.printPlayableCards();
            cardIndex = ZKlavesnice.readInt(ConsoleColors.CYAN + "--- Enter number of card, that you would like to play. [0 to stop] ---" + ConsoleColors.RESET);
            if (cardIndex == 0) {
                return;
            } else if (cardIndex < 0 || cardIndex > maxCardIndex) {
                System.out.println(ConsoleColors.RED + "!!! Try again! !!!" + ConsoleColors.RESET);
                continue;
            }
            Card cardToPlay = currentPlayer.getPlayableCards().get(cardIndex - 1);
            cardToPlay.playCard(currentPlayer, players);
            maxCardIndex = currentPlayer.getPlayableCards().size();
        }
    }

    private int getAlivePlayers() {
        int numOfAlivePlayers = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                numOfAlivePlayers++;
            }
        }
        return numOfAlivePlayers;
    }
    private void incCurrentPlayerIndex() {
        playerCurrentIndex++;
        playerCurrentIndex %= players.size();
    }

    private Player getWinner() {
        for (Player player : players) {
            if (player.isAlive()) {
                return player;
            }
        }
        return null;
    }

}
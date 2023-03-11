package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.CardsDeck;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.CardsDeckTrash;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang {
    private CardsDeck cardsDeck;
    private CardsDeckTrash cardsDeckTrash;
    private ArrayList<Player> players;
    private int playerCurrentIndex = 0;
    public Bang() {
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

        // Start game
        this.startGame();

    }
    private void startGame() {
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

            System.out.println(currentPlayer.getName() + " is on turn...");
            System.out.println(currentPlayer.getName() + " has " + currentPlayer.getHealth() + " hp.");
            // TODO make turn

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
}
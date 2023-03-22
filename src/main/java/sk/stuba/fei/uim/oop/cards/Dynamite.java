package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends Card {
    private static final String CARD_NAME = "Dynamite";
    public Dynamite(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        player.removeCard(this);
        player.getActiveCards().add(this);
    }
    public boolean checkChance(Player player, ArrayList<Player> players) {
        if (Math.random() < 1/8.0) {
            System.out.println(ConsoleColors.RED + "--- " + player.getName() + " has been bombed! ---" + ConsoleColors.RESET);
            player.removeHealth(3);
            player.getActiveCards().remove(this);
            cardDeck.trash.add(this);
            return false;
        }
        int index = players.indexOf(player)-1;

        while (true) {
            if (index < 0) {
                index = players.size()-1;
            }
            if (!players.get(index).isAlive()) {
                index--;
            } else {
                break;
            }

        }
        System.out.println("--- " + player.getName() + " didn't explode and dynamite was passed! ---");
        player.getActiveCards().remove(this);
        players.get(index).getActiveCards().add(this);
        return true;
    }
}

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
    public boolean canPlay(Player player) {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        player.removeCard(this);
        player.getActiveCards().add(this);
    }
    public void checkChance(Player player, ArrayList<Player> players) {
        if (Math.random() < 1/8.0) {
            System.out.println(ConsoleColors.RED + "--- " + player.getName() + " has been bombed and lost 3hp! ---" + ConsoleColors.RESET);
            player.removeHealth(3);
            cardDeck.getTrash().add(this);
            player.getActiveCards().remove(this);
            return;
        }

        System.out.println(ConsoleColors.GREEN + "--- " + player.getName() + " didn't explode and dynamite was passed! ---" + ConsoleColors.RESET);
        player.getActiveCards().remove(this);

        int index = players.indexOf(player);
        index--;

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
        players.get(index).getActiveCards().add(this);
    }
}

package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;

import java.util.ArrayList;
import java.util.Objects;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    public Prison(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay() {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        ArrayList<Player> filterPlayers = new ArrayList<>();

        for (Player other : players) {
            if (other == player) {
                continue;
            }

            boolean hasPrison = false;
            for (Card card : other.getActiveCards()) {
                if (Objects.equals(card.getName(), "Prison")) {
                    hasPrison = true;
                    break;
                }
            }
            if (!hasPrison) {
                filterPlayers.add(other);
            }
        }

        if (filterPlayers.size() == 0) {
            System.out.println(ConsoleColors.RED + "--- You cant use card prison on anyone! ---" + ConsoleColors.RESET);
            return;
        }

        Player target = player.chooseTarget(filterPlayers);
        target.getActiveCards().add(this);
        player.removeCard(this);
    }
    @Override
    public void playCard(Player player) {
        System.out.println(ConsoleColors.YELLOW + "--- Prison outbreak has happened. ---" + ConsoleColors.RESET);

        player.removeActiveCard(this);
        cardDeck.trash.add(this);
    }
}

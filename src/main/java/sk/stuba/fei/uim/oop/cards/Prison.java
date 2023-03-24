package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.consoleColors.ConsoleColors;
import java.util.ArrayList;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";
    private ArrayList<Player> canTarget;
    public Prison(CardDeck cardDeck) {
        super(CARD_NAME, cardDeck);
    }

    @Override
    public boolean canPlay(Player player) {
        canTarget = new ArrayList<>();

        for (Player target : cardDeck.getPlayers()) {
            if (target != player && target.isAlive()) {
                boolean targetHasPrisonActive = false;
                for (Card card : target.getActiveCards()) {
                    if (card instanceof Prison) {
                        targetHasPrisonActive = true;
                        break;
                    }
                }
                if (!targetHasPrisonActive) {
                    canTarget.add(target);
                }
            }
        }

        return canTarget.size() > 0;
    }
    @Override
    public void playCard(Player player, ArrayList<Player> players) {
        super.playCard(player, players);

        Player target = player.chooseTarget(canTarget);
        target.getActiveCards().add(this);
        player.removeCard(this);
    }
    @Override
    public void playCard(Player player) {
        System.out.println(ConsoleColors.YELLOW + "--- Prison outbreak has happened. ---" + ConsoleColors.RESET);

        player.removeActiveCard(this);
        cardDeck.getTrash().add(this);
    }
}

package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Card {
    private static final String CARD_NAME = "Cat Balou";
    private static final String CARD_COLOR = "Brown";

    public CatBalou() {
        super(CARD_NAME, CARD_COLOR, false);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    // TODO fix choosing option (he cant play it on player that doesn't have any cards)
    @Override
    public ArrayList<Card> playCard(Player caller, ArrayList<Player> others) {
        System.out.println(caller.getName() + " played cat balou.");
        ArrayList<Card> cardToThrowAway = new ArrayList<>();
        cardToThrowAway.add(this);
        int selectCardArray = -1;
        while (selectCardArray < 1 || selectCardArray > 2) {
            selectCardArray = ZKlavesnice.readInt("1. Active cards\n2. On hand cards");
            if (selectCardArray < 1 || selectCardArray > 2) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        Player otherPlayer = caller.choosePlayer(others);
        int randomCard;
        Card cardToRemove = null;
        switch(selectCardArray) {
            case 1:
                randomCard = (int) (Math.random() * otherPlayer.getActiveCards().size()-1);
                cardToRemove = otherPlayer.removeActiveCard(randomCard);
                break;
            case 2:
                randomCard = (int) (Math.random() * otherPlayer.getCards().size()-1);
                cardToRemove = otherPlayer.removeCard(randomCard);
                break;
        }
        cardToThrowAway.add(cardToRemove);
        return cardToThrowAway;
    }

    @Override
    public ArrayList<Card> playCard(Player player) {

        return null;
    }
}

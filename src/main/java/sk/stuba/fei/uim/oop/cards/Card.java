package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import java.util.ArrayList;

public abstract class Card {
    private final String name;
    private final String color;
    private final boolean useOnSelf;
    public Card(String name, String color) {
        this.name = name;
        this.color = color;
        this.useOnSelf = true;
    }
    public Card(String name, String color, boolean useOnSelf) {
        this.name = name;
        this.color = color;
        this.useOnSelf = useOnSelf;
    }

    public String getName() {
        return this.name;
    }
    public boolean getUseOnSelf() {
        return this.useOnSelf;
    }

    public String getColor() {
        return this.color;
    }
    public abstract boolean canPlay();
//    public abstract boolean canPlay(int index);
    public abstract ArrayList<Card> playCard(Player caller, ArrayList<Player> others);

    public abstract ArrayList<Card> playCard(Player player);


}

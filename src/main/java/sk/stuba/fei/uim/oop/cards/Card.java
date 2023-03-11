package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;

public abstract class Card {
    private String name;
    private String color;
    public Card(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

//    public abstract boolean canPlay();
//    public abstract boolean canPlay(int index);
    public abstract void playCard(Player player);
}

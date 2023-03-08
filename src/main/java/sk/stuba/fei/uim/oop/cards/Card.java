package sk.stuba.fei.uim.oop.cards;

public abstract class Card {
    private String name;
    private String color;
    public Card(String name, String color) {
        this.name = name;
        this.color = color;
    }

//    public abstract boolean canPlay();
//    public abstract boolean canPlay(int index);
}

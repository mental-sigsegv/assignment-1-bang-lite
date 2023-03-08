package sk.stuba.fei.uim.oop.player;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public void whoAmI() {
        System.out.println(this.name);
    }

    public String getName() {
        return this.name;
    }
}

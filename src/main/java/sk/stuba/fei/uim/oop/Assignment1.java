// // package sk.stuba.fei.uim.oop;
// import java.util.ArrayList;

// public class Assignment1 {
//     static public void main(String[] args) {
//         game();
//     }

//     public static void game() {
//         Player p1 = new Player();
//         Beer card1 = new Beer();
//         card1.setOwner(p1);
//         card1.effect();
//         System.out.println(p1.health);

//         p1.addCard(card1);
//         p1.addCard(card1);
//         p1.addCard(card1);
//         p1.addCard(card1);

//         p1.printCards();

//     }
 
//     public static class PlayerDeck {
//         protected ArrayList<Card> cards;

//         public PlayerDeck() {
//             cards = new ArrayList<Card>();
//         }

//         public void addCard(Card card) {
//             this.cards.add(card);
//         }

//         public ArrayList<Card> getCards() {
//             return cards;
//         }

//         public void printCards() {
//             for (Card card : cards) {
//                 System.out.println(card.getName());
//             }
//         }
//     }

//     public static class Player {
//         protected int health = 4;
//         protected String name;
//         protected PlayerDeck playerDeck = new PlayerDeck();

//         // Constructors
//         public Player(String name) {
//             this.name = name;
//         }

//         public Player() {
//             this.name = "Unknown player";
//         }

//         // Health
//         public void setHealth(int health) {
//             this.health = health;
//         }

//         public int getHealth() {
//             return this.health;
//         }

//         // Name
//         public void setName(String name) {
//             this.name = name;
//         }

//         public String getName() {
//             return this.name;
//         }

//         // Methods
//         public int isAlive() {
//             if (this.health <= 0) {
//                 return 0;
//             }
//             return 1;
//         }

//         // Cards
//         public void addCard(Card card) {
//             this.playerDeck.addCard(card);
//         }
    
//         public ArrayList<Card> getCards() {
//             return this.playerDeck.getCards();
//         } 

//         public void printCards() {
//             playerDeck.printCards();
//         }
//     }
    
//     public static class Card {
//         protected Player owner = null;
//         protected String name = "";

//         public void setOwner(Player owner) {
//             this.owner = owner;
//         }

//         public Player getOwner() {
//             return this.owner;
//         }

//         public void setName(String name) {
//             this.name = name;
//         }

//         public String getName() {
//             return this.name;
//         }
//     }
    
//     public static class Blue extends Card {
//         public void effect() {
    
//         }
//     }
    
//     public static class Barel extends Blue {
//         public void effect() {
            
//         }
//     }
    
//     public static class Dynamite extends Blue {
//         public void effect() {
    
//         }
//     }
    
//     public static class Prison extends Blue {
//         public void effect() {
    
//         }
//     }
    
//     public static class Brown extends Card {
//         public void effect() {
    
//         }
//     }
    
//     public static class Bang extends Brown {
//         public void effect() {
    
//         }
//     }
    
//     public static class Next extends Brown {
//         public void effect() {
    
//         }
//     }
    
//     public static class Beer extends Brown {
//         public void effect() {
//             int playerHealth = owner.getHealth();
//             owner.setHealth(--playerHealth);
//         }

//         // super.name = "beer";
//     }
    
//     public static class CatBalou extends Brown {
//         public void effect() {
    
//         }
//     }
    
//     public static class Dostavnik extends Brown {
//         public void effect() {
    
//         }
//     }
    
//     public static class Indians extends Brown {
//         public void effect() {
    
//         }
//     }

       
//     public void SelectPlayers() {
    
//     }
    
//     public void CreateGameDeck() {
//         // private ArrayList<Card> GameDeck;

//     }
// }


import Game.Card.Deck;

public class Main {

    public static void main(String[] args){
        Deck deck = new Deck(true);
        deck.shuffle();

        System.out.println(deck);
    }
}

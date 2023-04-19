package Game.Card;

import java.util.*;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

    }

    public void addCard(Card card){
        deck.add(card);

//        HashSet<Card> deck = new HashSet<>(card);;
    }

    public String toString(){
        //A string to hold everything we're going to return
        String output = "";

        //for each Card "card" in the deck
        for(Card card: deck){
            //add the card and the escape character for a new line
            output += card;
            output += "\n";
        }
        return output;
    }

    public Deck(boolean makeDeck){
        deck = new ArrayList<Card>();
        if(makeDeck){
            //fill deck with cards
            for (Suit suit: Suit.values()) {
                for (Rank rank : Rank.values()) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(deck, new Random());
    }

}


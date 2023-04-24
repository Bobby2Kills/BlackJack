package Game.Card;

import java.util.*;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

    }

    public void addCard(Card card){
        deck.add(card);
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

    public Card takeCard(){
        //take the top card from the deck
        Card cardToTake = new Card(deck.get(0));
        //remove the card from the deck
        deck.remove(0);
        return cardToTake;
    }

    public boolean hasCards() {
        return deck.size() > 0;
    }


    /**
     * Empties out this Deck
     */
    public void emptyDeck(){
        deck.clear();
    }

    /**
     *
     * @param cards an arraylist of cards to be added to this deck
     */

    public void addCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    /**
     * Take all the cards from a discarded deck and place them in this deck, shuffled.
     * Clear the old deck
     * @param discard - the deck we're getting the cards from
     */
    public void reloadDeckFromDiscard(Deck discard){
        this.addCards(discard.getCards());
        this.shuffle();
        discard.emptyDeck();
        System.out.println("Ran out of cards, creating new deck from discard pile & shuffling deck");
    }

    public int cardsLeft(){
        return deck.size();
    }



}


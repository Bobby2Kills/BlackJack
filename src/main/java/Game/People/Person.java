package Game.People;
import Game.Card.*;

public abstract class Person {

    private Hand hand;
    private String name;

    /**
     * Create a new person
     */
    public Person(){
        this.hand = new Hand();
        this.name = "";
    }

    public Hand getHand(){
        return this.hand;
    }

    public void setHand(Hand hand){
        this.hand = hand;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public boolean hasBlackjack(){
        //in the guide, this was an if/else statement. I have inferred that what I have written below should work the same
        return this.getHand().calculatedValue() == 21;
    }

    public void printHand(){
        System.out.println(this.name + "'s hand looks like this:");
        System.out.println(this.hand + " Valued at: " + this.hand.calculatedValue());
    }

    public void hit (Deck deck, Deck discard){

        //If there's no cards left in the deck
        if (!deck.hasCards()) {
            deck.reloadDeckFromDiscard(discard);
            System.out.println("Deck has been reloaded from discard pile");
        }
        this.hand.takeCardFromDeck(deck);
        System.out.println(this.name + " gets a card");
        this.printHand();

    }

}

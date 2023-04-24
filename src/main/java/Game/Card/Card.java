package Game.Card;

public class Card {
    private final Rank rank;
    private final Suit suit;
    public Card (Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue(){
        return rank.rankValue;
    }
    public Rank getRank() {
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    @Override
    public String toString(){
        return rank + " of " + suit;
    }

    public Card(Card card){
        this.suit = card.getSuit();
        this.rank = card.getRank();
     }

}



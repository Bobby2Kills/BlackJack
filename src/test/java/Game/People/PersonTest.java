package Game.People;

import Game.Card.Card;
import Game.Card.Deck;
import Game.Card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Game.Card.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Deck deck;
    Deck discardDeck;
    Hand hand;

    Player player;

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
        discardDeck = new Deck();
        hand = new Hand();
        player = new Player();



    }



    @Test
    void hasBlackjackIsTrueWhenValueIs21() {
        //empty deck
        deck = new Deck();
        //two cards adding to 21
        Card card1 = new Card(ACE, Suit.CLUBS);
        Card card2 = new Card(TEN, Suit.HEARTS);
        //add those cards to deck
        deck.addCard(card1);
        deck.addCard(card2);
        //take those cards from the take into the player's hand.
        player.getHand().takeCardFromDeck(deck);
        player.getHand().takeCardFromDeck(deck);

        //test
        assertTrue(player.hasBlackjack(), "blackjack status is not being triggered correctly");
    }

    @Test
    void hitTakesACardFromTheDeck(){

    }
}
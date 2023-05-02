package Game.People;

import Game.Card.Card;
import Game.Card.Deck;
import Game.Card.Suit;
import static Game.Card.Rank.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static Game.Card.Rank.ACE;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private Deck deck;

    private Deck discardDeck;
    private Hand hand;
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
        discardDeck = new Deck();
        hand = new Hand();
        dealer = new Dealer();
        player = new Player();
    }

    @Test
    void aHandStartsEmpty(){

        assertEquals(0, hand.getHand().size(), "A new empty hand has not been created correctly");
    }

    @Test
    void takeCardFromDeckAddsOneCardToHand(){
        hand.takeCardFromDeck(deck);
        assertEquals(1, hand.getHand().size(), "The takeCardFromDeck method has not did not increase the hand-size by one");
    }

    @Test
    void takeCardFromDeckAddsOneCardToDealerHand(){
        //take those cards from the take into the player's hand.
        dealer.getHand().takeCardFromDeck(deck);
        assertEquals(1, dealer.getHand().getHand().size(), "The takeCardFromDeck method has not did not increase the dealer's hand-size by one");
    }

    @Test
    void takeCardFromDeckUsedThreeTimesAddsThreeCardsToHand(){
        IntStream.range(0,3)
                .forEach(i -> hand.takeCardFromDeck(deck));
        assertEquals(3, hand.getHand().size(), "The hand-size has not increased by the expected amount of cards after taking multiple from deck");
    }

    @Test
    void takeCardFromDeckDefinitelyTakesFromTheTopOfTheDeck(){
        IntStream.range(0,3)
                        .forEach(i -> hand.takeCardFromDeck(deck));

        assertEquals("[DEUCE of HEARTS, THREE of HEARTS, FOUR of HEARTS]",hand.getHand().toString(), "The top cards from the deck were not taken");
    }

    @Test
    void aHandOfThreeAcesShouldBeWorth13(){
        deck = new Deck();
        Card card1 = new Card(ACE, Suit.CLUBS);
        Card card2 = new Card(ACE, Suit.HEARTS);
        Card card3 = new Card(ACE, Suit.SPADES);
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);
        IntStream.range(0,3)
                .forEach(i -> hand.takeCardFromDeck(deck));
        assertEquals(13, hand.calculatedValue(), "only the first ACE in a deck should be worth 11");
    }

    @Test
    void theOneAceInPlayersHandShouldBeWorth11WhenDealerHas2Aces(){
        deck = new Deck();
        Card card1 = new Card(ACE, Suit.CLUBS);
        Card card2 = new Card(ACE, Suit.HEARTS);
        Card card3 = new Card(ACE, Suit.SPADES);

        //Add the cards to deck
        Card[] cards = {card1,card2, card3};
        for (Card card : cards) {
            deck.addCard(card);
        }

        IntStream.range(0,2)
                .forEach(i -> dealer.getHand().takeCardFromDeck(deck));

        player.getHand().takeCardFromDeck(deck);

        assertEquals(11, player.getHand().calculatedValue(), "A player's solo ACE card does not value 11 if a dealer has two aces.");
    }

    @Test
    void aFourCardHandWithTwoAcesShouldAddUpCorrectly(){
            deck = new Deck();
            Card card1 = new Card(ACE, Suit.HEARTS);
            Card card2 = new Card(DEUCE, Suit.DIAMONDS);
            Card card3 = new Card(SEVEN, Suit.CLUBS);
            Card card4 = new Card(FOUR, Suit.DIAMONDS);

            //Add the cards to deck
            Card[] cards = {card1,card2, card3, card4};
            for (Card card : cards) {
                deck.addCard(card);
            }
            IntStream.range(0,4)
                    .forEach(i -> hand.takeCardFromDeck(deck));
            assertEquals(24, hand.calculatedValue(), "only the first ACE in a deck should be worth 11");
    }

    @Test
    void differentCardsInAHandAddUpCorrectly(){
        deck = new Deck();
        Card card1 = new Card(KING, Suit.CLUBS);
        Card card2 = new Card(DEUCE, Suit.HEARTS);
        Card card3 = new Card(THREE, Suit.SPADES);
        Card card4 = new Card(SIX, Suit.DIAMONDS);
        Card[] cards = {card1,card2, card3, card4};
        for (Card card : cards) {
            deck.addCard(card);
        }
        IntStream.range(0,4)
                .forEach(i -> hand.takeCardFromDeck(deck));
        assertEquals(21, hand.calculatedValue(), "A hand of KING, DEUCE, THREE and SIX should equal 21");
    }

    @Test
    void discardHandToDeckClearsHand(){
        IntStream.range(0,3)
                .forEach(i -> hand.takeCardFromDeck(deck));
        hand.discardHandToDeck(discardDeck);
        assertEquals(0, hand.getHand().size(), "the hand is not being cleared");
    }

    @Test
    void discardHandToDeckMovesCardsToDiscardDeck(){
        IntStream.range(0,3)
                .forEach(i -> hand.takeCardFromDeck(deck));
        hand.discardHandToDeck(discardDeck);
        assertEquals(3,discardDeck.getCards().size(), "cleared cards are not hitting the discard pile");
    }



}
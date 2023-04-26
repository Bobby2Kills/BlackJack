package Game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.stream.IntStream;

import static Game.Card.Rank.ACE;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;
    private Deck deck2;


    @Test
    void addCardCreatesOneCard() {
        deck= new Deck();
        Card card1 = new Card(ACE,Suit.CLUBS);
        deck.addCard(card1);
        assertEquals(1, deck.getCards().size(), "addCard is NOT just creating one card");
    }

    @BeforeEach
    void setUp() {

        deck = new Deck(true);
        deck2 = new Deck(true);
    }



    @Test
    void thereAre52CardsInADeck() {
        assertEquals(52, deck.getCards().size(), "The deck should contain 52 cards, and right now it doesn't");
    }

    @Test
    void theSecondCardIsTHREEOfHEARTS() {
        assertEquals("THREE of HEARTS", deck.getCards().get(1).toString(), "The default deck order is not as expected");
    }

    @Test
    void theTHREEOfHEARTSValueIs3() {
        assertEquals(3, deck.getCards().get(1).getValue(), "The card's value does not match its name");
    }

    @Test
    void shufflingTheDeckChangesTheOrderOfCards() {
        deck.shuffle();
        assertNotEquals(deck2.getCards(), deck.getCards(), "The shuffle function is not working as intended");
    }

    @Test
    void shufflingTheDeckDoesNotGainOrLoseCards() {
        deck.shuffle();
        assertEquals(52, deck.getCards().size(), "Shuffling the deck is not retaining the same amount of cards");
    }

    @Test
    void noTwoShufflesAreTheSame() {
        deck.shuffle();
        deck2.shuffle();
        assertNotEquals(deck,deck2, "Shuffling the deck is not producing a different order each time");
    }

    @Test
    void takeCardRemovesOneCardFromDeck() {
        deck.takeCard();
        assertEquals(51, deck.getCards().size(), "the takeCard() function is not removing the expected number of cards");
    }

    @Test
    void takeCardUsedMultipleTimesRemovesTheCorrectAmountOfCards() {
        //The IntStream is basically saying run this code x times
        IntStream.range(0, 10)
                .forEach(i -> deck.takeCard());
        assertEquals(42,deck.getCards().size(), "takeCard() used multiple times is not removing the intended amount from the deck");
    }

    @Test
    void hasCardsIsTrueIfTrue(){
        assertEquals(true,deck.hasCards(), "hasCards() is not working as intended");
    }

    @Test
    void hasCardsIsFalseIfFalse(){
        deck = new Deck();
        assertEquals(false,deck.hasCards(), "hasCards() is not working as intended");
    }

    @Test
    void emptyDeckClearsDeck(){
        deck.emptyDeck();
        assertEquals(0,deck.getCards().size(), "emptyDeck() is not clearing as intended");
    }

    @Test
    void cardsLeftShowsRemainingCards(){
        assertEquals(deck.getCards().size(), deck.cardsLeft(), "cardsLeft() is not returning the correct number of cards");
    }



}
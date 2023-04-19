package Game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        assertEquals(1, deck.getCards().size(), "addCard adds one card to the deck");
    }

    @BeforeEach
    void setUp() {

        deck = new Deck(true);
        deck2 = new Deck(true);
    }



    @Test
    void thereAre52CardsInADeck() {
        assertEquals(52, deck.getCards().size(), "The deck should contain 52 cards");
    }

    @Test
    void theSecondCardIsDEUCEOfHEARTS() {
        assertEquals("DEUCE of HEARTS", deck.getCards().get(1).toString(), "The second card should be DEUCE of HEARTS");
    }

    @Test
    void theDEUCEOfHEARTSValueIs2() {
        assertEquals(2, deck.getCards().get(1).getValue(), "The DEUCE of HEARTS' value is 2");
    }

    @Test
    void shufflingTheDeckChangesTheOrderOfCards() {
        deck.shuffle();
        assertNotEquals(deck2, deck.getCards(), "The shuffled deck should be in a different order");
    }

    @Test
    void shufflingTheDeckDoesNotGainOrLoseCards() {
        deck.shuffle();
        assertEquals(52, deck.getCards().size(), "The shuffled deck has not lost or gained any cards");
    }



}
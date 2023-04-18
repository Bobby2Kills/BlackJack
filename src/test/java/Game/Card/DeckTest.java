package Game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
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

}
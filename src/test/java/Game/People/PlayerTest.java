package Game.People;

import Game.Card.Card;
import Game.Card.Deck;
import Game.Card.Suit;
import Game.Game;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.IntStream;

import static Game.Card.Rank.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    Deck deck;
    Deck discard;
    Hand hand;
    Player player;
    Dealer dealer;


    @BeforeEach
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void setUp() {
        deck = new Deck(true);
        discard = new Deck();
        hand = new Hand();
        player = new Player("John");
        dealer = new Dealer();
    }

    @Test
    void makeDecisionTriggersStandTextFrom2(){
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt()).thenReturn(2);
            System.setIn(new ByteArrayInputStream("2".getBytes()));
            player.input = scanner;

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            player.makeDecision(deck, discard);

            String expectedOutput = "Would you like to 1) Hit or 2) Stand\r\nYou stand.\r\n";
            assertEquals(expectedOutput, outputStream.toString(), "opting to stand doesn't print correctly in the console");
    }

    @Test
    void makeDecisionTriggersHitTextFrom1(){
        ////

        //New blank deck
        deck = new Deck();
        // Create some cards
        Card card1 = new Card(ACE, Suit.CLUBS);
        Card card2 = new Card(ACE, Suit.HEARTS);
        Card card3 = new Card(ACE, Suit.SPADES);
        Card card4 = new Card(ACE, Suit.DIAMONDS);
        Card card5 = new Card(DEUCE, Suit.CLUBS);
        Card card6 = new Card(DEUCE, Suit.HEARTS);

        System.out.println(deck);

        //Add the cards to deck
        Card[] cards = {card1,card2, card3,card4,card5,card6};
        for (Card card : cards) {
            deck.addCard(card);
        }

        //take the cards from deck to dealer hand
        IntStream.range(1,3)
                .forEach(i -> dealer.getHand().takeCardFromDeck(deck));
        //take the cards from deck to player hand
        IntStream.range(1,3)
                .forEach(i -> player.getHand().takeCardFromDeck(deck));

        //Initiate the mock
        Scanner scanner = Mockito.mock(Scanner.class);
        // scanner input
        Mockito.when(scanner.nextInt())
                .thenReturn(1)
                .thenReturn(0); //required to prevent infinite recursion, as it would 'hit' forever in this test.
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        player.input = scanner;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        player.makeDecision(deck, discard);


       String expectedOutput = "Would you like to 1) Hit or 2) Stand\r\nJohn gets a card\r\nJohn's hand looks like this:\r\nACE of SPADES - ACE of DIAMONDS - DEUCE of CLUBS -  Valued at: 14\r\nWould you like to 1) Hit or 2) Stand\r\n";
       assertEquals(expectedOutput, outputStream.toString(), "opting to hit doesn't print correctly in the console");
    }

    @Test
    void makeDecisionTriggersPlayerBustTextWhenHitTakesOver21() {
        //New blank deck
        deck = new Deck();
        // Create some cards
        Card card1 = new Card(THREE, Suit.CLUBS);
        Card card2 = new Card(THREE, Suit.HEARTS);
        Card card3 = new Card(ACE, Suit.SPADES);
        Card card4 = new Card(THREE, Suit.DIAMONDS);
        Card card5 = new Card(KING, Suit.CLUBS);
        Card card6 = new Card(DEUCE, Suit.HEARTS);


        System.out.println(deck);

        //Add the cards to deck
        Card[] cards = {card1,card2,card3,card4,card5,card6};
        for (Card card : cards) {
            deck.addCard(card);
        }



        //take the cards from deck to dealer hand
        IntStream.range(0,2)
                .forEach(i -> dealer.getHand().takeCardFromDeck(deck));
        //take the cards from deck to player hand
        IntStream.range(0,2)
                .forEach(i -> player.getHand().takeCardFromDeck(deck));

        //Initiate the mock
        Scanner scanner = Mockito.mock(Scanner.class);
        // scanner input
        Mockito.when(scanner.nextInt())
                .thenReturn(1)
                .thenReturn(0); //required to stand to prevent infinite recursion, as it would 'hit' forever in this test.
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        player.input = scanner;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        player.getHand();

        player.makeDecision(deck, discard);

        player.getHand().takeCardFromDeck(deck);

        player.getHand();




        String expectedOutput = "Would you like to 1) Hit or 2) Stand\r\nJohn gets a card\r\nJohn's hand looks like this:\r\nACE of SPADES - THREE of DIAMONDS - KING of CLUBS -  Valued at: 24\r\nYou're busted mate!\r\n";
        assertEquals(expectedOutput, outputStream.toString(), "player busting after a hit doesn't print correctly in the console");
    }
}

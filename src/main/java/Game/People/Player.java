package Game.People;

import Game.Card.Card;
import Game.Card.Deck;

import java.util.Scanner;

/**
 * Handles all Player specific operations
 */

public class Player extends Person {
    private String playerName;

    Scanner input = new Scanner(System.in);


    //Create a new Player
    public Player(String playerName) {
        super();
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void makeDecision(Deck deck, Deck discard) {

        int decision = 0;
        boolean getNum = true;

        //while we're getting a number...
        while(getNum){

            try{
                System.out.println("Would you like to 1) Hit or 2) Stand");
                decision = input.nextInt();
                getNum = false;

            }
            catch(Exception e){
                System.out.println("Invalid, please enter 1 or 2");
                input.next();
                //we don't close the scanner here, because we will need it later.
            }
        }

        //if they decide to hit
        if (decision == 1){
            //hit the deck using the deck and discard deck
            this.hit(deck, discard);
            if(this.getHand().calculatedValue() > 21) {
                System.out.println("You're busted mate!");

            }
            //return (exit the method) if they have blackjack or busted
            if(this.getHand().calculatedValue()>20){
                return;
            }
            //if they didn't bust or get 21, allow them to decide to hit or stand again by going back to this same method
            else{
                this.makeDecision(deck,discard);
            }

        }
        else if( decision == 2){
            System.out.println("You stand.");
        }
        else{
            input.nextInt();
        }

    }


}

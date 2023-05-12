package Game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Game.Game;
@SpringBootApplication
public class BlackjackGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackjackGameApplication.class, args);
            System.out.println("Get ready to play.........");
            System.out.println(".................");
            System.out.println("Blackjack!");
            //Starts the game
            Game blackjack = new Game();


        }
    }

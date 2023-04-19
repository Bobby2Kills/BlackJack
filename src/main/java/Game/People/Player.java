package Game.People;

import Game.Card.Card;

import java.util.ArrayList;

public class Player {
    private String nickName;
    private int playerCardCount;
    ArrayList<Card> playerHand;

    public Player(String name) {
        this.nickName = name;
        playerHand = new ArrayList<Card>();
    }

    public String getNickName() {
        return nickName;
    }
}

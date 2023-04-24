package Game.People;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void SetUp(){
        dealer = new Dealer();
    }

    @Test

    void theNameOfTheDealerIsDealer(){

        assertEquals("Dealer", dealer.getName(), "The Dealer's name is incorrect");

    }


}
package pl.wbubula.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    @Test
    void itAssignCredit(){
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1500));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getCreditLimit()
        );
    }

    @Test
    void itAssignCreditV2(){
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCreditLimit(BigDecimal.valueOf(1700));
        //Assert
        assert BigDecimal.valueOf(1700).equals(card.getCreditLimit());
    }

    @Test
    void itDenyCreditBelowThreshold() {
        CreditCard card = new CreditCard();
//        var card = new CreditCard();
        //python lambda x: x + 2
        //java   (x) -> x + 2

        assertThrows(
                CreditBelowThresoldException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(10))
        );

        try{
            card.assignCreditLimit(BigDecimal.valueOf(50));
            assert false;
        } catch (CreditBelowThresoldException e) {
            assert true;
        }
    }

    @Test
    void itDenyCreditReassignment(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));
        assertThrows(
                CreditAlreadyAssignedException.class,
                () -> card.assignCreditLimit(BigDecimal.valueOf(1200))
        );
    }

    @Test
    void itAllowsToPayForSomething(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertEquals(
                BigDecimal.valueOf(100),
                card.getBalance()
        );
    }

    @Test
    void itDenyWhenNoSufficientFounds(){
        CreditCard card = new CreditCard();
        card.assignCreditLimit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.pay(BigDecimal.valueOf(200))
        );
    }
}

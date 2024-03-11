package pl.wbubula.creditcard;

import java.math.BigDecimal;

public class CreditCard {

    private BigDecimal creditLimit;
    private BigDecimal creditBalance;

    public void assignCreditLimit(BigDecimal creditLimit) {

        if(isCreditCardAlreadyAssigned()){
            throw new CreditAlreadyAssignedException();
        }
        //100 < x
        if (isCreditCardBelowThreshold(creditLimit)){
            throw new CreditBelowThresoldException();

        }
        this.creditLimit = creditLimit;
    }

    private boolean isCreditCardAlreadyAssigned() {
        return this.creditLimit != null;
    }

    private static boolean isCreditCardBelowThreshold(BigDecimal creditLimit) {
        return BigDecimal.valueOf(100).compareTo(creditLimit) > 0;
    }

    public BigDecimal getBalance() {
        return creditBalance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void pay(BigDecimal money) {
        if(this.creditBalance.subtract(money).compareTo(BigDecimal.ZERO) > 0){
            throw new NotEnoughMoneyException();
        }

        this.creditBalance=this.creditBalance.subtract(money);
    }
}

package store;

/**
 * This class represents the money that the Bakery has on hand.
 * Note that the dollar amounts are Integers to avoid weird floating point number issues.
 */
public class CashRegister {

    private Integer cashAmountInRegister = 0;

    public void addCashToRegister(Integer amountToAdd){
        this.cashAmountInRegister += amountToAdd;
    }
    public Integer getCashAmountInRegister(){
        return this.cashAmountInRegister;
    }
}

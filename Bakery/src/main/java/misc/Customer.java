package misc;

import employees.Cashier;

public class Customer {
    private String name;
    private Integer cashAmountInWallet;

    /**
     * Constructor that initializes the customer's name and a cash amount in their wallet
     * @param name
     */
    public Customer(String name, Integer cashAmountInWallet)
    {
        this.name = name;
        this.cashAmountInWallet = cashAmountInWallet;
    }

    /**
     * The customer's name
     * @return the name of the customer
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * This method returns the amount in the Customer's wallet
     * @return the amount in the customer's wallet
     */
    public Integer getCashAmountInWallet(){
        return this.cashAmountInWallet;
    }

    /**
     * Pay a certain amount to a cashier
     * @param amountToPay
     */
    public Integer payCashier(Cashier cashier, Integer amountToPay){
        return this.cashAmountInWallet -= amountToPay;
    }

    /**
     * We expect this method to be called by the Cashier once it is determined that the proper amount of
     * money has been paid
     * @param itemName
     * @param quantityDesired
     */
    public void receiveOrder(String itemName, Integer quantityDesired){
        System.out.println(this.name + " says: Thanks for baking me " + quantityDesired + " " + itemName);
    }
}

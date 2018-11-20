package employees;

import store.CashRegister;
import store.Inventory;
import java.util.Map;
import java.util.HashMap;


/**
 *
 * A store owner mostly acts as a middle man between certain entities (i.e., cashier, cash register, inventory)
 *
 */
public class StoreOwner {


    private Map<String, Integer> menu = new HashMap<>();

    private Inventory inventory;
    private CashRegister cashRegister;

    public StoreOwner(Inventory inventory, CashRegister cashRegister){
        this.cashRegister = cashRegister;
        this.inventory = inventory;

    }

    // Protects the user from getting the price of an item not in the hashmap.
    public Integer getItemPrice(String itemName)throws Exception {
        if(menu.containsKey(itemName)) return this.menu.get(itemName);
        throw new Exception();
    }

    // If item is in hashmap set the price, otherwise replace item's value
    public void setItemPrice(String itemName, Integer itemPrice) {

        if(!menu.containsKey(itemName)) menu.put(itemName, itemPrice);
         menu.replace(itemName, itemPrice);
    }

    /*
    * Because of how inventory works, if the item is not located in the hashmap it places it in there. Therefore, storeowner
    * must check before passing an item which may not exist in its hashmap to inventory. The exception is used to indicate to
    * the user the pass failed.
    * */

    void addItemToInventory(String itemName, Integer quantity) throws Exception {
        if(menu.containsKey(itemName))
        {
            this.inventory.addItemToInventory(itemName, quantity);
            return;
        }
        throw new Exception();
    }
    // Allows for granting a copy of the menu, used for the display
    Map<String, Integer> getMenu() {return this.menu;}


    public Integer getItemCount(String itemName) {return this.inventory.getItemCount(itemName);}


    void removeItemsFromInventory(String itemName, Integer quantity) throws Exception {
        this.inventory.removeItemFromInventory(itemName, quantity);}



    /**
     * We expect this method to be called by the Cashier when a customer has paid for an item.
     * @param paymentAmount the amount that should be added to the cash register
     */
    public void receivePayment(Integer paymentAmount) {
        this.cashRegister.addCashToRegister(paymentAmount);
    }

    /**
     * This is a getter method that returns the amount of money in the cash register at the moment
     * @return the amount of money in the cash regsiter
     */
    public Integer getCashAmountInRegister(){
        return this.cashRegister.getCashAmountInRegister();
    }
}

package employees;

import misc.Customer;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The cashier is the front man for the entire Bakery.
 * The cashier can coordinate with the StoreOwner to get items from inventory and to receive customer payments
 * The Cashier also ask the baker to bake more items
 * The store owner interacts with the inventory as well as the cash register
 */
public class Cashier {

    // We store a reference to the StoreOwner of the Bakery
    private StoreOwner storeOwner;
    private Baker baker;
    private Map<Integer, String> menu = new HashMap<>();
    /**
     * Constructor. Initialize our internal reference for StoreOwner
     * @param storeOwner
     */
    public Cashier(StoreOwner storeOwner, Baker baker){
        this.storeOwner = storeOwner;
        this.baker = baker;

    }

    // maps the menu in storeOwner to itemNumbers
    // Keeps the structure of ordering same ex. 1. cookies, 2. Cakes, etc..
    private void initializeMenu()
    {
        int counter = 1;
        for(String x: storeOwner.getMenu().keySet())
        {
            menu.put(counter, x);
            counter++;
        }
    }


    /**
     * When a customer comes to our bakery, this is where the core resides:
     *
     * 1) We greet them using their name.
     * 2) We ask them if they want Cakes (which has an item # of 1) or Muffins (which has an item # of 2)
     * 3) After they've made a selection, we ask them how many of this item they wish to have
     * 4) We will attempt to determine if the bakery even has enough of the item on hand
     *    by asking the StoreOwner about the inventory levels indirectly. Remember that a cashier cannot
     *    manage any aspect of the inventory themselves.
     * 5) If we don't have enough of the item on hand, ask the baker to bake more
     * 6) Once we have enough of the item, determine the payment amount that the customer owes us
     * 6) Have the customer pay us
     * 7) Make sure the customer receives the items they have ordered
     * @param customer
     */
    public void provideService(Customer customer){
        initializeMenu();  // when the customer is about to order get the menu from storeOwner and place it in a hashmap
        // Greet the customer01
        System.out.println("Hello " + customer.getName() + ", enter a number to order (-1 to exit).");

        // Determine the item number and quit if the item number is invalid
        Integer itemNumber = promptForItemType();

        if (!menu.containsKey(itemNumber)) // Cake == 1, Muffin == 2
            return;

        // Determine the quantity desired and quit if -1 is provided
        Integer quantityDesired = promptForQuantityDesired(itemNumber);
        if (quantityDesired < 0)
            return;
        String itemChosen = menu.get(itemNumber);
        try {
        // Handle the scenario when the bakery does not have enough of the specific item to sell
        if (!hasCorrectItemQuantity(itemNumber, quantityDesired)) { // should the baker always have 0 items or have a small padding
            baker.bakeItem(itemChosen, storeOwner.getItemCount(itemChosen) - quantityDesired);
        }


            // Determine how much the customer owes and collect payment
            Integer paymentAmount = determinePaymentAmount(itemNumber, quantityDesired);
            System.out.println("Hey " + customer.getName() + ", you owe: " + paymentAmount);

            // Hint: Are we missing something here?
            receivePayment(determinePaymentAmount(itemNumber, quantityDesired));
            // The cashier (this class) asks the store owner to remove a certain number of cakes from the inventory
            this.storeOwner.removeItemsFromInventory(itemChosen, quantityDesired);
            customer.receiveOrder(itemChosen, quantityDesired);
        }

        catch (Exception e) {System.out.println("Menu Issue, unable to retrieve Item");}
        // The cashier (this class) calls the receive() method of the customer reference

    }

    /**
     * This method accepts money from the customer
     * @param paymentAmount the amount we receive from a customer
     */
    public void receivePayment(Integer paymentAmount){
        this.storeOwner.receivePayment(paymentAmount);
    }


    /**
     * Ask the user for an item number
     *
     * @return the item number the selected, which may also be -1 to quit
     */
    private Integer promptForItemType(){

        Scanner scanner = new Scanner(System.in);
        for(Map.Entry<Integer, String> entry : menu.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        Integer selection = scanner.nextInt();
        return selection;
    }

    /**
     * Ask the user for the quantity of the item they wish to purchase
     *
     * @return the item quantity they have specified, which may also be -1 to quit
     */
    private Integer promptForQuantityDesired(Integer itemNumber){
        Integer quantityDesired = 0;
        System.out.println("How many " + menu.get(itemNumber) + " would you like?");

        Scanner scanner = new Scanner(System.in);
        quantityDesired = scanner.nextInt();
        return quantityDesired;

    }

    /**
     *  This method delegates to the StoreOwner reference to determine the inventory levels and quantity
     *
     * @param itemType
     * @param quantityDesired
     * @return
     */
    private boolean hasCorrectItemQuantity(Integer itemType, Integer quantityDesired)  {
        if(storeOwner.getItemCount(menu.get(itemType)) < quantityDesired) return false;
         return true;
    }

    /**
     * This method delegates to the StoreOwner reference to determine the payment amount owned.
     *
     * @param itemType 1 == Cake, 2 == Muffin
     * @param quantityDesired the quantity of an item a customer wants to purchase
     * @return the price associated with some number of cakes or muffins
     */
    private Integer determinePaymentAmount(Integer itemType, Integer quantityDesired) throws Exception {
        Integer itemPrice = this.storeOwner.getItemPrice(menu.get(itemType));
        return itemPrice * quantityDesired;
    }
}
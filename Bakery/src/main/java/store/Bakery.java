package store;

import employees.Baker;
import employees.Cashier;
import employees.StoreOwner;
import misc.Customer;
import java.util.Map;
/**
 * This is the main class that stores all object references/dependencies for our Bakery
 *
 */
public class Bakery {

    // A Bakery has references (a.k.a dependencies) on the following classes:
    private Cashier cashier;
    private Baker baker;


    // Notice that the following references/dependencies are package private.
    // This is done to allow our BakeryTest class to have direct access to these fields for testing purposes.
    Inventory inventory;
    StoreOwner storeOwner;
    CashRegister cashRegister;

    /**
     * Constructor. Note that inside the constructor, when we instantiate certain objects, they depend on other objects.
     * For example, store owner depends on having references to inventory and cash register.
     * This is because the store owner needs to utilize these objects to get its own functions completed.
     */
    public Bakery()
    {

        this.inventory = new Inventory();
        this.cashRegister = new CashRegister();

        // A StoreOwner needs to reference both the Inventory and the CashRegister.
        // This is because only the StoreOwner is allowed to manage these as per the requirements.
        this.storeOwner = new StoreOwner(inventory, cashRegister);
        this.baker = new Baker(this.storeOwner);
        this.cashier = new Cashier(this.storeOwner, this.baker);

    }


    /**
     * When a customer comes to visit, we delegate the responsibility of providing service to our
     * Cashier object.
     *
     * @param customer
     */
    public void visit(Customer customer) {

        // In order for a cashier to provide service to a customer during their visit,
        // the cashier must collaborate with both the customer and the store owner.
        // The customer is passed in as an argument but the store owner is passed in
        // in the new employees.Cashier() constructor.
        this.cashier.provideService(customer);

    }


}

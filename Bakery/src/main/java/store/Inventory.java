package store;
import java.util.Map;
import java.util.HashMap;

/**
 * This class is meant to be utilized by the store Owner.
 * The store Owner is the only employee with access to the inventory
 */
public class Inventory {


    private Map<String, Integer> menu = new HashMap<>();;


   // add an item to the inventory, if already within simply update the quantity
    public void addItemToInventory(String itemName, Integer quantity) {
        if(!menu.containsKey(itemName)) menu.put(itemName, quantity);
        menu.replace(itemName, menu.get(itemName) + quantity);

    }

    //if the item isn't located within the hashmap throw an exception to indicate so.
    public void removeItemFromInventory(String itemName, Integer quantityDesired) throws Exception  {
     if(menu.containsKey(itemName)) menu.replace(itemName, menu.get(itemName) - quantityDesired);
     else throw new Exception();
    }
// if the item is located within the hashmap return its quantity; otherwise return 0. Accessing an item not in the menu is dealt with the remove fuction
    public Integer getItemCount(String itemName)  {
       if(menu.containsKey(itemName)) return this.menu.get(itemName);
       return 0;
    }



}

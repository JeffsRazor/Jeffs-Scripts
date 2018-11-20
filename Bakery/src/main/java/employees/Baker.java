package employees;

/**
 * A Baker bakes both Cakes and Muffins.
 * As soon as a cake or muffin is baked, we ask the StoreOwner to handle it (by placing it into the inventory).
 * Remember that the StoreOwner is the only entity that should be manipulating the inventory
 */
public class Baker {

    private StoreOwner storeOwner;

    public Baker(StoreOwner storeOwner){
        this.storeOwner = storeOwner;
    }

   // Ask the storeOwner to bake an item.
    public void bakeItem(String itemName, Integer itemQuantity)  throws Exception {
        storeOwner.addItemToInventory(itemName, itemQuantity);}

}

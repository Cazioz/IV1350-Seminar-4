package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * Thrown when the specified ItemIdentifier is not found in the database.
 */
public class ItemNotFoundException extends Exception {
    private ItemIdentifier itemID;
    
    /**
     * Creates a new instance of ItemNotFoundException
     * 
     * @param itemID the ItemIdentifier which was not found in the inventory
     */
    public ItemNotFoundException(ItemIdentifier itemID) {
        this.itemID = itemID;
    }
    
    /**
     * Returns the ItemIdentifier that was not found in the inventory
     * 
     * @return the ItemIdentifier that was not found in the inventory
     */
    public ItemIdentifier getItemIdentifier() {
        return this.itemID;
    }
}

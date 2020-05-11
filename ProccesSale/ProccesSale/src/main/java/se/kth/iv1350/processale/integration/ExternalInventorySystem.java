package se.kth.iv1350.processale.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processale.model.Sale;

/**
 *
 * @author Oscar Eklund
 * 
 * This class is responsible for retrieving items from external inventory
 */
public class ExternalInventorySystem {
    private List<Item> listOfItems = new ArrayList<>();
    
    /**
     * Creates an instance of ExternalInventorySystem
     */
    ExternalInventorySystem() {
        addItems();
    }
    
    private void addItems() {
        listOfItems.add(new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, (new ItemIdentifier(1234.0))));
        listOfItems.add(new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, (new ItemIdentifier(9876.0))));
        listOfItems.add(new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, (new ItemIdentifier(5.0))));
    }
    
    /**
     * Retrieves an item matching the id (if one is found)
     * 
     * @param id The ItemIdentifier that is to be matched
     * @return   The Item matching the id, else null
     * @throws se.kth.iv1350.processale.integration.ItemNotFoundException
     *         when an Item with ItemIdentifier id cannot be found in inventory
     * @throws se.kth.iv1350.processale.integration.DatabaseException
     *         when connection to external inventory fails
     */
    Item getItem(ItemIdentifier id) 
            throws DatabaseException, ItemNotFoundException{
        if(id.getDoubleIdentifier() == 42.0) {
            throw new DatabaseException();
        }
        for (Item itemInList : listOfItems) {
            if(itemInList.matchingItems(id)) {
                return itemInList;
            }
        }
        throw new ItemNotFoundException(id);
    }
    
    /**
     * Placeholder for future implementation to report sales to the 
     * external inventory system. Currently does nothing.
     * 
     * @param currentSale The current sale that shall be reported
     */
    void reportSale(Sale currentSale) {
        
    }
}

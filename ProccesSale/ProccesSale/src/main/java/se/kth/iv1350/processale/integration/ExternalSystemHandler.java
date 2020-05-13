package se.kth.iv1350.processale.integration;

import java.time.LocalDate;
import java.time.LocalTime;
import se.kth.iv1350.processale.model.Sale;

/**
 *
 * @author Oscar Eklund
 * 
 * This class instantiates the classes handling the external systems, 
 * as well as directing calls to them.
 */
public class ExternalSystemHandler {
    private final ExternalInventorySystem extInvSys;
    private final ExternalAccountingSystem extAccSys;
    private final Printer printer;
    
    public ExternalSystemHandler() {
        extInvSys = new ExternalInventorySystem();
        extAccSys = new ExternalAccountingSystem();
        printer = new Printer();
    }
    
    /**
     * Calls the external inventory system and returns item corresponding to
     * identifier if an item is found
     * 
     * @param id The identifier of the item
     * @return   The item corresponding to id if it is found
     * @throws se.kth.iv1350.processale.integration.ItemNotFoundException
     *         when an Item with ItemIdentifier id cannot be found in inventory
     * @throws se.kth.iv1350.processale.integration.OperationFailedException
     *         when connection to external inventory fails
     */
    public Item getItem(ItemIdentifier id) throws 
            ItemNotFoundException, OperationFailedException{
        try {
        return extInvSys.getItem(id);
        }
        catch(DatabaseException dbExc){
            System.out.println("---LOG DATABASEEXCEPTION---");
            System.out.println("Failed to connect to database with IP " 
                    + dbExc.getIPAddress() + ". \nDate and Time: " 
                    + LocalDate.now() + ", " + LocalTime.now());
            dbExc.printStackTrace();
            System.out.println("---END OF LOG DATABASEEXCEPTION---");
           
            throw new OperationFailedException("Could not connect to database",
                    dbExc);
        }
    }
    
    /**
     * Placeholder for future implementation to report sales to the 
     * external accounting and inventory systems. Currently does nothing.
     * 
     * @param currentSale The current sale that shall be reported
     */
    public void reportSale(Sale currentSale) {
        extInvSys.reportSale(currentSale);
        extAccSys.reportSale(currentSale);
    }
    
    /**
     * Prints the receipt (placeholder, simply prints to system.out)
     * 
     * @param receiptString The string version of receipt
     */
    public void printReceipt(String receiptString) {
        printer.printReceipt(receiptString);
    }
}

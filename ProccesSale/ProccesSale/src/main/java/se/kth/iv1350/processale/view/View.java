package se.kth.iv1350.processale.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.kth.iv1350.processale.controller.Controller;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.ItemIdentifier;
import se.kth.iv1350.processale.integration.ItemNotFoundException;
import se.kth.iv1350.processale.integration.OperationFailedException;

/**
 * 
 * @author Oscar Eklund
 * 
 * This program has no view, instead, 
 * this class is a placeholder for the entire view
 */
public class View {
    private Controller contr;
    
     /**
      * Creates a new instance of the view
      * 
      * @param controller Used to pass calls to the model layer
      */
    public View(Controller controller) {
        this.contr = controller;
        contr.addSaleObserver(new TotalRevenueView());
    }
    
    /**
     * A sample execution of the program, which just performs an example of how
     * a sale could be performed.
     */
    public void sampleExecution() {
        contr.startSale();
        System.out.println("SAMPLEEXECUTION: A new sale has been started.");
        
        System.out.println("Adding milk");
        addItem(new ItemIdentifier(1234.0));
        
        System.out.println("\nTrying to add nonexisting item");
        addItem(new ItemIdentifier(1337.0));
        
        System.out.println("\nTest getting connection error");
        addItem(new ItemIdentifier(42.0));
        
        Double price = contr.endSale();
        System.out.println("\nSale has ended, price was " + price + "kr");
        AmountDTO change = contr.enterPayment(new AmountDTO(20.0, "SEK"));
        
        contr.startSale();
        System.out.println("\nSAMPLEEXECUTION: A new sale has been started.");
        System.out.println("Adding 3x milk");
        addItem(new ItemIdentifier(1234.0));
        addItem(new ItemIdentifier(1234.0));
        addItem(new ItemIdentifier(1234.0));
        Double priceOfSecondSale = contr.endSale();
        System.out.println("\nSale has ended, price was " + priceOfSecondSale
                + "kr");
        AmountDTO changeOfSecondSale = 
                contr.enterPayment(new AmountDTO(100.0, "SEK"));
    }
    
    private void logMsg(ItemNotFoundException infex) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append("---LOG ITEMNOTFOUNDEXCEPTION---\n");
        logMsgBuilder.append("Failed to find Item with ItemIdentifier " +  
                    infex.getItemIdentifier().getDoubleIdentifier() 
                    + ".\nDate and Time: " + LocalDate.now() + 
                    ", " + LocalTime.now() + "\n");
        System.out.print(logMsgBuilder.toString());
        infex.printStackTrace();
        System.out.println("---END OF LOG ITEMNOTFOUNDEXCEPTION---");
    }
    
    private void printOperationFailedException(OperationFailedException opfex) {
        System.out.println("EXCEPTION - View: Could not connect to inventory, " 
                    + "check internet connection");
    }
    
    private void printItemNotFoundException(ItemNotFoundException infex) {
        System.out.println("EXCEPTION - View: Item with Item Identifier " 
                    + infex.getItemIdentifier().getDoubleIdentifier()
                    + " was not found in inventory");
            logMsg(infex);
    }
    
    private void addItem(ItemIdentifier id) {
        try {
            contr.addNewItem(id);
        } catch (ItemNotFoundException infex) {
            printItemNotFoundException(infex);
        } catch (OperationFailedException opfex) {
            printOperationFailedException(opfex);
        }
    }
}

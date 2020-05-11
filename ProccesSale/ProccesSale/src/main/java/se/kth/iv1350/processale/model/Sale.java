package se.kth.iv1350.processale.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processale.integration.AddressDTO;
import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.integration.ExternalSystemHandler;
import se.kth.iv1350.processale.integration.Item;

/**
 *
 * @author Oscar Eklund
 * 
 * This class handles an ongoing Sale
 */
public class Sale {
    private Receipt receipt;
    private List<SaleObserver> saleObservers = new ArrayList<>();
    
    /**
     * Creates an instance of Sale
     * 
     * @param addrDTO The Address of the store
     */
    public Sale(AddressDTO addrDTO) {
        this.receipt = new Receipt(addrDTO);
    }
    
    /**
    * Adds a new item to an ongoing Sale
    * 
    * @param itemToBeAdded The Item to be added to the ongoing sale
    */
    public void addNewItem(Item itemToBeAdded) {
       receipt.addNewItem(itemToBeAdded);
    }
    
    /**
     * Getter for getting the ItemDTOs in the current sale from the Receipt
     * 
     * @return Returns a List<ItemDTO> of ItemDTOs in the current sale
     */
    public List<Item> getItemsInSale() {
        return receipt.getItemsInSale();
    }
    
    /**
    * Returns the price of the sale
    * 
    * @return Returns the Double priceOfSale from the Receipt
    */ 
    public Double endSale() {
        return receipt.getPriceOfSale();
    }
    
    /**
     * Finalizes the sale by entering the amount paid as well as
     * printing the receipt and returning the change to be returned to customer
     * 
     * @param amountPaid The AmountDTO paid by the customer
     * @param saleLog    The log of Sales where this Sale is to be logged
     * @param integ      The external system handler which will handle the 
     *                   call to printer in order to print receipt
     * @return           Returns the change which is to be returned to customer
     */
    public AmountDTO enterPayment(AmountDTO amountPaid, SaleLog saleLog, 
            ExternalSystemHandler integ) {
        AmountDTO returnedChange = this.receipt.enterPayment(amountPaid);
        saleLog.addSaleToLog(this);
        integ.reportSale(this);
        integ.printReceipt(this.receipt.receiptToString());
        AmountDTO payment = new AmountDTO(amountPaid.getAmount() 
                - returnedChange.getAmount(), "SEK");
        notifyObserversOfPayment(payment);
        return returnedChange;
    }
    
    /**
     * Adds a new observer to the current sale which will be notified of payment
     * 
     * @param observer The observer which will be notified.
     */
    public void addSaleObserver(SaleObserver observer) {
        saleObservers.add(observer);
    }
    
    /**
     * Adds an entire list of observers to the current sale which 
     * will be notified of payment
     * 
     * @param observers The list of observers which will be notified.
     */
    public void addListOfSaleObservers(List<SaleObserver> observers) {
        for(SaleObserver observer : observers) {
            addSaleObserver(observer);
        }
    }
    
    private void notifyObserversOfPayment(AmountDTO amount) {
        for(SaleObserver observer : saleObservers) {
            observer.newCompletedPayment(amount);
        }
    }
}

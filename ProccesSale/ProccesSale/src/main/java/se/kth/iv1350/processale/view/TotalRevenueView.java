package se.kth.iv1350.processale.view;

import se.kth.iv1350.processale.integration.AmountDTO;
import se.kth.iv1350.processale.model.SaleObserver;

/**
 *
 * @author Oscar Eklund
 * 
 * Acts as a display which shows the total amount paid for sales since program 
 * was started.
 */
class TotalRevenueView implements SaleObserver {
    private AmountDTO amountPaid;
    
    /**
     * Creates a new instance of TotalRevenueView which acts as a display.
     */
    public TotalRevenueView() {
        this.amountPaid = new AmountDTO(0.0, "SEK");
    }
    
    /**
     * Notifies observers of a completed payment with the amount.
     * 
     * @param amount The amount of the payment.
     */
    @Override
    public void newCompletedPayment(AmountDTO amount) {
        this.amountPaid = new AmountDTO(amountPaid.getAmount() 
                + amount.getAmount(), "SEK");
        printTotalRevenue();
    }
    
    private void printTotalRevenue() {
        System.out.println("TOTALREVENUE: " + amountPaid.getAmount() 
                + " SEK revenue since start");
    }
}

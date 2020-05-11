package se.kth.iv1350.processale.model;

import se.kth.iv1350.processale.integration.AmountDTO;

/**
 *
 * @author Oscar Eklund
 * 
 * This is an interface that observes sales.
 */
public interface SaleObserver {
    
    /**
     * Notifies observers of a completed payment with the amount.
     * 
     * @param amount The amount of the payment.
     */
    void newCompletedPayment(AmountDTO amount);
}

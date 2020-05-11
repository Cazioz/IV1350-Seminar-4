package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * Thrown when connection to database is failed
 */
public class OperationFailedException extends Exception{
    
    /**
     * Creates a new instance of OperationFailedException
     * 
     * @param msg    The message that is to be sent with the exception
     * @param cause  The cause of the exception
     */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}

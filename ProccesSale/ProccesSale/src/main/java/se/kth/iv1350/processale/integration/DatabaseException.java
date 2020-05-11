package se.kth.iv1350.processale.integration;

/**
 *
 * @author Oscar Eklund
 * 
 * Thrown when an error occurs whilst trying trying to contact external systems
 * such as Inventory or Accounting.
 */
class DatabaseException extends Exception{
    private String ipAddress;
    
    /**
     * Creates a new instance of DatabaseException, for now the IP is hardcoded
     * since this is a simulation.
     */
    DatabaseException() {
        this.ipAddress = "127.0.0.1";
    }
    
    /**
     * Returns the IPaddress which was used when the exception was thrown
     * 
     * @return IPaddress of the database
     */
    String getIPAddress() {
        return this.ipAddress;
    }
    
}

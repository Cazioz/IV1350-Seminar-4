package se.kth.iv1350.processale.integration;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {
    private ExternalInventorySystem extInvSys;
    
    @BeforeEach
    public void setUp() {
        extInvSys = new ExternalInventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
        extInvSys = null;
    }
    
    @Test
    public void testGetFirstItem() {
        ItemIdentifier id = new ItemIdentifier(1234.0);
        Item milk = new Item("Some Nice Milk", 1.0, 
                new AmountDTO(15.0, "SEK"), 0.125, id);
        Item milkRetrievedFromInvSys = getItem(id);
        
        boolean result = milk.matchingItems
            (milkRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetSecondItem() {
        ItemIdentifier id = new ItemIdentifier(9876.0);
        Item potatoes = new Item("Sweet Potatoes", 0.5, 
                new AmountDTO(25.0, "SEK"), 0.125, id);
        Item potatoesRetrievedFromInvSys = getItem(id);
        
        boolean result = potatoes.matchingItems
            (potatoesRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetThirdItem() {
        ItemIdentifier id = new ItemIdentifier(5.0);
        Item ketchup = new Item("Ketchup", 1.0, 
                new AmountDTO(18.5, "SEK"), 0.125, id);
        Item ketchupRetrievedFromInvSys  = getItem(id);
        
        boolean result = ketchup.matchingItems
            (ketchupRetrievedFromInvSys.getItemIdentifier());
        assertTrue(result, "Items are not equal");
    }
    
    @Test
    public void testGetItemThatDoesNotExist() {
        ItemIdentifier idThatDoesNotExist = new ItemIdentifier(1337.0);
        
        try {
            Item itemThatDoesNotExist = extInvSys.getItem(idThatDoesNotExist);
        }
        catch (DatabaseException ex) {
            fail("DatabaseException was thrown");
        } catch (ItemNotFoundException ex) {
            assertTrue(ex.getItemIdentifier().getDoubleIdentifier() == 1337.0);
        }
    }
    
    @Test
    public void testFailToConnectToDatabase() {
        ItemIdentifier idConnectionError = new ItemIdentifier(42.0);
        
        try {
            Item item = extInvSys.getItem(idConnectionError);
        }
        catch (DatabaseException ex) {
            assertTrue(ex.getIPAddress().equals("127.0.0.1"));
        } catch (ItemNotFoundException ex) {
            fail("ItemNotFoundException was thrown");
        }
    }
    
    
    private Item getItem(ItemIdentifier id) {
        Item returnedItem = null;
        
        try {
        returnedItem = extInvSys.getItem(id);
        } catch (DatabaseException ex) {
            fail("DatabaseException was thrown");
        } catch (ItemNotFoundException ex) {
            fail("ItemNotFoundException was thrown");
        }
        return returnedItem;
    }
}

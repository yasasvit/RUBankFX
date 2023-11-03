package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
* This class is a JUnit test for the close method of the AccountDatabase class
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/

class AccountDatabaseTest {

    /**
     * Creates two tests for close method of AccountDatabase class
     */
    @Test
    void close() {
        AccountDatabase accountDatabase = new AccountDatabase();

        Date dob1 = new Date(10, 20, 1995);
        Account account1 = new Checking(new Profile("Mike", "Williams", dob1), 3000);
        assertTrue(accountDatabase.open(account1));

        // Valid case: Close account that exists
        assertTrue(accountDatabase.close(account1));

        // Invalid case: Close account that does not exist
        Date dob2 = new Date(5, 5, 2003);
        Account nonExistingAccount = new Checking(new Profile("Alice", "Johnson", dob2), 3000);
        assertFalse(accountDatabase.close(nonExistingAccount));
    }
}

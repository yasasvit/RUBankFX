package src;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * This class is a JUnit test for the isValid method of the Date class
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
class DateTest {

    /**
     * Creates two valid dates to test the isValid() method of Date class
     */
    @Test
    void testIsValidWithValidCases() {
        // Valid date for Leap year
        Date validDate1 = new Date("2/29/2020");
        assertTrue(validDate1.isValid());

        // Valid date for Non-leap year
        Date validDate2 = new Date("3/15/2022");
        assertTrue(validDate2.isValid());
    }
    /**
     * Creates two five invalid dates to test the isValid() method of Date class
     */
    @Test
    void testIsValidWithInvalidCases() {
        // Invalid date for Non-leap year
        Date invalidDate1 = new Date("2/29/2011");
        assertFalse(invalidDate1.isValid());

        // Month out of range
        Date invalidDate2 = new Date("10/32/2022");
        assertFalse(invalidDate2.isValid());

        // Day out of range
        Date invalidDate3 = new Date("6/0/2022");
        assertFalse(invalidDate3.isValid());

        // Year out of range
        Date invalidDate4 = new Date("2/15/2032");
        assertFalse(invalidDate4.isValid());

        // Invalid leap year date
        Date invalidDate5 = new Date("2/30/2020");
        assertFalse(invalidDate5.isValid());
    }
}
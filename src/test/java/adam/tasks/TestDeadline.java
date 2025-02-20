package adam.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import adam.exceptions.AdamException;

/**
 * Test class for Deadline.
 */
class TestDeadline {
    /**
     * Tests the toString method of Deadline.
     */
    @Test
    void testToString() {
        try {
            Deadline d = new Deadline("Test Deadline", LocalDate.parse("2021-12-31"));
            assertEquals("[D][ ] Test Deadline (by: 31 Dec 2021)", d.toString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }

    /**
     * Tests the toLogString method of Deadline.
     */
    @Test
    void testToLogString() {
        try {
            Deadline d = new Deadline("Test Deadline", LocalDate.parse("2021-12-31"));
            assertEquals("D | false | Test Deadline | 31-12-2021", d.toLogString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }

    /**
     * Tests the isOn method of Deadline.
     */
    @Test
    void testIsOn() {
        try {
            LocalDate date = LocalDate.parse("2021-12-31");
            LocalDate dayBefore = LocalDate.parse("2021-12-30");
            Deadline d = new Deadline("Test Deadline", date);
            assertEquals(true, d.isOn(date));
            assertEquals(false, d.isOn(dayBefore));
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }
}

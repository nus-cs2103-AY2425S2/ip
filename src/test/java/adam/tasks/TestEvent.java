package adam.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import adam.exceptions.AdamException;

/**
 * Test class for Event.
 */
class TestEvent {
    /**
     * Tests the toString method of Event.
     */
    @Test
    void testToString() {
        try {
            Event e = new Event("Test Event", LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-30"));
            assertEquals("[E][ ] Test Event (from: 20 Dec 2021 to: 30 Dec 2021)", e.toString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }

    /**
     * Tests the toLogString method of Event.
     */
    @Test
    void testToLogString() {
        try {
            Event e = new Event("Test Event", LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-30"));
            assertEquals("E | false | Test Event | 20-12-2021 | 30-12-2021", e.toLogString());
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }

    /**
     * Tests the isOn method of Event.
     */
    @Test
    void testIsOn() {
        try {
            LocalDate startDate = LocalDate.parse("2021-12-20");
            LocalDate endDate = LocalDate.parse("2021-12-30");

            Event e = new Event("Test Event", startDate, endDate);
            assertEquals(true, e.isOn(startDate));
            assertEquals(true, e.isOn(endDate));
            assertEquals(false, e.isOn(startDate.minusDays(1)));
            assertEquals(false, e.isOn(endDate.plusDays(1)));
        } catch (AdamException e) {
            assertEquals(0, 1);
        }
    }
}

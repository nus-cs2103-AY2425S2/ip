package clank.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code Event} class.
 */
public class EventTest {

    /**
     * Tests the creation of an Event task and its string representation.
     */
    @Test
    public void testEventCreation() {
        Event event = new Event("Team meeting", "1/3/2025 1000", "2/3/2025 1200");
        assertEquals("[E][ ] Team meeting (from: 1 March 2025, 10:00 AM to: 2 March 2025, 12:00 PM)",
                event.toString());
    }

    /**
     * Tests that the save format of an Event task is correctly formatted.
     */
    @Test
    public void testSaveFormat() {
        Event event = new Event("Team meeting", "1/3/2025 1000", "2/3/2025 1200");
        assertEquals("E|false|Team meeting|1/3/2025 1000|2/3/2025 1200", event.toSaveFormat());
    }
}

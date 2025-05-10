package phil.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



/**
 * Represents Testing Class for Event Class.
 */
public class EventTest {

    /**
     * Tests the toString method of the Event class with a new Event.
     */
    @Test
    public void toString_newEvent_correctStringOutput() {
        Event event = new Event("book sharing session", "afternoon", "night");
        assertEquals("[E][ ] book sharing session (from: afternoon to: night)", event.toString());
        event.markDone();
        assertEquals("[E][X] book sharing session (from: afternoon to: night)", event.toString());
        event.markNotDone();
        assertEquals("[E][ ] book sharing session (from: afternoon to: night)", event.toString());
    }

    /**
     * Tests the toLoadString method of the Event class with a new Event.
     */
    @Test
    public void toLoadString_newEvent_correctStringOutput() {
        Event event = new Event("book sharing session", "afternoon", "night");
        assertEquals("Event -   - book sharing session - afternoon - night", event.toLoadString());
        event.markDone();
        assertEquals("Event - X - book sharing session - afternoon - night", event.toLoadString());
        event.markNotDone();
        assertEquals("Event -   - book sharing session - afternoon - night", event.toLoadString());
    }

    /**
     * Tests the toString method of the Event class with a new Event.
     * This event gives the from and to dates with a valid date-time input.
     */
    @Test
    public void toString_newEventWithDateTime_correctStringOutput() {
        Event event = new Event("book sharing session", "2/7/25 1000", "2/7/25 1500");
        assertEquals("[E][ ] book sharing session (from: Feb 7, 2025 10.00AM to: Feb 7, 2025 3.00PM)",
                event.toString());
        event.markDone();
        assertEquals("[E][X] book sharing session (from: Feb 7, 2025 10.00AM to: Feb 7, 2025 3.00PM)",
                event.toString());
        event.markNotDone();
        assertEquals("[E][ ] book sharing session (from: Feb 7, 2025 10.00AM to: Feb 7, 2025 3.00PM)",
                event.toString());
    }

    /**
     * Tests the toLoadString method of the Event class with a new Event.
     */
    @Test
    public void toLoadString_newEventWithDateTime_correctStringOutput() {
        Event event = new Event("book sharing session", "2/7/25 1000", "2/7/25 1500");
        assertEquals("Event -   - book sharing session - 2/7/25 1000 - 2/7/25 1500", event.toLoadString());
        event.markDone();
        assertEquals("Event - X - book sharing session - 2/7/25 1000 - 2/7/25 1500", event.toLoadString());
        event.markNotDone();
        assertEquals("Event -   - book sharing session - 2/7/25 1000 - 2/7/25 1500", event.toLoadString());
    }

}
